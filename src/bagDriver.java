import java.util.Arrays;
import java.util.Scanner;

public class bagDriver {
    public static void main(String[] args) throws Exception {
        /**
         * The driver opens a menu with the following options:
         * 1: Perform a test on all methods of the ResizableArrayBag class
         * 2: Perform a test on all methods of the LinkedBag class
         * 3: Demonstrates the 3 methods based on two pre-defined Char arrays (1 arrayBag and 1 linkedBag)
         * 4: Demonstrates the 3 methods based on user input of two Int arrays (1 arrayBag and 1 linkedBag)
         */
        int choose = 0;
        Scanner input = new Scanner(System.in);
        do{
            System.out.println("\nChoose one of the following options:");
            System.out.println("1. Run Test: ResizableArrayBag");
            System.out.println("2. Run Test: LinkedBag");
            System.out.println("3. Run Demo: Predefined Bag");
            System.out.println("4. Run Demo: User-Defined Bag");
            System.out.println("99. end program");

            System.out.print("\nSelection: ");

            choose = input.nextInt();

            switch (choose){
                case 1: ArrayBagTest.testArrayBag();
                    break;
                case 2: LinkedBagTest.testLinkedBag();
                    break;
                case 3: menuOption3();
                    break;
                case 4: menuOption4();
                    break;
                case 99: System.exit(0);
            }

            input.nextLine();
        } while (choose != 99);
        input.close();
    }

    /** Demonstrates the 3 methods based on two pre-defined Char arrays (1 arrayBag and 1 linkedBag) */
    public static void menuOption3() throws Exception{
        Character[] arrayDriverCharsOne = {'a', 'b', 'c', 'd', 'e', 'f'};
        Character[] arrayDriverCharsTwo = {'d', 'e', 'f', 'g', 'h', 'i'};

        ResizableArrayBag<Character> bag1 = new ResizableArrayBag<Character>();
        LinkedBag<Character> bag2 = new LinkedBag<Character>();
        
        for (int i = 0; i < arrayDriverCharsOne.length; i++){
            bag1.add(arrayDriverCharsOne[i]);
        }
        System.out.println("Contents of bag 1 (array): " + Arrays.toString(bag1.toArray()));
 
        for (int i = 0; i < arrayDriverCharsTwo.length; i++){
            bag2.add(arrayDriverCharsTwo[i]);
        }
        System.out.println("Contents of bag 2 (linked): " + Arrays.toString(bag2.toArray()));
        System.out.println();

        
        System.out.print("Contents of bag1.union(bag2): ");
        System.out.println(Arrays.toString(bag1.union(bag2).toArray()));

   
        System.out.print("Contents of bag1.intersection(bag2): ");
        System.out.println(Arrays.toString(bag1.intersection(bag2).toArray()));

        System.out.print("Contents of bag1.difference(bag2): ");
        System.out.println(Arrays.toString(bag1.difference(bag2).toArray()));

        System.out.println("\nReversing Bag 1 and Bag 2: \n");
        System.out.print("Contents of bag2.union(bag1): ");
        System.out.println(Arrays.toString(bag2.union(bag1).toArray()));

        System.out.print("Contents of bag2.intersection(bag1): ");
        System.out.println(Arrays.toString(bag2.intersection(bag1).toArray()));

        System.out.print("Conents of bag2.difference(bag1): ");
        System.out.println(Arrays.toString(bag2.difference(bag1).toArray()));
    }

    /** Demonstrates the 3 methods based on two user-defined Integer arrays (1 arrayBag and 1 linkedBag) */
    public static void menuOption4(){

        Scanner input = new Scanner(System.in);
        Integer[] intBagArray1;
                  intBagArray1 = new Integer[5];
        Integer[] intBagArray2;
                  intBagArray2 = new Integer[5];
        ResizableArrayBag<Integer> bag1 = new ResizableArrayBag<Integer>();
        LinkedBag<Integer> bag2 = new LinkedBag<Integer>();

        System.out.println("\nFor the first bag please input 5 integer values between 1 and 10.");
        for (int i = 0; i < 5; i++){
            intBagArray1[i] = input.nextInt();
            while ((intBagArray1[i] > 10) || (intBagArray1[i] < 0)){
                System.out.println("That value assignment is invalid,\nplease try again and choose a integer value between 1 and 10.");
                input.nextLine();
                intBagArray1[i] = input.nextInt();
            }
        }

        System.out.println("Please input 5 additional values between 1 and 10.\nSome should overlap with the first 5 values, but not all.");
        for (int i = 0; i < 5; i++){
            intBagArray2[i] = input.nextInt();
            while ((intBagArray2[i] > 10) || (intBagArray2[i] < 0)){
                System.out.println("That value assignment is invalid,\nplease try again and choose a integer value between 1 and 10.");
                input.nextLine();
                intBagArray2[i] = input.nextInt();
            }
        }

        for (int i = 0; i < intBagArray1.length; i++){
            bag1.add(intBagArray1[i]);
        }

        for (int i = 0; i < intBagArray2.length; i++){
            bag2.add(intBagArray2[i]);
        }

        System.out.print("Contents of bag1.union(bag2): ");
        System.out.println(Arrays.toString(bag1.union(bag2).toArray()));

   
        System.out.print("Contents of bag1.intersection(bag2): ");
        System.out.println(Arrays.toString(bag1.intersection(bag2).toArray()));

        System.out.print("Contents of bag1.difference(bag2): ");
        System.out.println(Arrays.toString(bag1.difference(bag2).toArray()));

        System.out.println("\nReversing Bag 1 and Bag 2: \n");
        System.out.print("Contents of bag2.union(bag1): ");
        System.out.println(Arrays.toString(bag2.union(bag1).toArray()));

        System.out.print("Contents of bag2.intersection(bag1): ");
        System.out.println(Arrays.toString(bag2.intersection(bag1).toArray()));
        System.out.print("Conents of bag2.difference(bag1): ");
        System.out.println(Arrays.toString(bag2.difference(bag1).toArray()));
    }
}
