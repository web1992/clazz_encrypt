package cn.web1992;

/**
 * Created by erbao.wang on 2016/3/15.
 *
 * @desc
 */
public class Main {

    public static void main(String[] args) throws Exception {
        System.out.println("----------------");
        if (args.length > 0) {
            System.out.println("main run with args new " + args[0]);
        } else {
            System.out.println("main run with out args ");
        }
        System.out.println("----------------");
    }
}
