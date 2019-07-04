package basic.map;

import java.util.HashMap;
import java.util.Random;

public class Exg1 {
	public static void main(String[] args) {
		HashMap<Integer, Ctime> h1 = new HashMap<Integer, Ctime>();
		Random r1 = new Random();
		for (int i = 0; i < 1000; i++) {
			Integer t = r1.nextInt(30);
			if (h1.containsKey(t))
				(h1.get(t)).count++;
			else
				h1.put(t, new Ctime());
		}
		System.out.println(h1);
	}
}

class Ctime {
	int count = 1;

	public String toString() {
		return Integer.toString(count);
	}
}
