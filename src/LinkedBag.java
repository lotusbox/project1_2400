public class LinkedBag implements BagInterface{

    private Node    
    // need to define next node data field


    class Node <T> {
        T data;
        private Node nextNode;

    }

    @Override
    public int getCurrentSize() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public boolean isEmpty() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean isFull() {
        // TODO Auto-generated method stub
        // dont need to add because a linked bag cant be full???
        return false;
    }

    @Override
    public boolean add(Object newEntry) {
        // TODO Auto-generated method stub
        // create new node
        return false;
    }

    @Override
    public Object remove() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean remove(Object anEntry) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void clear() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public int getFrequencyOf(Object anEntry) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public boolean contains(Object anEntry) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public Object[] toArray() {
        // TODO Auto-generated method stub
        return null;
    }


    
}
