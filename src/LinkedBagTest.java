import java.util.Arrays;
import java.util.Scanner;

public class LinkedBagTest {
    
    //wraps testing of ArrayBag 
    public static void testLinkedBag(){
        LinkedBag<Character> testBag = new LinkedBag<Character>();

        System.out.println((Arrays.toString(testBag.toArray())));

        System.out.println("\n\n***************************************");
        System.out.println("******* TESTING LinkedBag CLASS *******");
        System.out.println("***************************************\n");

        System.out.println("******* Testing add() *******\n");
        System.out.println(testBag.toString());
        System.out.println("Adding a, b, c, d...");
        testBag.add('a');
        testBag.add('b');
        testBag.add('c');
        testBag.add('d');
        System.out.println(testBag.toString());
        promptEnterKey();

        System.out.println("******* Testing remove() *******\n");
        System.out.println(testBag.toString());
        System.out.println("Testing remove()");
        testBag.remove();
        System.out.println(testBag.toString());
        System.out.println("testing remove('a')");
        testBag.remove('a');
        System.out.println(testBag.toString());
        promptEnterKey();

        System.out.println("******* Testing clear() *******\n");
        System.out.println(testBag.toString());
        testBag.clear();;
        System.out.println(testBag.toString());
        promptEnterKey();

        System.out.println("******* Testing getFrequencyOf() *******\n");
        testBag.add('a');
        testBag.add('b');
        testBag.add('b');
        System.out.println(testBag.toString());
        System.out.println("Frequency of " + "'b' is " + testBag.getFrequencyOf('b'));
        System.out.println("Frequency of " + "'a' is " + testBag.getFrequencyOf('a'));
        System.out.println("Frequency of " + "'A' is " + testBag.getFrequencyOf('A'));
        promptEnterKey();
        
        System.out.println("\n******* Testing contains() *******\n");
        System.out.println(testBag.toString());
        System.out.println("Bag contains 'a': " + testBag.contains('a'));
        System.out.println("Bag contains 'A': " + testBag.contains('A'));
        promptEnterKey();

        System.out.println("\n******* Testing toArray() *******\n");
        System.out.println(testBag.toString());
        System.out.println("Array of contents: " + Arrays.toString(testBag.toArray()));
        promptEnterKey();

        System.out.println("\n******* Testing union() *******\n");

        LinkedBag<Character> bag1 = new LinkedBag<Character>();
        LinkedBag<Character> bag2 = new LinkedBag<Character>();

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
        promptEnterKey();
        
        System.out.println("\n******* Testing intersection() *******\n");
        
        System.out.println("\nBag 1:\n" + bag1.toString());
        System.out.println("Bag 2:\n" + bag2.toString());
        System.out.println("\nIntersection of Bag 1 and 2: " + Arrays.toString(bag1.intersection(bag2).toArray()));
        promptEnterKey();

        System.out.println("\n******* Testing difference() *******\n");
        
        System.out.println("\nBag 1:\n" + bag1.toString());
        System.out.println("Bag 2:\n" + bag2.toString());
        System.out.println("\nDifference of Bag 1 and 2: " + Arrays.toString(bag1.difference(bag2).toArray()) + "\n");
        System.out.println("\nDifference of Bag 2 and 1: " + Arrays.toString(bag2.difference(bag1).toArray()) + "\n");
        promptEnterKey();
    }

    private static void promptEnterKey(){
        System.out.println("<Press any key to continue>");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
     }
}
