package JavaCore;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

class listManipulations {
	ArrayList<Integer> l1 = new ArrayList<>();

	public void sumAndAvg(List l) {
		int sum = 0, avg = 0;
		for (int i = 0; i < l.size(); i++) {
			sum += (int) l.get(i);
		}
		avg = sum / l.size();
		System.out.println("Sum:" + sum + "\nAvg:" + avg);
	}

	public void evenSum(List l) {
		int sum = 0;
		for (int i = 0; i < l.size(); i++) {
			if (((int) l.get(i)) % 2 == 0)
				sum += (int) l.get(i);
		}
		System.out.println("EvenSum:" + sum);
	}

	public void findDups(List l) {
		int count, x = 0, max = 0, y = 0;
		for (int i = 0; i < l.size(); i++) {
			count = 0;
			for (int j = 0; j < l.size(); j++) {
				if (l.get(i) == l.get(j)) {
					count++;
					x = j;
				}

			}
			if (count > max) {
				max = count;
				y = (int) l.get(x);
			}

			System.out.println("element:" + l.get(i) + "repeated" + count + "times");
			i = x;
		}
		System.out.println("element:" + y + "  repeated max of" + max);
	}

	public void delDups(List l) {
		int count = 0, x = 0;
		for (int i = 0; i < l.size(); i++) {
			l1.add((Integer) l.get(i));
			count = 0;
			for (int j = 0; j < l.size(); j++) {
				if (l.get(i) == l.get(j)) {
					count++;
					x = j;
				}

			}
			i = x;
		}
		System.out.println("list Without duplicates");
		for (int i = 0; i < l1.size(); i++) {
			System.out.println(l1.get(i));
		}
	}
}

public class qns2Assessment4 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int n, x;
		n = sc.nextInt();
		List<Integer> l = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			x = sc.nextInt();
			l.add(x);
		}

		Collections.sort(l);
		for (int i = 0; i < l.size(); i++) {
			System.out.println(l.get(i));
		}
		listManipulations lm = new listManipulations();
		lm.sumAndAvg(l);
		lm.evenSum(l);
		lm.findDups(l);
		lm.delDups(l);
	}

}
