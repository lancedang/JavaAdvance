package dang.hash;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import ove.crypto.digest.Blake2b;

/**
 * 对文件进行哈希运算，包括MD5，SHA1，Blake2b
 * @author Dangdang
 *
 */
public class HashFileUtil {
	public static final char[] hexChar = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e',
			'f' };

	public static void main(String[] args) throws NoSuchAlgorithmException, IOException {
		System.out.println("Hello World!");
		MessageDigest messageDigest = MessageDigest.getInstance("MD5");

		File demo = new File("C:\\Users\\Dangdang\\Desktop\\code\\HashTest\\500M.zip");

		long start1 = System.currentTimeMillis();
		String hashMd5 = getMd5ByFile(demo);
		System.out.println("MD5: " + hashMd5);
		long end1 = System.currentTimeMillis();

		System.out.println("md5:" + (end1 - start1));

		long start3 = System.currentTimeMillis();
		String sha1 = getSHA1ByFile(demo);
		System.out.println("sha1: " + sha1);
		long end3 = System.currentTimeMillis();
		System.out.println("sha1:" + (end3 - start3));

		long start2 = System.currentTimeMillis();
		String hashBlake = getBlake2ByFile(demo, 20, 1024 * 30);
		long end2 = System.currentTimeMillis();
		System.out.println("Blake2: " + hashBlake);
		System.out.println("Number" + ": " + (end2 - start2));

		/**
		 * 
		 * 
		 * for(int i=1; i<=40; i++) { long start2 = System.currentTimeMillis();
		 * String hashBlake = getBlake2ByFile(demo, 32, 1024*i); long end2 =
		 * System.currentTimeMillis(); System.out.println("Blake2: " +
		 * hashBlake); System.out.println("Number" + i + ": " + (end2-start2));
		 * }
		 **/
	}

	public static String getSHA1ByFile(File file) {
		String value = null;
		FileInputStream in = null;
		try {
			in = new FileInputStream(file);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		try {
			MappedByteBuffer byteBuffer = in.getChannel().map(FileChannel.MapMode.READ_ONLY, 0, file.length());
			MessageDigest sha1 = MessageDigest.getInstance("SHA1");
			sha1.update(byteBuffer);
			BigInteger bi = new BigInteger(1, sha1.digest());
			value = bi.toString(16);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != in) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return value;
	}

	public static String getMd5ByFile(File file) {
		String value = null;
		FileInputStream in = null;
		try {
			in = new FileInputStream(file);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		try {
			MappedByteBuffer byteBuffer = in.getChannel().map(FileChannel.MapMode.READ_ONLY, 0, file.length());
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			md5.update(byteBuffer);
			BigInteger bi = new BigInteger(1, md5.digest());
			value = bi.toString(16);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != in) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return value;
	}

	public static String getBlake2ByFile(File file, int digestSize, int blockSize)
			throws NoSuchAlgorithmException, IOException {
		FileInputStream in = new FileInputStream(file);

		Blake2b.Param param = new Blake2b.Param().setDigestLength(digestSize);

		byte buffer[] = new byte[blockSize];
		// 判断输入流中的数据是否已经读完的标识
		int len = 0;
		// 用于保存文件大小，单位 B
		long filesize = 0;
		// 调用md5加密文件
		// MessageDigest md5 = MessageDigest.getInstance("md5");

		Blake2b blake2b = Blake2b.Digest.newInstance(param);

		// 循环将输入流读入到缓冲区当中，(len=in.read(buffer))>0就表示in里面还有数据
		while ((len = in.read(buffer)) > 0) {

			// 调用MD5加密文件
			blake2b.update(buffer, 0, len);
			filesize += len;
			// 使用FileOutputStream输出流将缓冲区的数据写入到指定的目录(storePath + "\\"
			// + filename)当中
			// out.write(buffer, 0, len);
		}

		String hashv = toHexString(blake2b.digest());
		return hashv;
	}

	public static String toHexString(byte[] b) {
		StringBuilder sb = new StringBuilder(b.length * 2);
		for (int i = 0; i < b.length; i++) {
			sb.append(hexChar[(b[i] & 0xf0) >>> 4]);
			sb.append(hexChar[b[i] & 0x0f]);
		}
		return sb.toString();
	}

}
