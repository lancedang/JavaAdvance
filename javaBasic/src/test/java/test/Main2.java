package test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main2 {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner input = new Scanner(System.in);
		
		int i = 0;
		int j = 0;
		int k = 0;

		List<Integer> list = new ArrayList<>();

		String number = input.nextLine();
		String[] c = number.split("\\s+");
		Map<String, Integer> map = new HashMap<>();
		int num = 0;
		for (String ss : c) {
			
			if(map.get(ss) != null) {
				map.put(ss, map.get(ss)+1);
			}
		}
		
		int len = list.size();

		for (j = 0; j < len; j++) {
			int f = 0;
			for (k = 0; k < len; k++) {
				if (list.get(j) == list.get(k)) {
					f++;
				}
			}
			if (f == 1) {
				System.out.println(list.get(j));
				break;
			}

		}
	}
}
