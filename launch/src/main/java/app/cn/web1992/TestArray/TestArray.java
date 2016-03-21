package app.cn.web1992.TestArray;

import java.util.Objects;

/**
 * Created by erniu on 2016/3/20.
 */
public class TestArray {

    public static void main(String[] args) throws Exception {

        String[] s1=new String[]{"4","3","2","1"};
//        String[] s2=new String[]{};
        String[] s2=new String[4];
        String[] s3=new String[5];
        println(s1.length);
        println(s2.length);
        println(s3.length);
        println("---");
        System.arraycopy(s1, 0, s2, 0, s1.length);
        println(s2.length);
        println(s1.length);
        println("---");
        for(String _s:s1){
            println(_s);
        }
        println("---");
        for(String _s:s2){
            println(_s);
        }
    }
    public static void println(Object obj){
        System.out.println(obj.toString());
    }
}
