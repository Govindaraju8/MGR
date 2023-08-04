// Java Program to demonstrate iterate
// an Iterable using an Iterator


/*We can iterate the elements of Java Iterable by obtaining the Iterator from it using the iterator() method. 

The methods used while traversing the collections using Iterator to perform the operations are:

hasNext(): It returns false if we have reached the end of the collection, otherwise returns true.
next(): Returns the next element in a collection.
remove(): Removes the last element returned by the iterator from the collection.
forEachRemaining(): Performs the given action for each remaining element in a collection, in sequential order.

*/
import java.io.*;
import java.util.*;

public class IterateUsingIterator {
	public static void main(String[] args)
	{
		List<String> list = new ArrayList<>();

		list.add("Geeks");
		list.add("for");
		list.add("Geeks");

		Iterator<String> iterator = list.iterator();

		while (iterator.hasNext()) {
			String element = iterator.next();
			System.out.println(element);
		}
	}
}
