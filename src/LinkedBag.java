public class LinkedBag<T> implements BagInterface<T>{

    
    private static final int DEFAULT_CAPACITY = 25; //the default size of the array, this is a resizeable array class
    private int numberOfEntries; //will change everytime an enry is put into the bag

    /******************************************* Linked List Implementation <Start> *******************************************/

    

    private class Node {
        private T data;
        private Node next;

        private Node(T data){
            this(data, null);
        }

        private Node(T data, Node next){
            this.data = data;
            this.next = next;
        }

        private T getData(){
            return data;
        }

        private void setData(T newData){
            data = newData;
        }

        private Node getNextNode(){
            return next;
        }

        private void setNextNode(Node nextNode){
            next = nextNode;
        }
    }

    private Node head = new Node(null);

    /******************************************* Linked List Implementation <End> *******************************************/

    public LinkedBag(){
        head = null;
        numberOfEntries = 0;
    }


    //This "copy constructor" will create a duplicate of a bag so we do not affect
    //the contents of the original bags
    public LinkedBag(BagInterface<T> copy){ 
        head = null;
        numberOfEntries = 0;
        T[] tempBag = copy.toArray(); //duplicates array of objects
        for(T each : tempBag){
            this.add(each);
        }
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
        return numberOfEntries == DEFAULT_CAPACITY;
    }

    @Override
    public boolean add(T newEntry) {
        
        if(!isFull()){
            Node nextNode = new Node(newEntry);
            nextNode.next = head;
            head = nextNode;
            //head.next = nextNode;
            System.out.println(newEntry + " successfully added!");
            numberOfEntries++;
            return true;
        }else{
            System.out.println("Cannot add " + newEntry + ", capacity of" + DEFAULT_CAPACITY + " reached.");
            return false;
        }
    }

    @Override
    public T remove() {
        T result = null;
        if(head != null){
            result = head.getData();
            head = head.getNextNode();
            numberOfEntries--;
        }
        
        return result;
    }

    @Override
    public boolean remove(T anEntry) {
        boolean result = false;
        Node nodeN = getReferenceTo(anEntry);

        if(nodeN != null){
            nodeN.setData(head.getData());
            head = head.getNextNode();
            numberOfEntries--;

            result = true;
        }

        return false;
    }

    @Override
    public void clear() {
        while(!isEmpty())
            remove();
    }

    @Override
    public int getFrequencyOf(T anEntry) {
        int frequency = 0;

        int counter = 0;
        Node curreNode = head;
        while ((counter < numberOfEntries) && (curreNode != null)){
            if(anEntry.equals(curreNode.getData())){
                frequency++;
            }

            counter++;
            curreNode = curreNode.getNextNode();
        }

        return frequency;
    }

    @Override
    public boolean contains(T anEntry) {
        boolean found = false;
        Node currentNode = head;

        while(!found && (currentNode != null)){
            if(anEntry.equals(currentNode.getData()))
                found = true;
            else
                currentNode = currentNode.getNextNode();
        }

        return found;
    }

    @Override
    public T[] toArray() {
        @SuppressWarnings("unchecked")
        T[] result = (T[])new Object[numberOfEntries];

        int index = 0;
        Node currentNode = head;
        while ((index < numberOfEntries) && (currentNode != null)){
            result[index] = currentNode.getData();
            index++;
            currentNode = currentNode.getNextNode();
        }

        return result;
    }

    public String toString(){

        String output = "\nEntries: " + this.getCurrentSize() + "\nContents:\n";

        int index = 0;
        Node currentNode = head;

        if(this.isEmpty()){
            output += "<EMPTY>\n";
        } else while((index < numberOfEntries) && (currentNode != null)){
            output += (index+1) + ". " + currentNode.getData() + "\n";
            index++;
            currentNode = currentNode.getNextNode();
        }
           

        return output;
    }


        /****************************************************** 3 METHODS ******************************************************/

    public BagInterface<T> union(BagInterface<T> aBag){ //returns a BagInterface per instructions (means can be arraybag or linked bag)
        BagInterface<T> tempBag = new LinkedBag<T>(); //temp bag for output
        LinkedBag<T> thisClone = new LinkedBag<T>(this); //clone of "this" bag (bag 1)
        LinkedBag<T> aBagClone = new LinkedBag<T>(aBag); //clone of another bag (bag 2) *cloning is to ensure the original bags are not changed

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
        BagInterface<T> tempBag = new LinkedBag<T>();
        LinkedBag<T> thisClone = new LinkedBag<T>(this);
        LinkedBag<T> aBagClone = new LinkedBag<T>(aBag);

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
        BagInterface<T> outputBag = new LinkedBag<T>();
        while(!tempBag.isEmpty()){
            outputBag.add(tempBag.remove());
        }

        return outputBag;
    }

    public BagInterface<T> difference(BagInterface<T> aBag){
        //cloning...
        LinkedBag<T> thisClone = new LinkedBag<T>(this);
        LinkedBag<T> aBagClone = new LinkedBag<T>(aBag);

        while(!aBagClone.isEmpty()){//while bag2 is not empty
            thisClone.remove(aBagClone.remove());//remove an item from bag2 and try to remove it from bag1
        }
        
        //move items from tempbag to new bag for output
        BagInterface<T> outputBag = new LinkedBag<T>();
        while(!thisClone.isEmpty()){
            outputBag.add(thisClone.remove());
        }

        return outputBag;

    }

    /****************************************************** HELPERS ******************************************************/

    private Node getReferenceTo(T anEntry){

        boolean found = false;
        Node currentNode = head;

        while(!found && (currentNode != null)){
            if(anEntry.equals((currentNode.getData())))
                found = true;
            else
            currentNode = currentNode.getNextNode();
        }

        return currentNode;
    }



    
}
