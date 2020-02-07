package util;

import java.io.InputStream;
import java.net.URL;
import java.util.List;

import com.alibaba.fastjson.JSON;


/**
 * Created by qiankai02 on 2017/11/9.
 */
public class TestHelper {

    //return object from JSON string
    public static <T> T getTestObject(String rs, Class<T> clazz) throws Exception {
        return JSON.parseObject(readSourceFile(rs), clazz);
    }

    //return object list from JSON array string
    public static <T> List<T> getTestList(String rs, Class<T> clazz) throws Exception {
        return JSON.parseArray(readSourceFile(rs), clazz);
    }

    //return JSON string from json file
    public static String readSourceFile(String rs) throws Exception {
        Class<TestHelper> testHelperClass = TestHelper.class;
        ClassLoader classLoader = testHelperClass.getClassLoader();
        URL resource = classLoader.getResource(rs);
        InputStream inputStream = resource.openStream();
        int size = inputStream.available();

        //URI uri = resource.toURI();
        //byte[] bytes = Files.readAllBytes(Paths.get(uri));
        byte[] bytes = new byte[size];
        inputStream.read(bytes);

        return new String(bytes, "UTF-8");
    }

}
