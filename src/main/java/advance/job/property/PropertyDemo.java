package advance.job.property;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * 该类练习java.util.Properties类
 */
public class PropertyDemo {
    public static void main(String[] args) throws IOException {
        Properties properties = new Properties();
        properties.setProperty("message.classname", "advance.reflect.course.News");
        properties.setProperty("city", "北京");
        properties.store(new FileOutputStream(new File("D:" + File.separator + "property.properties")), "properties demo");

        properties.load(new FileInputStream(new File("D:" + File.separator + "property.properties")));
        String classname = properties.getProperty("message.classname");
        System.out.println(classname);

    }
}
