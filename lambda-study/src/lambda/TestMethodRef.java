package lambda;

import org.junit.Test;

import java.util.function.Function;
import java.util.function.Supplier;

/**
 * 二、构造器引用
 * 格式：
 * ClassName::new
 * 注意：需要调用的构造器的参数列表要与函数式接口中抽象方法的参数列表保持一致
 *
 * 三、数组引用
 * Type::new
 */
public class TestMethodRef {
    //数组引用
    @Test
    public  void test03(){
        Function<Integer,String[]> fun = (x)->new String[x];
        String[] apply = fun.apply(10);
        System.out.println(apply.length);

        Function<Integer,String[]> funs = String[]::new;
        String[] apply1 = funs.apply(20);
        System.out.println(apply1.length);
    }

    //构造器引用
    @Test
    public void test01() {
        Supplier<Employee> sup = new Supplier<Employee>() {
            @Override
            public Employee get() {
                return new Employee();
            }
        };

        Supplier<Employee> sup1 = () -> new Employee();
        Supplier<Employee> sup2 = Employee::new;
        Employee employee = sup2.get();
        System.out.println(employee);
    }

    @Test
    public void test02() {
        Function<String, Employee> fun = new Function<String, Employee>() {
            @Override
            public Employee apply(String s) {
                return new Employee(s);
            }
        };
        Function<String, Employee> fun1 = (x) -> new Employee(x);

        Function<String, Employee> fun2 = Employee::new;

    }
}
