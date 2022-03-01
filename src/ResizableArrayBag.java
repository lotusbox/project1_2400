import java.util.Arrays;

public class ResizableArrayBag<T> implements BagInterface<T>{


    private T[] bag; //final so reference will not change
    private static final int DEFAULT_CAPACITY = 25; //the default size of the array, this is a resizeable array class
    public int capacity; //the constructor receives a parameter to fill this field with to define a new array with higher capacity if needed
    private int numberOfEntries; //will change everytime an enry is put into the bag
    private Boolean integrityOK = false; //ensures bag is not corrupt i.e. out of bounds


    /** 
     * Default Constructor
     */
    public ResizableArrayBag(){
        this(DEFAULT_CAPACITY);
    }

    /** 
     * Constructor for class
     */
    public ResizableArrayBag(int capacity){
        if(capacity <= DEFAULT_CAPACITY){
            numberOfEntries = 0;
            @SuppressWarnings("unchecked") //these three lines cast a new object into the bag class, which is generic, because
            T[] tempBag = (T[])new Object[capacity]; //we do not yet know what T[] will be
            this.capacity = capacity; //must use @suppress warning because this type of operation is typically unsafe or unchecked
            bag = tempBag; //temp bag, our temporary bag, is assigned to bag, our proper bag, becaue is now has an appropriate size
            integrityOK = true;
        }
        else
            throw new IllegalStateException("Attempting to create a bag whose capacity exceeds allowed maximum!");
        
    }
    
    /** 
     * Copy constructor for class
     * Clones an existing Array or Linked bag as an Array bag
     */
    public ResizableArrayBag(BagInterface<T> copy){
        numberOfEntries = copy.getCurrentSize(); //duplicates parameter
        T[] tempBag = copy.toArray(); //duplicates array of objects
        this.capacity = copy.getCurrentSize(); //duplicates capacity
        bag = tempBag; //temp bag assigned to proper bag
    }


    /** Gets the current number of entries in this bag.
     @return  The integer number of entries currently in the bag. */
    @Override
    public int getCurrentSize() {
        return numberOfEntries;
    }

    /** Sees whether this bag is empty.
    @return  True if the bag is empty, or false if not. */
    @Override
    public boolean isEmpty() {
        return numberOfEntries == 0;
    }

    /** Sees whether this bag is full.
    @return  True if the bag is full, or false if not. */
    @Override
    public boolean isFull() {
        return numberOfEntries == capacity;
    }

    /** Adds a new entry to this bag.
    @param newEntry  The object to be added as a new entry.
    @return  True if the addition is successful, or false if not. */
    @Override
    public boolean add(T newEntry) {
        
        checkIntegrity();
        if (isFull()){
            if(!doubleCapacity()){
                System.out.println("Could not add " + newEntry + ", doubling capacity would exceed maximum of " + DEFAULT_CAPACITY);
            }
            return false;
        }
        
        if(newEntry != null) {
            bag[numberOfEntries] = newEntry;
            ++numberOfEntries;
            //System.out.println("\"" + newEntry + "\" " + "added at position " + numberOfEntries); //used for testing
            return true;
        }
        
        return false;
    }

    /** Removes one unspecified entry from this bag, if possible.
    @return  Either the removed entry, if the removal was successful, or null. */
    @Override
    public T remove() {
        if (isEmpty()){
            System.out.println("Bag is empty, nothing to remove!"); //used for testing
            return null;
        }else {
            T removed = removeHelper(numberOfEntries - 1);
            //System.out.println(removed + " succesfully removed!"); //used for testing
            return removed;
        }
    }

    /** Removes one occurrence of a given entry from this bag, if possible.
    @param anEntry  The entry to be removed.
    @return  True if the removal was successful, or false if not. */
    @Override
    public boolean remove(T anEntry) {
        int index = findEntry(anEntry);
        if(index < 0){
            //System.out.println(anEntry + " not found!"); //used for testing
            return false;
        } else {
            removeHelper(index);
            //System.out.println(anEntry + " successfully removed!"); //used for testing
            return true;
        }
    }

    /** Removes all entries from this bag. */
    @Override
    public void clear() {
        for(int i = 0; i < bag.length; i++){
            bag[i] = null;
        }
        //System.out.println("Clearing contents of bag...\n"); //used for testing
        numberOfEntries = 0;
    }

    /** Counts the number of times a given entry appears in this bag.
    @param anEntry  The entry to be counted.
    @return  The number of times anEntry appears in the bag. */
    @Override
    public int getFrequencyOf(T anEntry) {
        int count = 0;
        for(int i = 0; i < this.numberOfEntries; ++i){
            if(bag[i].equals(anEntry)){
                count++;
            }
        }
        return count;
    }

    /** Tests whether this bag contains a given entry.
    @param anEntry  The entry to find.
    @return  True if the bag contains anEntry, or false if not. */
    @Override
    public boolean contains(T anEntry) {
        return findEntry(anEntry) >= 0;
    }

    /** Retrieves all entries that are in this bag.
    @return  A newly allocated array of all the entries in the bag. Note: If the bag is empty, the returned array is empty. */
    @Override
    public T[] toArray() {
        @SuppressWarnings("unchecked")
        T[] tempbag = (T[])new Object[this.numberOfEntries];
        for(int i = 0; i < this.numberOfEntries; i++){
            tempbag[i] = this.bag[i];
        }
        return tempbag;
    }

