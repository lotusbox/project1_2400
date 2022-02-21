import java.util.Arrays;

public class bagDriver {
    public static void main(String[] args) throws Exception {

        int capacity = 3;
        ResizableArrayBag<Character> testBag = new ResizableArrayBag<Character>(capacity);

        System.out.println("\n\n***************************************");
        System.out.println("******** TESTING DRIVER CLASS *********");
        System.out.println("***************************************\n");

        System.out.println("******* Testing add() *******\n");
        System.out.println(testBag.toString());
        testBag.add('a');
        testBag.add('b');
        testBag.add('c');
        testBag.add('d');
        System.out.println(testBag.toString());

        System.out.println("******* Testing remove() *******\n");
        System.out.println(testBag.toString());
        testBag.remove();
        testBag.remove('a');
        System.out.println(testBag.toString());

        System.out.println("******* Testing clear() *******\n");
        System.out.println(testBag.toString());
        testBag.clear();;
        System.out.println(testBag.toString());

        System.out.println("******* Testing getFrequencyOf() *******\n");
        testBag.add('a');
        testBag.add('b');
        testBag.add('b');
        System.out.println(testBag.toString());
        System.out.println("Frequency of " + "'b' is " + testBag.getFrequencyOf('b'));
        System.out.println("Frequency of " + "'a' is " + testBag.getFrequencyOf('a'));
        System.out.println("Frequency of " + "'A' is " + testBag.getFrequencyOf('A'));
        
        System.out.println("\n******* Testing contains() *******\n");
        System.out.println(testBag.toString());
        System.out.println("Bag contains 'a': " + testBag.contains('a'));
        System.out.println("Bag contains 'A': " + testBag.contains('A'));

        System.out.println("\n******* Testing toArray() *******\n");
        System.out.println(testBag.toString());
        System.out.println("Array of contents: " + Arrays.toString(testBag.toArray()));

        System.out.println("\n******* Testing union() *******\n");

        ResizableArrayBag<Character> bag1 = new ResizableArrayBag<Character>(3);
        ResizableArrayBag<Character> bag2 = new ResizableArrayBag<Character>(5);

        bag1.add('a');
        bag1.add('a');
        bag1.add('b');

        bag2.add('a');
        bag2.add('a');
        bag2.add('a');
        bag2.add('b');
        bag2.add('c');

        System.out.println("\nBag 1:\n" + bag1.toString());
        System.out.println("Bag 2:\n" + bag2.toString());
        System.out.println("\nUnion of Bag 1 and 2: " + Arrays.toString(bag1.union(bag2).toArray()));
        
        System.out.println("\n******* Testing intersection() *******\n");
        
        System.out.println("\nBag 1:\n" + bag1.toString());
        System.out.println("Bag 2:\n" + bag2.toString());
        System.out.println("\nIntersection of Bag 1 and 2: " + Arrays.toString(bag1.intersection(bag2).toArray()));

        System.out.println("\n******* Testing difference() *******\n");
        
        System.out.println("\nBag 1:\n" + bag1.toString());
        System.out.println("Bag 2:\n" + bag2.toString());
        System.out.println("\nDifference of Bag 1 and 2: " + Arrays.toString(bag1.difference(bag2).toArray()) + "\n");
        System.out.println("\nDifference of Bag 2 and 1: " + Arrays.toString(bag2.difference(bag1).toArray()) + "\n");
        

    }

}
