package advance.job.recursive;

import java.util.Scanner;

public class MaxGongYue {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		int p = scanner.nextInt();
		int q = scanner.nextInt();

		int result = getGongYue(p, q);
		System.out.printf("%d, %d 的最大公约数是 ：%d", p, q, result);
		scanner.close();
	}

	/**
	 * 首先确定p,q大于0，
	 * 然后由于需要求余运算，故需确定中间递归，p1, q1为0的情况
	 * 接着，较大数除以较小数 得余数，余数和较小数做递归
	 * @param p
	 * @param q
	 * @return
	 */
	public static int getGongYue(int p, int q) {
		if (p < 0 || q < 0) {
			return 0;
		} else if (p == 0 || q == 0) {
			if(p == 0) {
				return q;
			}
			
			return p;
			
		} else {
			int r = (q > p) ? (q % p) : (p % q);
			return getGongYue(r, (q > p) ? p : q);

		}

	}
	
	 // 求最小公倍数  
    public static int minCommonMultiple(int m, int n) {  
    	if(m == 0 || n == 0) {
    		return 0;
    	}
        return m * n / getGongYue(m, n);  
    }  
}