    /** Outputs data about a bag, used for testing purposes
     @return  A bag's capacity, entries, and contents as a String */
    public String toString(){

        String output = "\nCapacity: " + this.capacity + "\nEntries: " + this.getCurrentSize() + "\nContents:\n";

        if(this.isEmpty()){
            output += "<EMPTY>\n";
        } else for(int i = 0; i < this.numberOfEntries; i++){
            output += " (" + (i + 1) + ") " + this.bag[i].toString() + "\n";
        }

        return output;
    }

    /****************************************************** 3 METHODS ******************************************************/

    /** Returns the union of elements in two bags.
    @return  A new bag containing all elements of both bags, including duplicates */
    public BagInterface<T> union(BagInterface<T> aBag){ //returns a BagInterface per instructions (means can be arraybag or linked bag)
        BagInterface<T> tempBag = new ResizableArrayBag<T>(this.numberOfEntries + aBag.getCurrentSize()); //temp bag for output
        ResizableArrayBag<T> thisClone = new ResizableArrayBag<T>(this); //clone of "this" bag (bag 1)
        ResizableArrayBag<T> aBagClone = new ResizableArrayBag<T>(aBag); //clone of another bag (bag 2) *cloning is to ensure the original bags are not changed

        while(!thisClone.isEmpty()){//as long as bag1 is not empty
            tempBag.add(thisClone.remove()); //remove an item from bag1 add add it to tempbag
            //System.out.println(thisClone.numberOfEntries);
        }
        while(!aBagClone.isEmpty()){//as long as bag2 is not empty
            tempBag.add(aBagClone.remove()); //remove an item from bag2 and add it to tempbag
            //System.out.println(aBagClone.numberOfEntries);
        }
        return tempBag;
    }


    /** Returns the intersection of elements in two bags.
    @return  A new bag containing the intersection of both bags */
    public BagInterface<T> intersection(BagInterface<T> aBag){
        //cloning...
        BagInterface<T> tempBag = new ResizableArrayBag<T>(DEFAULT_CAPACITY);
        ResizableArrayBag<T> thisClone = new ResizableArrayBag<T>(this);
        ResizableArrayBag<T> aBagClone = new ResizableArrayBag<T>(aBag);

        while(!thisClone.isEmpty()){ //as long as bag1 is not empty
            T item = thisClone.remove(); //remove an item from bag1 and hold it
            if(aBagClone.remove(item)){ //if bag2 contains the removed item, remove it also and return true, true == the same item was removed from both bags
                tempBag.add(item);//add an item to tempbag IFF an item was removed from bag1 AND bag2
            }
        }

        //move items from temp bag to a new bag for output
        //this step is necessary because temp bag's capacity is too big
        //e.g. tempbag = [a, b, c, null, null, null, ...]
        //so create an output bag that's only big enough to hold [a, b, c]
        BagInterface<T> outputBag = new ResizableArrayBag<T>(tempBag.getCurrentSize());
        while(!tempBag.isEmpty()){
            outputBag.add(tempBag.remove());
        }

        return outputBag;
    }

    /** Returns the difference of elements in two bags.
    @return  A new bag containing the difference of the first bag minus the contents of @param aBag */
    public BagInterface<T> difference(BagInterface<T> aBag){
        //cloning...
        //BagInterface<T> tempBag = new ResizableArrayBag<T>(DEFAULT_CAPACITY);
        ResizableArrayBag<T> thisClone = new ResizableArrayBag<T>(this);
        ResizableArrayBag<T> aBagClone = new ResizableArrayBag<T>(aBag);

        while(!aBagClone.isEmpty()){//while bag2 is not empty
            thisClone.remove(aBagClone.remove());//remove an item from bag2 and try to remove it from bag1
        }
        
        //move items from tempbag to new bag for output
        BagInterface<T> outputBag = new ResizableArrayBag<T>(thisClone.getCurrentSize());
        while(!thisClone.isEmpty()){
            outputBag.add(thisClone.remove());
        }

        return outputBag;

    }


    /****************************************************** HELPERS ******************************************************/

    /** Returns index of a found entry, helper function for remove().
    @return  the index of an entry, if found, or -1 if not */
    private int findEntry(T anEntry) {
        for(int i = 0; i < numberOfEntries; ++i){
            if(bag[i].equals(anEntry)){
                return i;
            }
        }
        return -1;
    }

    /** Removes an entry based on last index
    @return  the entry removed */
    private T removeHelper(int i){
        T removedEntry = bag[i];
        bag[i] = bag[numberOfEntries -1];
        bag[numberOfEntries - 1] = null;
        --numberOfEntries;
        return removedEntry;
    }

    /** Checks if a bag's capacity exceeds the default or "max"
    @return  True if under max capacity, false if not */
    private boolean checkCapacity(int capacity){
        return capacity <= DEFAULT_CAPACITY;
    }

    /** Creates a new array with double the capacity
    @return  Returns true if array has been doubled, or false if doubling would exceed max capacity*/
    private boolean doubleCapacity(){
        int newCapacity = 2 * bag.length;
        if(checkCapacity(newCapacity)){
            System.out.println("\n*** Bag full, capacity doubled from " + bag.length + " to " + newCapacity + " ***\n");
            bag = Arrays.copyOf(bag, newCapacity);
            capacity = newCapacity;
            return true;
        }else{
            return false;
        }
        
    }

    /** Ensures array remains within capacity*/
    private void checkIntegrity(){
        if(!integrityOK)
            throw new SecurityException("ArrayBag object is corrput.");
    }
    
}