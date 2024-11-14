package advance.job.iterator.mock;

import java.util.Iterator;

/**
 * Created by Dangdang on 2018/6/24.
 */
public class StudentIteratorDemo {
    public static void main(String[] args) {
        StudentList students = new StudentList(4);
        Iterator<Student> iterator = students.iterator();

        while (iterator.hasNext()) {
            Student student = iterator.next();
            System.out.println(student);
        }

        // 重复执行不会继续打印因为 当前 iterator cursor 已经到末尾，可以再次取一次 iterator(),将返回新的 iterator
        while (iterator.hasNext()) {
            Student student = iterator.next();
            System.out.println(student);
        }

        //跟上面的 iterator 对象无关，因为 for-each 方法糖会重新获取 iterator
        for (Student student : students) {
            System.out.println(student);
        }

        FakeStudentList fakeStudentList = new FakeStudentList();
        //若 FakeStudentList 对象未实现 Iterable 接口 for-each 方法糖使用会编译不通过
        /*
        for (Student student : fakeStudentList) {

        }*/

    }
}
