package advance.stream;


import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Arrays.asList;

public class StreamDemo {
    public static void main(String[] args) {
        //stream转成一个List
        List<String> collect = Stream.of("a", "b").collect(Collectors.toList());
        System.out.println(collect.getClass().getName());

        //多个list源构造成一个Stream
        List<String> collect1 = Stream.of(asList("a", "b"), asList("c", "d")).flatMap(item -> item.stream()).collect(Collectors.toList());
        System.out.println(collect1);

        //获取最值
        Integer min = Stream.of(10, 2, 3, 5, 1).min(Comparator.comparing(item -> item)).get();
        System.out.println(min);

        //reduce求和
        Integer sum = Stream.of(3, 2, 10).reduce(0, (total, item) -> total + item);
        System.out.println(sum);

        SchoolEntity schoolEntity = new SchoolEntity();
        List<String> nameList = schoolEntity.classEntityList
                .stream()
                .flatMap(clazz -> clazz.studentEntityList.stream())
                .filter(student -> student.name.startsWith("qian"))
                .peek(item -> System.out.println(item.name))
                .map(student -> student.name)
                .collect(Collectors.toList());

        System.out.println(nameList);

        //demo1:lambda表达式是Runnable接口实例, Runnable接口是用@FunctionInterface修饰的
        Runnable runnable = () -> System.out.println("Hi");

        //demo2: lambda表达式是Consumer接口的实例，Consumer接口也用@FunctionInterface修饰的
        //consumer接口有一个接收一个参数的accept方法
        //用lambda一个缺点是：不能直观的表明lambda实现的是哪个接口，接口的方法名是啥一下子看不清
        Consumer<String> consumer1 = item -> System.out.println(item);

        //demo3: 同2一个含义，方法引用方式替代 lambda表达式，
        Consumer<String> consumer2 = System.out::println;


        Arrays.asList("a", "b").forEach(item -> System.out.println(item));
        Arrays.asList("a", "b").forEach(System.out::println);

        //ThreadLocal<StreamDemo> demo = ThreadLocal.withInitial(() -> return 2))

        int sumAge = schoolEntity.classEntityList.stream()
                .flatMap(clazz -> clazz.studentEntityList.stream())
                .mapToInt(student -> student.age)
                .sum();

        System.out.println("sum age = " + sumAge);

        Set<String> nameSet = schoolEntity.classEntityList.stream()
                .flatMap(clazz -> clazz.studentEntityList.stream())
                .map(StudentEntity::getName)
                .collect(Collectors.toSet());

        System.out.println(nameSet);

        Integer sum2 = schoolEntity.classEntityList.stream()
                .flatMap(clazz -> clazz.studentEntityList.stream())
                .collect(Collectors.summingInt(StudentEntity::getAge));
        System.out.println(sum2);

        Double averageAge = schoolEntity.classEntityList.stream()
                .flatMap(clazz -> clazz.studentEntityList.stream())
                .collect(Collectors.averagingInt(StudentEntity::getAge));

        System.out.println(averageAge);

        //按name进行分组,value是默认stream的对象
        Map<String, List<StudentEntity>> nameMap = schoolEntity.classEntityList.stream()
                .flatMap(clazz -> clazz.studentEntityList.stream())
                .collect(Collectors.groupingBy(StudentEntity::getName));

        System.out.println(nameMap);

        //按name进行分组，value是name对应的元素的个数，即student的个数（一个统计值）
        Map<String, Long> nameCount = schoolEntity.classEntityList.stream()
                .flatMap(clazz -> clazz.studentEntityList.stream())
                .collect(Collectors.groupingBy(StudentEntity::getName, Collectors.counting()));
        System.out.println(nameCount);

        //按name进行分组，value是stream元素student的age的集合list
        Map<String, List<Integer>> nameAgeList = schoolEntity.classEntityList.stream()
                .flatMap(clazz -> clazz.studentEntityList.stream())
                .collect(Collectors.groupingBy(StudentEntity::getName, Collectors.mapping(StudentEntity::getAge, Collectors.toList())));

        System.out.println(nameAgeList);
    }

    private static class SchoolEntity {
        List<ClassEntity> classEntityList = new ArrayList<>();
        {
            classEntityList.add(new ClassEntity());
            classEntityList.add(new ClassEntity());

        }
    }

    private static class ClassEntity {
        List<StudentEntity> studentEntityList = new ArrayList<>();
        {
            studentEntityList.add(new StudentEntity("qian kai", 1));
            studentEntityList.add(new StudentEntity("li si", 2));
            studentEntityList.add(new StudentEntity("qian kai", 3));
            studentEntityList.add(new StudentEntity("wei si", 4));
        }
    }

    private static class StudentEntity {
        String name;

        int age;

        public StudentEntity(String name) {
            this.name = name;
            age = 10;
        }

        public StudentEntity(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public int getAge() {
            return age;
        }

        @Override
        public String toString() {
            return "StudentEntity{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }


}

