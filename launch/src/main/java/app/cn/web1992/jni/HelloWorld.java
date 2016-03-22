package app.cn.web1992.jni;


public class HelloWorld {

    static {
        System.loadLibrary("Hello");
    }

    public native void DisplayHello();

    public static void main(String[] args) {

        new HelloWorld().DisplayHello();
    }

}
