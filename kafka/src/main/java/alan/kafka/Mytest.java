package alan.kafka;

import java.util.Arrays;
import java.util.List;

public class Mytest {
    public static void main(String[] args) {
//        String s="abc,def";
        String s="abc";
        String[] array=s.split(",");
        List<String> list= Arrays.asList(array);
        for(String e:list)
        System.out.println(e);
    }
}
