package advance.job.callback;

import java.io.File;
import java.io.FilenameFilter;
import java.util.regex.Pattern;

/**
 * 接口定义文件名过滤规则,只需实现其accept方法,accept()方法因不同实现类而不同
 * 
 * @author Dangdang
 *
 */
public class MyDirFilter implements FilenameFilter {
	private Pattern pattern;

	public MyDirFilter(String regex) {
		// TODO Auto-generated constructor stub
		pattern = Pattern.compile(regex);
	}

	@Override
	public boolean accept(File dir, String name) {
		// TODO Auto-generated method stub
		return pattern.matcher(name).matches();
	}

}
