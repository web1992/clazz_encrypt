package com.web1992.app;

/**
 * Created by erbao.wang on 2016/3/10.
 *
 * @desc
 */
public class AppMain {
    public static void main(String[] args) {
        System.out.println("----------------");
        if (args.length > 0) {
            System.out.println("main run with args " + args[0]);
        } else {
            System.out.println("main run with out args ");
        }
        System.out.println("----------------");
    }
}
