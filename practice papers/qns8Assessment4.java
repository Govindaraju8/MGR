package JavaCore;

import java.util.HashMap;

public class qns8Assessment4 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		HashMap<String, Integer> hash_map = new HashMap<String, Integer>();
		hash_map.put("President", 1);
		hash_map.put("Manager", 2);
		hash_map.put("Clerk", 3);
		hash_map.put("SalesMan", 4);
		hash_map.put("Developer", 5);
		// print the map
		System.out.println("The Original map: " + hash_map);
		System.out.println("Is key 'clerk' exists?");
		if (hash_map.containsKey("Clerk")) {
			// key exists
			System.out.println("yes! - " + hash_map.get("Clerk"));
		} else {
			// key does not exists
			System.out.println("no!");
		}

		System.out.println("\n Is key 'Tester' exists?");
		if (hash_map.containsKey("Tester")) {
			System.out.println("yes! - " + hash_map.get("Tester"));
		} else {
			System.out.println("no!");
		}

	}

}
