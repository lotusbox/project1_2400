

public class ResizableArrayBag<T> implements BagInterface<T>{ //resizable array bag implements bag interface to 'inherit' methods that need 
                                                              //to be customized for a resizable bag 

    private final T[] bag; //final so reference will not change
    private static final int DEFAULT_CAPACITY = 25; //the default size of the array, this is a resizeable array class
    public int capacity; //the constructor receives a parameter to fill this field with to define a new array with higher capacity if needed
    private int numberOfEntries; //will change everytime an enry is put into the bag


    public ResizableArrayBag(){ //default constructor
        this(DEFAULT_CAPACITY);
    }

    public ResizableArrayBag(int capacity){
        numberOfEntries = 0;
        @SuppressWarnings("unchecked") //these three lines cast a new object into the bag class, which is generic, because
        T[] tempBag = (T[])new Object[capacity]; //we do not yet know what T[] will be
        this.capacity = capacity; //must use @suppress warning because this type of operation is typically unsafe or unchecked
        bag = tempBag; //temp bag, our temporary bag, is assigned to bag, our proper bag, becaue is now has an appropriate size
    }

    public ResizableArrayBag(BagInterface<T> copy){ //This "copy constructor" will create a duplicate of a bag so we do not affect
                                                    //the contents of the original bags
        numberOfEntries = copy.getCurrentSize(); //duplicates parameter
        T[] tempBag = copy.toArray(); //duplicates array of objects
        this.capacity = copy.getCurrentSize(); //duplicates capacity
        bag = tempBag; //temp bag assigned to proper bag
    }


    @Override
    public int getCurrentSize() {
        return numberOfEntries;
    }

    @Override
    public boolean isEmpty() {
        return numberOfEntries == 0;
    }

    @Override
    public boolean isFull() {
        return numberOfEntries == capacity;
    }

    @Override
    public boolean add(T newEntry) {
        if (isFull() || newEntry == null){
            System.out.println("\nWARNING: Could not add \"" + newEntry + "\", bag is full!"); //used for testing
            return false;
        } else{
            bag[numberOfEntries] = newEntry;
            ++numberOfEntries;
            System.out.println("\"" + newEntry + "\" " + "added at position " + numberOfEntries); //used for testing
            return true;
        }
    }

    @Override
    public T remove() {
        if (isEmpty()){
            System.out.println("Bag is empty, nothing to remove!"); //used for testing
            return null;
        }else {
            T removed = removeHelper(numberOfEntries - 1);
            System.out.println(removed + " succesfully removed!"); //used for testing
            return removed;
        }
    }

    @Override
    public boolean remove(T anEntry) {
        int index = findEntry(anEntry);
        if(index < 0){
            System.out.println(anEntry + " not found!"); //used for testing
            return false;
        } else {
            removeHelper(index);
            System.out.println(anEntry + " successfully removed!"); //used for testing
            return true;
        }
    }

    @Override
    public void clear() {
        for(int i = 0; i < bag.length; i++){
            bag[i] = null;
        }
        System.out.println("Clearing contents of bag...\n"); //used for testing
        numberOfEntries = 0;
    }

    @Override
    public int getFrequencyOf(T anEntry) {
        int count = 0;
        for(int i = 0; i < bag.length; ++i){
            if(bag[i].equals(anEntry)){
                count++;
            }
        }
        return count;
    }

    @Override
    public boolean contains(T anEntry) {
        return findEntry(anEntry) >= 0;
    }

    @Override
    public T[] toArray() {
        @SuppressWarnings("unchecked")
        T[] tempbag = (T[])new Object[capacity];
        for(int i = 0; i < this.numberOfEntries; i++){
            tempbag[i] = this.bag[i];
        }
        return tempbag;
    }

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

    public BagInterface<T> union(BagInterface<T> aBag){ //returns a BagInterface per instructions (means can be arraybag or linked bag)
        BagInterface<T> tempBag = new ResizableArrayBag<T>(this.numberOfEntries + aBag.getCurrentSize()); //temp bag for output
        ResizableArrayBag<T> thisClone = new ResizableArrayBag<T>(this); //clone of "this" bag (bag 1)
        ResizableArrayBag<T> aBagClone = new ResizableArrayBag<T>(aBag); //clone of another bag (bag 2) *cloning is to ensure the original bags are not changed

        while(!thisClone.isEmpty()){//as long as bag1 is not empty
            tempBag.add(thisClone.remove()); //remove an item from bag1 add add it to tempbag
        }
        while(!aBagClone.isEmpty()){//as long as bag2 is not empty
            tempBag.add(aBagClone.remove()); //remove an item from bag2 and add it to tempbag
        }
        return tempBag;
    }

    public BagInterface<T> intersection(BagInterface<T> aBag){//input can bve null
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

    public BagInterface<T> difference(BagInterface<T> aBag){
        //cloning...
        BagInterface<T> tempBag = new ResizableArrayBag<T>(DEFAULT_CAPACITY);
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

    private int findEntry(T anEntry) {
        for(int i = 0; i < numberOfEntries; ++i){
            if(bag[i].equals(anEntry)){
                return i;
            }
        }
        return -1;
    }

    private T removeHelper(int i){
        T removedEntry = bag[i];
        bag[i] = bag[numberOfEntries -1];
        bag[numberOfEntries - 1] = null;
        --numberOfEntries;
        return removedEntry;
    }
    
}