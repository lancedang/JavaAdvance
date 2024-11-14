package basic.copy;

import com.google.gson.Gson;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.*;

@Slf4j
@Data
public class Student implements Cloneable, Serializable {
    private int age;
    private String name;
    private Address address;
    private Book book;

    public static void main(String[] args) throws CloneNotSupportedException, IOException, ClassNotFoundException {
        //copyByClone();

        main2(null);
    }

    public static void main2(String[] args) throws CloneNotSupportedException, IOException, ClassNotFoundException {
        Student oldStudent = new Student();

        Address address = new Address();
        address.setAddress("1");

        oldStudent.age = 10;
        oldStudent.name = "xxx";
        oldStudent.address = address;

        Student newStudent = oldStudent.copyBySerialize();
        log.info("old age={} A.address={},name={}", oldStudent.age, oldStudent.address.getAddress(), oldStudent.name);
        log.info("new age={} A.address={},name={}", newStudent.age, newStudent.address.getAddress(), newStudent.name);

        address.setAddress("444");

        log.info("old age={} A.address={},name={}", oldStudent.age, oldStudent.address.getAddress(), oldStudent.name);
        log.info("new age={} A.address={},name={}", newStudent.age, newStudent.address.getAddress(), newStudent.name);


    }

    private static void copyByClone() throws CloneNotSupportedException {
        Student oldStudent = new Student();

        Address address = new Address();
        address.setAddress("上海");

        oldStudent.age = 10;
        oldStudent.name = "xxx";
        oldStudent.address = address;

        Student newStudent = (Student) oldStudent.clone();

        //复制后直接输出old,new
        log.info("old age={} A.id={},name={}", oldStudent.age, oldStudent.address.getAddress(), oldStudent.name);
        log.info("new age={} A.id={},name={}", newStudent.age, newStudent.address.getAddress(), newStudent.name);


        //修改old,看new的变化
        oldStudent.name = "yyy";
        oldStudent.age = 20;
        oldStudent.address.setAddress("北京");

        log.info("old age={} A.id={},name={}", oldStudent.age, oldStudent.address.getAddress(), oldStudent.name);
        log.info("new age={} A.id={},name={}", newStudent.age, newStudent.address.getAddress(), newStudent.name);
    }

    public Student copyBySerialize() throws IOException, ClassNotFoundException {
        //把this写入stream流
        ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(arrayOutputStream);
        objectOutputStream.writeObject(this);

        //再从stream取出构造成复制的对象
        ByteArrayInputStream arrayInputStream = new ByteArrayInputStream(arrayOutputStream.toByteArray());

        ObjectInputStream objectInputStream = new ObjectInputStream(arrayInputStream);
        Student newStudent = (Student) objectInputStream.readObject();

        return newStudent;
    }

    public Student copyByGson() {
        Gson gson = new Gson();

        Book book = new Book();
        book.setName("数学");

        Student student = new Student();
        student.book = book;

        return student;
    }
}

@Data
class Address implements Serializable {
    private String address;
}

@Data
class Book {
    private String name;
}