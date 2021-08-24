package stream;

import lambda.Employee;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 * 中间操作
 */
public class TestStreamAPI2 {
    List<Employee> employeeList = Arrays.asList(
            new Employee("张三", 22, 2222.2),
            new Employee("李四", 33, 3333.3),
            new Employee("王五", 44, 4444.4),
            new Employee("赵六", 55, 5555.5),
            new Employee("天气", 33, 5555.5),
            new Employee("天气", 33, 5555.5),
            new Employee("天气", 33, 5555.5),
            new Employee("天气", 33, 5555.5)
    );
    /**
     * 映射
     * map---接收Lambda,将元素转换成其他形式或提取信息。接受一个函数作为参数，该函数会被应用到每个元素上，并将其映射为一个新的元素，
     * flatMap----接受一个函数作为参数，将流中的每个值都换成另一个流，然后把所有流连接成一个流
     */
    @Test
    public void test5(){
        List<String> list = Arrays.asList("aaa","bbb","ccc","ddd","eee");
        list.stream()
                .map(str->str.toUpperCase())
                .forEach(System.out::println);
        System.out.println("-------------------");
        employeeList.stream()
                .distinct()
                .map(Employee::getName)
                .forEach(System.out::println);
        System.out.println("-------------------");

        Stream<Stream<Character>> streamStream = list.stream()
//                .map(s -> TestStreamAPI2.filterCharacter(s));
//                .map(new Function<String, Stream<Character>>() {
//                    @Override
//                    public Stream<Character> apply(String s) {
//                        return TestStreamAPI2.filterCharacter(s);
//                    }
//                });
                .map(TestStreamAPI2::filterCharacter);
        streamStream.forEach((sm)->{
            sm.forEach(System.out::println);
        });
        System.out.println("-------------------");
        TestStreamAPI2.filterCharacter("aaaa").forEach(System.out::println);

    }

    public static Stream<Character> filterCharacter(String str){
        List<Character> list = new ArrayList<>();
        for (Character ch : str.toCharArray()) {
            list.add(ch);
        }
        return list.stream();
    }
    /**
     * 多个中间操作可以连接起来形成一个流水线，除非流水线上触发终止操作，否则中间操作不会执行任何的处理！而在终止操作时一次性全部处理，称为"惰性求值"
     * <p>
     * 筛选与切片
     * filter---接收Lambda,从流中排除某些元素
     * limit---截断流，使其元素不超过给定数量
     * skip(n)--跳过元素，返回一个扔掉了前n个元素的流。若流中元素不足n个，则返回一个空流，与limit(n) 互补
     * distinct---筛选，通过流所生成元素的hashCode()和equals()去除重复元素
     */
    // 内部迭代：迭代操作由Stream API完成
    @Test
    public void test1() {
        Stream<Employee> employeeStream = employeeList.stream().filter(employee -> {
            System.out.println("进行到这里了");
            return employee.getAge() > 10;
        });
        employeeStream.forEach(System.out::println);
//        employeeStream.forEach(new Consumer<Employee>() {
//            @Override
//            public void accept(Employee employee) {
//                System.out.println(employee);
//            }
//        });

        List<Employee> employeeList = new ArrayList<>();
    }


    //外部迭代
    @Test
    public void test02() {
        Iterator<Employee> iterator = employeeList.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    @Test
    public void test03() {
        employeeList.stream()
                .filter((employee) -> {
                            System.out.println("短路");
                            return employee.getSalary() > 1000;
                        }
                )
                .limit(2)
                .forEach(System.out::println);
    }

    @Test
    public void tst04() {
        employeeList.stream()
                .filter(employee -> {
//                    System.out.println("短路");
                    return employee.getAge() > 30;
                })
                .skip(2)
                .distinct()
                .forEach(System.out::println);
    }
}
