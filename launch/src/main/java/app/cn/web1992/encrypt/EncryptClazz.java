package app.cn.web1992.encrypt;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * Created by erbao.wang on 2016/3/17.
 *
 * @desc
 */
public class EncryptClazz {


    private  static String SOURCE_FILE_NAME = "launch/tmp/app-1.0.0.jar";
    private  static String DIST_FILE_NAME ="launch/tmp/app-1.0.0.zip";
    private  static String CLASS_FILE ="xx.class";
    private static final  String CLAZZ=".clazz";
    private static final  String CLASS=".class";

    private static final  String BUILD_DIR="launch/tmp/build/";


    private  static int KEY=3;

    public static void main(String[] args) throws Exception {

        if(args.length <1 ){
            println("args is less.");
            return;
        }
//        SOURCE_FILE_NAME =args[0];
//        new EncryptClazz().encrypt();
        new EncryptClazz().encryptJar2Zip();
    }

    /**
     * 加密class 文件
     * @throws IOException
     */
    public void encrypt() throws IOException {

        byte[] sourceFile=FileUtils.readFileToByteArray(new File(CLASS_FILE));

        for(int i=0;i<sourceFile.length-1;i++){
            sourceFile[i]=(byte)(sourceFile[i]+KEY);
        }
        String file_pix=CLASS_FILE.split("\\.")[0];
        FileUtils.writeByteArrayToFile(new File(file_pix + CLAZZ), sourceFile, true);
    }


    /**
     * 加密jar 文件
     * @throws IOException
     */
    public void encryptJar2Zip() throws IOException {

        File file=new File(SOURCE_FILE_NAME);
        if(!file.exists()){
            System.err.println("file not exit");
            return;
        }

        ZipFile zipFile=new ZipFile(file);
        Enumeration<?> enumeration=zipFile.entries();
        while (enumeration.hasMoreElements()){
            ZipEntry zipEntry= (ZipEntry)enumeration.nextElement();
            if(zipEntry.isDirectory()){
                continue;
            }
            String _name=zipEntry.getName();

            String newName=parseFilName2ClazzName(_name).replace(CLASS, CLAZZ);

            if(_name.endsWith(CLASS)){
                InputStream is = zipFile.getInputStream(zipEntry);
                int readLen=0;
                byte[] _b=new byte[1024];
                while ((readLen=is.read(_b))>0){
                    FileUtils.writeByteArrayToFile(new File((BUILD_DIR+newName)), _b, true);
                }
            }else{

            }
        }
    }

    private String genFileName(String clazzFiles){
     return    clazzFiles.replace(CLASS,CLAZZ);

    }

    private String parseFilName2ClazzName(String fileName){

        if(fileName.indexOf(92)>0){// 92=\
            return fileName.replace("\\",".");
        }
        if(fileName.indexOf(47)>0){ // 47=/
            return fileName.replace("/",".");
        }
        return fileName;
    }

    public static void println(Object obj){
        System.out.println(obj.toString());
    }
    public static void print(Object obj){
        System.out.print(obj.toString());
    }

}
