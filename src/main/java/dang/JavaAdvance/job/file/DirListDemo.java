package dang.JavaAdvance.job.file;

import java.io.File;
import java.util.Arrays;

public class DirListDemo {
	public static void main(String[] args) {
		File path = new File(".");
		String[] lists;
		if (args == null) {
			lists = path.list();
		} else {
			lists = path.list(new DirFilter(args[0]));
		}
		Arrays.sort(lists, String.CASE_INSENSITIVE_ORDER);
		for (String item: lists) {
			System.out.println(item);
		}
	}
}
