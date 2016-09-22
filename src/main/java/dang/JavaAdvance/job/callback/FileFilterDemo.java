package dang.JavaAdvance.job.callback;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 回调函数+策略设计模式的运用(实际上就是多态+回调)
 * @author Dangdang
 *
 */
public class FileFilterDemo {
	public static void main(String[] args) {
		MyDirFilter filter;
		File path = new File(".");
		
		String[] lists;
		Iterator<String> iterator;
		Iterable<String> iterable;
		List<String> list = new ArrayList<>();
		if (args == null) {
			lists = path.list();
		} else {
			filter = new MyDirFilter(args[0]);
			lists = path.list(filter); //list方法会自动调用filer内部的accept方法
		}
	}
}
