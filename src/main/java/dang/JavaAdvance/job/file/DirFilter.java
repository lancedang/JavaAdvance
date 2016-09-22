package dang.JavaAdvance.job.file;

import java.io.File;
import java.io.FilenameFilter;
import java.util.regex.Pattern;

import dang.JavaAdvance.job.polymorp.PrivateOverride;

public class DirFilter implements FilenameFilter {
	private Pattern pattern;

	public DirFilter(String regex) {
		// TODO Auto-generated constructor stub
		pattern = Pattern.compile(regex);
	}

	@Override
	public boolean accept(File dir, String name) {

		// TODO Auto-generated method stub
		return pattern.matcher(name).matches();

	}

}
