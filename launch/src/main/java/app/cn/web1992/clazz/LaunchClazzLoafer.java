package app.cn.web1992.clazz;

import app.cn.web1992.encrypt.EncryptClazz;
import org.apache.commons.lang.StringUtils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * Created by erbao.wang on 2016/3/15.
 *
 * @desc
 */
public class LaunchClazzLoafer extends ClassLoader {

    private static final  int SIZE=1024;
    private static final  String CLAZZ=".clazz";
    private static final  String CLASS=".class";
    private String fileName;

    /**
     * key:  文件路径
     * value: 类的二级制文件
     *
     */
    private Map<String,byte[]> fileMap=new HashMap<String,byte[]>();

    public LaunchClazzLoafer(String fileName) throws IOException {
        this.fileName=fileName;
        initFileMap();
    }

    private void initFileMap() throws IOException {
        File file=new File(fileName);
        if(!file.exists()){
            System.err.println("file not exit");
            return;
        }
        ZipFile zipFile=new ZipFile(file);
        Enumeration<?>  enumeration=zipFile.entries();
        // 存在class 字节文件，
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        while (enumeration.hasMoreElements()){
            ZipEntry zipEntry= (ZipEntry)enumeration.nextElement();
            if(zipEntry.isDirectory()){
                continue;
            }
            String _name=zipEntry.getName();
            if(_name.endsWith(CLAZZ)){
                println(_name);
                InputStream is = zipFile.getInputStream(zipEntry);
                byte[] _b =new byte[SIZE];
                // 把读取的文件放入到 byte 数组中
                bout.reset();
                int len=0;
                while ((len=is.read(_b))!=-1){
                    bout.write(_b,0,len);
                }
                String clazzName=parseFilName2ClazzName(_name);
                fileMap.put(clazzName,bout.toByteArray());
            }
        }
    }


    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] clazzInMap=null;
        try {


            clazzInMap= this.fileMap.get(name);
            String _a=name.split(CLAZZ)[0];
            byte[] cl= EncryptClazz.decipherByte(clazzInMap);
            Class<?> c=  defineClass(_a,cl,0,cl.length);
            if(null == c){
                throw new ClassNotFoundException(name);
            }
            return c;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return super.findClass(name);
    }

    /**
     * 把文件路径，转化为 包名+类名
     * @param fileName
     * @return
     */
    private String parseFilName2ClazzName(String fileName){

        if(fileName.indexOf(92)>0){// 92=\
            return fileName.replace("\\",".");
        }
        if(fileName.indexOf(47)>0){ // 47=/
            return fileName.replace("/",".");
        }
        return fileName.split(CLASS)[0];
    }

    @Override
    protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
        return super.loadClass(name, resolve);
    }

    public static void println(Object obj){
        System.out.println(obj.toString());
    }
}
