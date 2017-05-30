package dang.advance.job;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class WordLinkTest {
	public static void main(String[] args) {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		int number;
		String[] array;
		boolean flag = true;
		try {
			number = Integer.parseInt(reader.readLine());
			array = new String[number];
			String str;
			array[0] = reader.readLine();
			int index = 0;
			while ((str = reader.readLine()) != null) {
				char head = str.charAt(0);
				char oldTail = array[index].charAt(array[index].length() - 1);
				// System.out.println("oldTail = " + oldTail);
				if (oldTail == head) {
					array[index + 1] = str;
					index++;
				} else {
					System.out.println("No");
					return;
				}
				if (index + 1 == number) {
					System.out.println("Yes");
					return;
				}
			}
			
			//return;
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
