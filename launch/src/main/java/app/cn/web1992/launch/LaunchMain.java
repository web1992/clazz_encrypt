package app.cn.web1992.launch;


import app.cn.web1992.clazz.LaunchClazzLoafer;
import app.cn.web1992.encrypt.ZipCompressor;

import java.lang.reflect.Method;

/**
 * Created by erbao.wang on 2016/3/10.
 *
 * @desc
 */
public class LaunchMain {

    private final static String MAIN="cn.web1992.Main.clazz";
    private  static String SOURCE_FILE_NAME = "launch/tmp/app-1.0.0.zip";
    private  static String BUILD_FILE_NAME = "launch/tmp/build/cn";

    public static void main(String[] args) throws Exception {

        ClassLoader clazzLoader = new LaunchClazzLoafer(SOURCE_FILE_NAME);
        Class clazz = clazzLoader.loadClass(MAIN);


        Method mainMethod= clazz.getMethod("main", new Class[]{String[].class});

        mainMethod.invoke((Object)null,(Object)new String[]{" encrypt with LaunchMain "});

//        ZipCompressor zipCompressor=new ZipCompressor(SOURCE_FILE_NAME);
//
//        zipCompressor.compress(BUILD_FILE_NAME);
    }

}
