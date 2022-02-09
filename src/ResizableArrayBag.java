import java.util.Arrays;

public class ResizableArrayBag<T> implements BagInterface<T>{

    private final T[] bag;
    private static final int DEFAULT_CAPACITY = 25;
    public int capacity;
    private int numberOfEntries;


    public ResizableArrayBag(){ //default constructor
        this(DEFAULT_CAPACITY);
    }

    public ResizableArrayBag(int capacity){
        numberOfEntries = 0;
        @SuppressWarnings("unchecked")
        T[] tempBag = (T[])new Object[capacity];
        this.capacity = capacity;
        bag = tempBag;
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
    public T[] toArray(T[] a) {
        return Arrays.copyOf(bag, numberOfEntries);
    }

    /****************************************************** 3 METHODS ******************************************************/

    public ResizableArrayBag<T> union(ResizableArrayBag<T> aBag){
        for(int i = 0; i < aBag.numberOfEntries; i++){
            this.add(aBag.bag[i]);
        }
        return this;
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
