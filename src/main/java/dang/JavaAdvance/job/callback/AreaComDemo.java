package dang.JavaAdvance.job.callback;

import java.util.Arrays;

/**
 * Arrays.sort(T[] ts)元素都实现了Comparable接口,更深刻直接的是实现了compareTo()方法, Sort元素时,
 * 方法内部会将所有元素值向上转型为Comparable类型, 然后调用该类自定义的compareTo()方法比较
 * 
 * @author Dangdang
 *
 */
public class AreaComDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Area[] locations = { new Area(5, 2), new Area(1, 2), new Area(3, 4) };
		Arrays.sort(locations); // sort内部会回调 类中自定义的compareTo方法,
								// 用Comparable接口定义比较规则则体现了策略模式的运用,查看sort方法原码可以看到Comparable
								// array[i].compareTo()字样
		for (Area l : locations) {
			System.out.println(l);
		}
		/**
		if(true) return;
		else return;
		**/
	}

}
