import java.util.Arrays;

public class bagDriver {
    public static void main(String[] args) throws Exception {

        // LinkedBagTest.testLinkedBag();
        // ArrayBagTest.testArrayBag();

        String[] arrayDriverStringsOne = {"This", "is", "the", "driver", "program", "for", "project", "one!"};
        String[] arrayDriverStringTwo = {"This", "is", "a", "test", "string", "for", "project", "one."};

        ResizableArrayBag<String> bag1 = new ResizableArrayBag<>();
        ResizableArrayBag<String> bag2 = new ResizableArrayBag<>();
        LinkedBag<String> bagOne = new LinkedBag<>();
        LinkedBag<String> bagTwo = new LinkedBag<>();

        for (int i = 0; i < arrayDriverStringsOne.length; i++){
            bag1.add(arrayDriverStringsOne[i]);
        }

        for (int i = 0; i < arrayDriverStringTwo.length; i++){
            bag2.add(arrayDriverStringTwo[i]);
        }

        ResizableArrayBag<String> newUnion = (ResizableArrayBag<String>) bag1.union(bag2);
        System.out.println(newUnion.toString());

        ResizableArrayBag<String> newIntersection = (ResizableArrayBag<String>) bag1.intersection(bag2);
        System.out.println(newIntersection.toString());

        ResizableArrayBag<String> newDifference = (ResizableArrayBag<String>) bag1.difference(bag2);
        System.out.println(newDifference.toString());

        for (int i = 0; i < arrayDriverStringsOne.length; i++){
            bagOne.add(arrayDriverStringsOne[i]);
        }

        for (int i = 0; i < arrayDriverStringTwo.length; i++){
            bagTwo.add(arrayDriverStringTwo[i]);
        }

        LinkedBag<String> linkedUnion = (LinkedBag<String>) bagOne.union(bagTwo);
        System.out.println(linkedUnion.toString());

        LinkedBag<String> linkedIntersection = (LinkedBag<String>) bagOne.union(bagTwo);
        System.out.println(linkedIntersection.toString());

        LinkedBag<String> linkedDifference = (LinkedBag<String>) bagOne.union(bagTwo);
        System.out.println(linkedDifference.toString());



    }

}
