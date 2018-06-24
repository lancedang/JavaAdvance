package advance.job.iterator.mock;

import java.util.Iterator;

/**
 * Created by Dangdang on 2018/6/24.
 * 1. 现在定义一个集合对象 StudentList, 其中通过 Student 数组存放若干 student 对象, 数据结构的知识点-封装，需要理解 </br>
 * 2. 因为集合类实现了 Iterable 接口，故内部需自定义实现 Iterator 接口的 SelfIterator 类来实现 Iterable 中的 iterator() 方法
 */
public class StudentList implements Iterable<Student> {

    /**
     * 定义 List 长度
     */
    private int size;

    /**
     * 某个集合对象，内部存储多个元素的形式，此处用 Student[] 数组形式存储
     */
    private Student[] students;

    /**
     * 构造函数创建一个 StudentList 对象，且初始化students 数组、并填充其中元素
     *
     * @param length
     */
    public StudentList(int length) {
        this.size = length;
        this.students = new Student[length];
        for (int j = 0; j < length; j++) {
            Student student = new Student(j, "学生-" + String.valueOf(j));
            students[j] = student;
        }
    }

    @Override
    public Iterator<Student> iterator() {
        //返回自定义的 StudentIterator 对象，注意每次返回的都是 new 一个新 StudentIterator，这样可以从新开始遍历
        return new StudentIterator();
    }

    /**
     * 内部私有 Iterator 类，实现 Iterator 接口，用于遍历当前 StudentList 集合
     */
    private class StudentIterator implements Iterator<Student> {

        //记录当前集合访问的位置，遍历数组的 cursor
        private int index;

        @Override
        public boolean hasNext() {
            return index < size;
        }

        @Override
        public Student next() {
            //return students[index++];
            //return this.** 编译不通过
            return StudentList.this.students[index++];
        }
    }
}
