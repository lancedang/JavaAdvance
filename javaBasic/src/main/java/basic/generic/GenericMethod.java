package basic.generic;

public class GenericMethod {

	public static <T> void show(T[] array) {
		for (T t : array) {
			System.out.printf("%s ", t);
		}
		System.out.print(".....................");
	}

	public static void main(String[] args) {
		Integer[] i = { 2, 5, 6, 3, 9 };
		Double[] d = { 1.21, 3.15, 8.04, 8.44 };
		Character[] c = { 'd', 'g', 'r', 'u', 'i' };

		show(i);
		show(d);
		show(c);
	}
}
