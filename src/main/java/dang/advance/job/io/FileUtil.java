package dang.advance.job.io;

import java.io.*;

/**
 * Created by Dangdang on 2018/4/11.
 * 本案例主要讲解使用字节流复制文件，并比较不同 io api 的操作性能
 * 字节文件即通常所说的二进制文件包括mp3,exe等
 * 字符文件就是txt, doc 等文件
 * 操作性能通过复制时间来衡量
 */
public class FileUtil {

    /**
     * 复制二进制文件如图片，音频，采用字节流，并且没有采用 Buffer 缓存
     * FileInputStream.read()
     *
     * @param srcPath
     * @param destPath
     */
    public static void copyWithoutBuffer(String srcPath, String destPath) throws IOException {

        long start = System.currentTimeMillis();

        //根据指定path 创建一个File instance, 指向的 文件可能存在或不存在, 可通过 exist 函数判断
        File file = new File(srcPath);
        //file.exists();
        //建立到该 file 的 inputStream 用于从中读取字节
        InputStream inputStream = new FileInputStream(file);

        //建立到目的文件的输出流,且若指定文件不存在将会自动创建，存在的话则覆盖重写
        OutputStream outputStream = new FileOutputStream(destPath);

        //字节自动转化为int, byte->int
        int temp = -1;

        //开始从源文件一个字节一个字节的读取,并将读取结果放在int temp 中存储，当值为 -1 的时候表明到达文件尾部
        //这里单纯的读一个字节然后写一个字节，并没有将读到的若干个字节缓存起来，一次写入
        while ((temp = inputStream.read()) != -1) {
            //将读到的每个字节依次写入 outputStream
            outputStream.write(temp);
        }

        outputStream.flush();

        outputStream.close();
        inputStream.close();

        long end = System.currentTimeMillis();

        System.out.println("copyWithoutBuffer 耗时 " + (end - start) + " ms");
    }

    /**
     * 只是用 BufferedOutputStream
     *
     * @param srcPath
     * @param destPath
     * @param bufferSize 每次读取缓存大小
     * @throws IOException
     */
    public static void copyWithOutputBuffer(String srcPath, String destPath, int bufferSize) throws IOException {

        long start = System.currentTimeMillis();

        File file = new File(srcPath);

        InputStream inputStream = new FileInputStream(file);

        //依旧建立到destFile 的最原始 outputStream
        OutputStream outputStream = new FileOutputStream(destPath);

        //封装 最原始的outputStream 为 BufferOutPutStream，对inputStream 读取结果进行缓存
        OutputStream bufferedOutputStream = new BufferedOutputStream(outputStream);

        //刚才是同一个 int 来存放每次读到的唯一的 byte，现在用一个 byte 数组来存放一次读到的若干个字节
        //这里先假设每次读 10 个字节
        //buffer array
        byte[] temp = new byte[bufferSize];

        int result = -1;

        //上一方法是读一个字节写一个字节，现在是读若干个字节写若干个字节
        //且将每次读的若干字节存到 temp buffer Array 中
        //result 表示每次读取的字节数，-1表示已经读完
        while ((result = inputStream.read(temp)) != -1) {
            //将缓存的多个字节 temp 写入输出流,参数 temp 表示每次读取缓存的数据
            bufferedOutputStream.write(temp);
        }

        bufferedOutputStream.close();
        outputStream.close();
        inputStream.close();

        long end = System.currentTimeMillis();
        System.out.println("copyWithOutputBuffer 耗时 " + (end - start) + " ms");
    }

    /**
     * 同时使用 BufferedInputStream 和 BufferedOutputStream
     *
     * @param srcPath
     * @param destPath
     * @throws IOException
     */
    public static void copyWithBothBufferByByte(String srcPath, String destPath) throws IOException {

        long start = System.currentTimeMillis();

        File file = new File(srcPath);
        if (!file.exists()) {
            //如果src 文件不存在
            System.out.println("source file not exist");
            return;
        }

        InputStream inputStream = new FileInputStream(file);
        //用 缓存字节流 封装无缓存的 inputStream，如此一来可以每次多读若干字节
        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);

        OutputStream outputStream = new FileOutputStream(destPath);

        //用缓存输出流封装 无缓存的outputStream
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream);

        int temp = -1;

        //这里仍旧每次读取一个字节，读到 BufferedInputStream 中，然后缓存若干字节再写入 程序中
        while ((temp = bufferedInputStream.read()) != -1) {
            //这里仍旧每次写一个 byte，但是是写到 bufferOutputStream 中，BufferedOutputStream 会缓存然后再写入文件
            bufferedOutputStream.write(temp);
        }

        bufferedOutputStream.flush();

        bufferedOutputStream.close();
        outputStream.close();
        bufferedInputStream.close();
        inputStream.close();

        long end = System.currentTimeMillis();
        System.out.println("copyWithBothBufferByByte 耗时 " + (end - start) + " ms");
    }

    /**
     * 同时使用 BufferedInputStream 和 BufferedOutputStream
     *
     * @param srcPath
     * @param destPath
     * @param bufferSize 缓存大小
     */
    public static void copyWithBothBufferByBuffer(String srcPath, String destPath, int bufferSize) throws IOException {

        long start = System.currentTimeMillis();

        File file = new File(srcPath);

        if (!file.exists()) {
            System.out.println("file not exists");
            return;
        }

        InputStream inputStream = new FileInputStream(file);

        //使用buffer 缓存
        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);

        OutputStream outputStream = new FileOutputStream(destPath);

        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream);

        byte[] buffer = new byte[bufferSize];

        int result = -1;

        //这次使用 buffer 读
        while ((result = bufferedInputStream.read(buffer)) != -1) {
            //写的时候也用buffer 写
            bufferedOutputStream.write(buffer);
        }

        bufferedOutputStream.flush();

        bufferedOutputStream.close();
        outputStream.close();

        bufferedInputStream.close();
        inputStream.close();

        long end = System.currentTimeMillis();
        System.out.println("copyWithBothBufferByBuffer 耗时 " + (end - start) + " ms");

    }

    public static void main(String[] args) throws IOException {
        String srcPath = "F:\\WiFiMasterKey.exe";
        String destPath1 = "F:\\WiFiMasterKey-copy1.exe";
        String destPath2 = "F:\\WiFiMasterKey-copy2.exe";
        String destPath3 = "F:\\WiFiMasterKey-copy3.exe";
        String destPath4 = "F:\\WiFiMasterKey-copy4.exe";

        //使用 byte
        copyWithoutBuffer(srcPath, destPath1);

        //使用 byte[]
        copyWithOutputBuffer(srcPath, destPath2, 1024);

        //使用 byte
        copyWithBothBufferByByte(srcPath, destPath3);

        //使用 byte[]
        copyWithBothBufferByBuffer(srcPath, destPath4, 1024);

    }

}
