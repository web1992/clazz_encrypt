package app.cn.web1992.encrypt;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;

/**
 * Created by erbao.wang on 2016/3/17.
 *
 * @desc
 */
public class EncryptClazz {


    private  static String BUILD_DIR="build/distributions/";

    public static void main(String[] args) throws Exception {

        if(args.length >0 ){
            BUILD_DIR=args[0];
            System.out.println("BUILD_DIR="+BUILD_DIR);
        }
        new EncryptClazz().run();
    }

    public void run() throws IOException {
        // π§æﬂ¿‡≤‚ ‘
        if(StringUtils.isBlank("")){

        }
        FileUtils.cleanDirectory(new File("build"));
        BigDecimal a=NumberUtils.createBigDecimal("2.30");
        println(a);
    }

    public static void println(Object obj){
        System.out.println(obj.toString());
    }

}
