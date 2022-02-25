import java.util.Arrays;

public class bagDriver {
    public static void main(String[] args) throws Exception {

        // LinkedBagTest.testLinkedBag();
        // ArrayBagTest.testArrayBag();

        String[] arrayDriverStringsOne = {"This", "is", "the", "driver", "program", "for", "project", "one!"};
        String[] arrayDriverStringTwo = {"This", "is", "a", "test", "string", "for", "project", "one."};

        ResizableArrayBag<String> bag1 = new ResizableArrayBag<String>();
        ResizableArrayBag<String> bag2 = new ResizableArrayBag<String>();
        LinkedBag<String> bagOne = new LinkedBag<String>();
        LinkedBag<String> bagTwo = new LinkedBag<String>();

        for (int i = 0; i < arrayDriverStringsOne.length; i++){
            bag1.add(arrayDriverStringsOne[i]);
        }

        for (int i = 0; i < arrayDriverStringTwo.length; i++){
            bag2.add(arrayDriverStringTwo[i]);
        }

        //testing union
        System.out.println(Arrays.toString(bag1.union(bag2).toArray()));
        //testing intersection
        System.out.println(Arrays.toString(bag1.intersection(bag2).toArray()));

        //testing difference
        System.out.println(Arrays.toString(bag1.difference(bag2).toArray())); 

        for (int i = 0; i < arrayDriverStringsOne.length; i++){
            bagOne.add(arrayDriverStringsOne[i]);
        }

        for (int i = 0; i < arrayDriverStringTwo.length; i++){
            bagTwo.add(arrayDriverStringTwo[i]);
        }

        //testing union
        System.out.println(Arrays.toString(bagOne.union(bagTwo).toArray()));

        //testing intersection
        System.out.println(Arrays.toString(bagOne.intersection(bagTwo).toArray()));

        //testing difference
        System.out.println(Arrays.toString(bagOne.difference(bagTwo).toArray())); 



    }

}
