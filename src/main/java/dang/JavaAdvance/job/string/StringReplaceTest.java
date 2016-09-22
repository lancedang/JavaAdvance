package dang.JavaAdvance.job.string;

import java.util.StringTokenizer;

/**
 * 考察的数据结构： char[], StringBuffer 内部还是char[], StringBuffer.charAt(i),
 * .substring(int begin), append(char c), append(String str), new StringBuffer(char[] array)
 * 
 * @author Dangdang
 *
 */
public class StringReplaceTest {
	/**
	 * 只能保证将字符串中的空格替换成 %20 ，并且连续空格当一个处理
	 * 
	 * @param str
	 * @return
	 */
	public static String stringReplace(StringBuffer str) {

		StringBuffer temp = new StringBuffer("");
		StringTokenizer tokenizer = new StringTokenizer(str.toString());
		while (tokenizer.hasMoreTokens()) {
			temp.append("%20" + tokenizer.nextToken());
		}

		String ss = temp.substring(3);
		String sts = str.toString();

		if (sts.startsWith(" ") && !sts.endsWith(" ")) {
			return "%20" + ss;
		} else if (!sts.startsWith(" ") && sts.endsWith(" ")) {
			return ss + "%20";
		} else if (sts.startsWith(" ") && sts.endsWith(" ")) {
			return "%20" + ss + "%20";
		}
		return ss;
	}

	/**
	 * 将字符串中的所有空格替换成 %20 ，一个空格对应一个
	 * 
	 * @param str
	 * @return
	 */
	public static String stringReplace2(StringBuffer str) {
		int len1 = str.length();
		int len2 = len1 + 2 * getBlankNum(str.toString()); // 计算替换后数组的大小
		char[] array = new char[len2]; // StringBuffer 内部即是 自动扩容的 数组

		int i = 0;
		int j = 0;

		while (i < len1) {
			char c = str.charAt(i);
			if (c != ' ') {
				array[j++] = c; // StringBuffer.append(char c)
			} else {
				array[j++] = '%'; // 直接用StringBuffer.append(String str)
				array[j++] = '2';
				array[j++] = '0';
			}
			i++;
		}
		return new String(array);
	}

	/**
	 * 获取字符串中空格的数量
	 * 
	 * @param str
	 * @return
	 */
	public static int getBlankNum(String str) {
		int i = 0;
		int len = str.length();
		int num = 0;
		while (i < len) {
			if (str.charAt(i) == ' ') {
				num++;
			}
			i++;
		}
		return num;
	}

	/**
	 * 将获取字符串空格数和替换结合，初定char数组大小为某确定值
	 * 
	 * @param str
	 * @return
	 */
	public static String stringReplace3(String str) {
		int len1 = str.length();
		int i = 0;
		int j = 0;
		char[] array = new char[100];
		while (i < len1) {
			char c = str.charAt(i);
			if (c == ' ') {
				array[j++] = '%';
				array[j++] = '2';
				array[j++] = '0';

			} else {
				array[j++] = c;
			}
			i++;
		}
		// Arrays.copyOf(array, j);
		return new String(array, 0, j);
	}

	/**
	 * 最优解，StringBuffer.append(String str), StringBuffer.append(char c)
	 * 实际上，StringBuffer内部就是 char[]数组，StringBuffer替代了方法2、3中的char[]
	 * 
	 * @param str
	 * @return
	 */
	public static String stringReplace4(StringBuffer str) {
		StringBuffer result = new StringBuffer("");
		int len1 = str.length();
		// int len2 = 0;
		int i = 0;
		while (i < len1) {
			char c = str.charAt(i);
			if (c == ' ') {
				result.append("%20");
			} else {
				result.append(c);
			}
			i++;
		}
		return result.toString();
	}

	public static String stringReplace5(StringBuffer stringBuffer) {
		return stringBuffer.toString().replaceAll(" ", "%20");
	}
	
	public static void main(String[] args) {
		StringBuffer test = new StringBuffer("I am a student ");
		
		System.out.println(stringReplace5(test));
	}
}
