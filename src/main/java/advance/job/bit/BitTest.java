package advance.job.bit;

public class BitTest {
	/**
	 * 用Java的 自带函数，Integer.bitCount(int key)
	 * 
	 * @param key
	 * @return
	 */
	public static int numberOfOne(int key) {
		System.out.println(Integer.bitCount(key));
		return Integer.bitCount(key);
	}
	
	/**
	 * 要求key二进制中数字1的个数，可以用1与二进制流每一位做与运算, 但是下面方法只适用于正数
	 * @param key
	 * @return
	 */
	public static int numberOfOne2(int key) {
		int count = 0;
		while (key != 0) {
			
			if ((key & 1) == 1) {	//结果为1，000001这种模式
				count ++;
			}
			
			key = key >> 1; //正数右移无所谓，负数就错了
		}
		return count;
	}
	
	/**
	 * 改进版，key不动，让位 1 从左到右移动，
	 * @param key
	 * @return
	 */
	public static int numberOfOne3(int key) {
		int count = 0;
		int temp = 1;
		while (temp != 0) {
			if ((temp & key) != 0) //此处已不再是=1了，因为与后的结果为0001000这种样式
			{
				count++;
			}
			temp = (temp << 1); //保证key不动，将与位 1，从左向右移动，直到移出表示范围，即为0
		}
		return count;
	}
	
	
	/**
	 * string 的replaceAll("", "");
	 * @param n
	 * @return
	 */
	public static int numberOfOneOther(int n) {
		String string = Integer.toBinaryString(n);
		String string2 = string.replaceAll("0", "");
		return string2.length();
	}
	/**
	 * 利用n和n-1与运算；n-1是将n的最后一个1变成0，n中该1后面的所有1变成0，所有0变成1；有此可以得出n和(n-1)取与后，
	 * 最后一个1及其后面所有位都变为0 
	 * 
	 * @param n
	 * @return
	 */
	public static int numberOfOne4(int n) {
		int count = 0;
		while (n != 0) {
			n = n & (n - 1); // n 和
								// n-1取与，每次取与，消去最后一位1，此位后面与后全为0，剩余前面的1，直到把所有1与光，结果全为0时
			count++;
		}
		return count;
	}

	public static void main(String[] args) {
		numberOfOne3(-2);
	}
}
