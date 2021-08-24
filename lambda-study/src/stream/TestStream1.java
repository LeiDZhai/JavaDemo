package stream;

import lambda.Employee;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * 一、Stream的三个操作步骤
 * 1.创建Stream
 * 2.中间操作
 * 3.终止操作(终端操作)
 */
public class TestStream1 {

    //创建Stream
    @Test
    public void test1(){
        //1.可以通过Collection系列集合提供的stream()串行流或parallelStream()并行流
        List<String> list = new ArrayList<>();
        Stream<String> stream = list.stream();
        //2.通过Arrays中的静态方法stream()获取数组流
        Employee[] employees = new Employee[10];
        Stream<Employee> stream1 = Arrays.stream(employees);
        //3.通过Stream类中的静态方法 of()
        Stream<String> stream2 = Stream.of("aa", "bb", "ccc");

        //4.创建无限流
        //迭代
        Stream<Integer> iterate = Stream.iterate(0, (x) -> x + 2);
//        iterate.forEach(new Consumer<Integer>() {
//            @Override
//            public void accept(Integer integer) {
//                System.out.println(integer);
//            }
//        });
//        iterate.limit(10).forEach(System.out::println);

        //生成
        Stream.generate(()->Math.random()).limit(10).forEach(System.out::println);

    }
}
