package netty.buffer;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

@Slf4j
@Data
public class ByteBufferDemo {

    public static void main(String[] args) {
        //step-1: 分配一个指定大小的空空间/容器而已
        ByteBuffer byteBuffer = ByteBuffer.allocate(10);
        log.info("--------初始化缓冲区，打印最开始分配后情况-------");
        printByteBufferInfo(byteBuffer);

        log.info("--------添加数据到缓冲区，打印添加完数据后的缓冲区-------");
        log.info("--------添加数据到缓冲区，空间里的position发生变化,指向数据当前填充到的位置-------");
        log.info("--------添加数据到缓冲区，此刻还不能从其中读取数据-------");
        String str = "abcd";
        //step-2: 往容器里put点数据吧，数据得是以byte[]字节数组的形式
        //put(byteArray)是插入数据的api,参数是字节数组
        byteBuffer.put(str.getBytes(StandardCharsets.UTF_8));
        printByteBufferInfo(byteBuffer);

        try {
            log.info("--------添加数据到缓冲区，虽说还不能从其中读取数据,但我就想试一下-------");
            byte[] tempBytes = new byte[byteBuffer.limit()];
            byteBuffer.get(tempBytes);
            log.info("tempBytes={}", new String(tempBytes));
        } catch (Exception e) {
            log.error("还未flip强行读error了吧，", e);
            log.info("--------添加数据到缓冲区，虽说还不能从其中读取数据,但我就想试一下，上面就是强行读数据结果-------");
        }

        log.info("--------添加数据到缓冲区后，得flip一下进入读模式后，才允许再读数据,下面进行flip,flip之后如下-------");
        //flip()是切换到read模式的api
        byteBuffer.flip();
        printByteBufferInfo(byteBuffer);

        log.info("--------添加数据到缓冲区后，flip之后，下面展示读完所有数据的ByteBuffer，注意是position位置-------");
        log.info("--------添加数据到缓冲区后，flip之后，读几个字节，position往后走几个位置，直到limit位置-------");
        log.info("--------添加数据到缓冲区后，flip之后，现在我们专门不读所有数据，读的长度=写入长度-1 -------");
        try {
            byte[] tempBytes = new byte[byteBuffer.limit() - 2];
            //get(byteDestArray)方法是从byteBuffer往目的地读数据的api
            byteBuffer.get(tempBytes);
            log.info("读取到内容-tempBytes={}", new String(tempBytes));
        } catch (Exception e) {
            log.error("e");
            log.info("--------添加数据到缓冲区，虽说还不能从其中读取数据,但我就想试一下，上面就是强行读数据结果-------");
        }

        //没读一个byte,position向后移动一下，现在到limit位置了
        printByteBufferInfo(byteBuffer);

        log.info("--------添加数据到缓冲区后，flip之后，并且已经从中读完数据之后，再执行compact进入写模式-------");
        log.info("--------添加数据到缓冲区后，读完数据是指，读过的那些数据可以不care了，compact会重新整理空间-------");
        log.info("--------添加数据到缓冲区后，compact将读过的数据丢掉，没读的转移到数组最初的位置，我给你留到最前面-------");
        log.info("--------添加数据到缓冲区后，compact是啥意思？又可以执行写模式了-------");
        log.info("--------添加数据到缓冲区后，compact之后呢？position指向最初空位置，limit和capacity最大化-------");
        byteBuffer.compact();
        printByteBufferInfo(byteBuffer);

        log.info("--------添加数据到缓冲区后，flip之后，并且已经从中读完数据之后，clear一下呢，clear管你有没有读完，全都清除-------");
        log.info("--------添加数据到缓冲区后，flip之后，并且已经从中读完数据之后，compact还保留之前每读的数据呢-------");
        byteBuffer.clear();
        printByteBufferInfo(byteBuffer);
    }

    public static void printByteBufferInfo(ByteBuffer byteBuffer) {
        log.info("ByteBuffer position={}", byteBuffer.position());
        log.info("ByteBuffer limit={}", byteBuffer.limit());
        log.info("ByteBuffer capacity={}", byteBuffer.capacity());
        log.info("ByteBuffer mark field={}", getMarkField(byteBuffer));
        log.info("ByteBuffer mark object={}", byteBuffer.mark());

    }

    public static int getMarkField(ByteBuffer byteBuffer) {
        try {

            //这个地方很有意思
            //ByteBuffer.allocate()返回的实际是HeapByteBuffer类型
            //HeapByteBuffer->ByteBuffer->Buffer类层次
            //int mark字段定义在Buffer父父类中，要获取父父类中的private字段
            //所以这里getSuperClass()了2层，能不能直接用HeapByteBuffer.class.getSuperclass().getSuperclass()?
            //不能，HeapByteBuffer这个类modifier外部不能访问
            //侧面说明getDeclaredField()/getField()这些api只能访问自己的类成员（非父类成员）
            Field mark = byteBuffer.getClass().getSuperclass().getSuperclass().getDeclaredField("mark");
            //Field markField = HeapByteBuffer.class.getSuperclass().getSuperclass().getDeclaredField("mark");

            mark.setAccessible(true);
            int markValue = mark.getInt(byteBuffer);
            return markValue;
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        throw new RuntimeException();
    }
}
