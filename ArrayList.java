import java.util.*;

public class ArrayListExample {
    public static void main(String[] args) {
        // Create an ArrayList of Integers
        ArrayList<Integer> integerList = new ArrayList<>();

        // Add elements to the ArrayList
        integerList.add(10);
        integerList.add(20);
        integerList.add(30);
        

      Iterator<Integer> intlist = integerList.iterator();

        // Access elements from the ArrayList
        System.out.println("Elements in the integerList:");
        while (intlist.hasNext()) {
            System.out.println(intlist.next());
        }
        integerList.forEach(number -> System.out.println(number));

        // Create an ArrayList of Strings
        ArrayList<String> stringList = new ArrayList<>();

        // Add elements to the ArrayList
        stringList.add("Hello");
        stringList.add("World");
        stringList.add("!");
        
        Iterator<String> strlist = stringList.iterator();

        // Access elements from the ArrayList
        System.out.println("Elements in the integerList:");
        while (strlist.hasNext()) {
            System.out.println(strlist.next());
        }
         stringList.forEach(str -> System.out.println(str));

    }
}
