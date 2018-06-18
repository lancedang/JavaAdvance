package dang.advance.job.base64;

import org.apache.commons.codec.binary.Base64;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class Base64Demo {
    public static void main(String[] args) throws IOException {
        byte[] buffer = Base64.decodeBase64("");
        OutputStream out = new FileOutputStream("d://echart//test.png");
        out.write(buffer);
        out.flush();
    }
}
