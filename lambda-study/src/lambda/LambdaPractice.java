package lambda;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

public class LambdaPractice {
    List<Employee> employeeList = Arrays.asList(
            new Employee("张三",22,2222.2),
            new Employee("李四",33,3333.3),
            new Employee("王五",44,4444.4),
            new Employee("赵六",55,5555.5),
            new Employee("天气",33,5555.5)
    );
    @Test
    public void test01(){
//        Collections.sort(employeeList, new Comparator<Employee>() {
//            @Override
//            public int compare(Employee o1, Employee o2) {
//                if(o1.getAge()==o2.getAge()){
//                    return o1.getName().compareTo(o2.getName());
//                }else{
//                    return Integer.compare(o1.getAge(),o2.getAge());
//                }
//            }
//        });

        Collections.sort(employeeList, (o1, o2) -> {
            if (o1.getAge() == o2.getAge()) {
                return o1.getName().compareTo(o2.getName());
            } else {
                return Integer.compare(o1.getAge(), o2.getAge());
            }
        });

        for (int i = 0; i < employeeList.size(); i++) {
            System.out.println(employeeList.get(i));
        }
    }

    @Test
    public void test02(){
        System.out.println(this.getValue("aaaaaaaaaa"));

    }

    public String getValue(String str){
//        Function<String, String> function = new Function<String, String>() {
//            @Override
//            public String apply(String s) {
//                return s.toUpperCase();
//            }
//        };
        //        return function.apply(str);
        Function<String,String> fun = s -> s.toUpperCase();
        return  fun.apply(str);

    }

}
