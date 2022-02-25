public class LinkedBag<T> implements BagInterface<T>{

    
    private static final int DEFAULT_CAPACITY = 25; //the max size of the linkedBag (linked lists do not have a limit capacity so we define one here as default)
    private int numberOfEntries; //will change everytime an enry is put into the bag

    /******************************************* Linked List Implementation <Start> ********************************************/

    //the LinkedBag uses a linked list to store objects

    //a node is an obj in a linked list.  It stores one object @data and points to the next node @next;
    //it also has getters and setters as defined by the assignment, but not actually necessary.
    private class Node {
        private T data; //stores one  object
        private Node next; 
        //points to the "next" node.  initially points to null.  each time 1 element is added to the bag, a new Node is created to store the new element.
        //this new node becomes the new "head" or first node in the chain.  The @next of this new node points to the previous "head", extending the chain

        private Node(T data){//constructor for a node
            this(data, null);//constructor within a constructor (see next constructor)
        }

        private Node(T data, Node next){//constructor for a node that sets data and next simultaneously (this is odd but the class notes write it this way)
            this.data = data;
            this.next = next;
        }

        private T getData(){//returns the item stored in this node
            return data;
        }

        private void setData(T newData){//replaces the item stored in this node
            data = newData;
        }

        private Node getNextNode(){//returns the address of the next node
            return next;
        }

        private void setNextNode(Node nextNode){//sets the address of the next node
            next = nextNode;
        }
    }

    private Node head = new Node(null);  //the default "head" of the linked list.  Initially, @head holds nothing and points to null.

    /******************************************* Linked List Implementation <End> *******************************************/

    public LinkedBag(){ //constuctor for a linked bag;
        head = null;
        numberOfEntries = 0;
    }


    //This "copy constructor" will create a duplicate of a bag so we do not affect
    //the contents of the original bags
    public LinkedBag(BagInterface<T> copy){ 
        head = null;
        numberOfEntries = 0;
        T[] tempBag = copy.toArray(); //converts "copy" into an array.  We don't know if the bag to be cloned is a linked or array bag
        for(T each : tempBag){
            this.add(each);//fills this linked bag with the array's contents
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
        
        if(!isFull()){//if the Default Capacity is not reached (in this case we set it at 25,  otherwise it would be infinite)
            Node newNode = new Node(newEntry);//create a new node filled with the element we want to add to the bag
            newNode.next = head; //point the "next node" pointer at head (head was the previous start of the chain)
            head = newNode; //set head as the new node.  Now the new node or new head is first, and it points at the previous head, which is now second in line.
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
        T result = null; //create a temp holder named result
        if(head != null){//if the first element is not empty (the linked list is  not empty)
            result = head.getData();//fill the temp holder with head's data so we can return it
            head = head.getNextNode(); //we are deleting the head node, so the new head is the next node after head
            numberOfEntries--;
        }
        
        return result;
    }

    @Override
    public boolean remove(T anEntry) {
        boolean result = false; //by default, entry is not found
        
        Node deleteThisNode = getReferenceTo(anEntry); //create a new node and try to find @anEntry, if found, deletethisnode will point to that node by reference
        if(deleteThisNode != null){//if an Entry is found, deleteThisNode will no longer be null
            deleteThisNode.setData(head.getData());//because we want to delete the node we found, we replace its data with the data from head (because head is easiest to access)
            head = head.getNextNode();//next, head is deleted.  The node following head becomes the new head.  Data from the old head is now stored in the deleteThisNode's location, and the original data from that node is gone
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

    public String toString(){ //this is for testing

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
