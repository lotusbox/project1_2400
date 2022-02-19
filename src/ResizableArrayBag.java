import java.util.Arrays;

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

    @Override
    public int getCurrentSize() {
        return numberOfEntries;
    }

    @Override
    public boolean isEmpty() {
        if (numberOfEntries != 0)
            return false;
        else
            return true;
    }

    @Override
    public boolean isFull() {
        return numberOfEntries == capacity - 1;
    }

    @Override
    public boolean add(T newEntry) {
        if (isFull() || newEntry == null){
            System.out.println("bag is full!");
            return false;
        } else{
            bag[numberOfEntries] = newEntry;
            ++numberOfEntries;
            return true;
        }
    }

    @Override
    public T remove() {
        if (isEmpty()){
            return null;
        }else {
            T removed = removeHelper(numberOfEntries - 1);
            return removed;
        }
    }

    @Override
    public boolean remove(T anEntry) {
        int index = findEntry(anEntry);
        if(index < 0){
            return false;
        } else {
            removeHelper(index);
            return true;
        }
    }

    @Override
    public void clear() {
        for(int i = 0; i < bag.length; i++){
            bag[i] = null;
        }
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
    public T[] toArray (T[] a) {
        return Arrays.copyOf(bag, numberOfEntries);
    }

    /****************************************************** 3 METHODS ******************************************************/

    public ResizableArrayBag<T> union(ResizableArrayBag<T> aBag){
        for(int i = 0; i < aBag.numberOfEntries; i++){
            this.add(aBag.bag[i]);
        }
        return this;
        /*
        @SuppressWarnings("unchecked")
        T[] tempbag = (T[])new Object[this.numberOfEntries + aBag.getCurrentSize];
        
        for(int i = 0; i < this.numberofEntries; i++){
            tempbag.add(this[i]);
        }
        for(int i = 0; i < aBag.getCurrentSize; i++)
        {
            tempbag.add(aBag[i]);
        }

        return tempbag;
        */ 
    }

    public ResizableArrayBag<T> Intersection(ResizableArrayBag<T> aBag){
        return null;
    }

    public ResizableArrayBag<T> Difference(ResizableArrayBag<T> aBag){
        return null;
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

    public T[] 
    
}
