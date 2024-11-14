package basic.reference;

import java.lang.ref.SoftReference;

public class KindsReference {
	public static void main(String[] args) {
		String str = new String("abc");
		SoftReference<String> softReference = new SoftReference<String>(str);
	}
}
