package basic.io;

import java.io.*;

public class FileUtil {
	
	/**
	 * 复制二进制文件如图片，音频，采用字节流
	 * FileInputStream.read()
	 * @param srcPath
	 * @param destPth
	 */
	public static void copyWithoutBufferBytes(String srcPath, String destPth) {
		long start = System.currentTimeMillis();
		InputStream iStream;
		OutputStream outputStream;

		try {
			System.out.println("srcPath " + srcPath);
			iStream = new FileInputStream("F:\\Foxit.exe");
			outputStream = new FileOutputStream(destPth);
			
			int tmp;
			while((tmp = iStream.read()) != -1) {
				//outputStream.write(bs, 0, len);
				outputStream.write(tmp);
			}
			
			//outputStream.flush();
			iStream.close();
			// fileInputStream.close();
			outputStream.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		long end = System.currentTimeMillis();
		System.out.println("without buffer time is " + (end - start) + " ms");
	}

	/**
	 * 复制二进制文件如图片，音频，采用字节流
	 * FileInputStream.read(byte[])
	 * @param srcPath
	 * @param destPth
	 */
	public static void copyWithBufferBytes(String srcPath, String destPth) {
		long start = System.currentTimeMillis();
		InputStream iStream;
		OutputStream outputStream;

		try {
			//System.out.println(srcPath);
		
			iStream = new FileInputStream("F:\\Foxit.exe");
			outputStream = new FileOutputStream(destPth);
			
			byte[] bs = new byte[1024];
			int len;
			while((len = iStream.read(bs)) != -1) {
				//outputStream.write(bs, 0, len);
				outputStream.write(bs, 0, len);
			}
			
			outputStream.flush();
			iStream.close();
			// fileInputStream.close();
			outputStream.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		long end = System.currentTimeMillis();
		System.out.println("with buffer time is " + (end - start) + " ms");
	}
	
	/**
	 * 复制二进制文件如图片，音频，采用字节流
	 * BufferedInputStream.read()
	 * @param srcPath
	 * @param destPth
	 */
	public static void copyWithoutBufferEnhance(String srcPath, String destPth) {
		long start = System.currentTimeMillis();
		File srcFile;
		File destFile;

		InputStream iStream;
		OutputStream outputStream;

		BufferedInputStream bufferedInputStream;
		BufferedOutputStream bufferedOutputStream;

		srcFile = new File(srcPath);
		destFile = new File(destPth);

		try {
			iStream = new FileInputStream("F:\\Foxit.exe");
			bufferedInputStream = new BufferedInputStream(iStream);

			outputStream = new FileOutputStream(destFile);
			bufferedOutputStream = new BufferedOutputStream(outputStream);

			int tmp;
			while ((tmp = bufferedInputStream.read()) != -1) {
				outputStream.write(tmp);
			}

			bufferedOutputStream.flush();
			iStream.close();
			bufferedInputStream.close();
			// fileInputStream.close();
			outputStream.close();
			bufferedOutputStream.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		long end = System.currentTimeMillis();
		System.out.println("with bufferedInputStream no buffer time is " + (end - start) + " ms");
	}
	
	/**
	 * 复制二进制文件如图片，音频，采用字节流
	 * BufferedInputStream.read(byte[])
	 * @param srcPath
	 * @param destPth
	 */
	public static void copyWithBufferEnhance(String srcPath, String destPth) {
		long start = System.currentTimeMillis();
		File srcFile;
		File destFile;

		InputStream iStream;
		OutputStream outputStream;

		BufferedInputStream bufferedInputStream;
		BufferedOutputStream bufferedOutputStream;

		srcFile = new File(srcPath);
		destFile = new File(destPth);

		try {
			iStream = new FileInputStream("F:\\Foxit.exe");
			bufferedInputStream = new BufferedInputStream(iStream);

			outputStream = new FileOutputStream(destFile);
			bufferedOutputStream = new BufferedOutputStream(outputStream);

			int len;
			byte[] bs = new byte[1024];
			while ((len = bufferedInputStream.read(bs)) != -1) {
				outputStream.write(bs, 0, len);
			}
			
			bufferedOutputStream.flush();
			iStream.close();
			bufferedInputStream.close();
			// fileInputStream.close();
			outputStream.close();
			bufferedOutputStream.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		long end = System.currentTimeMillis();
		System.out.println("with bufferedInputStream buffer time is " + (end - start) + " ms");
	}
	


}
