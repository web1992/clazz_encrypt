package app.cn.web1992.clazz;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;

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

    private  static int KEY=3;
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
        while (enumeration.hasMoreElements()){
            ZipEntry zipEntry= (ZipEntry)enumeration.nextElement();
            if(zipEntry.isDirectory()){
                continue;
            }
            String _name=zipEntry.getName();
            if(_name.endsWith(CLASS)){
//                println(_name);
                InputStream is = zipFile.getInputStream(zipEntry);
                byte[] _b =loadClazzByte(is);

                fileMap.put(_name,_b);
            }
        }
    }


    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        if(StringUtils.isBlank(name)){
            StringUtils.isBlank(name);
            return super.findClass(name);
        }
        if(!name.endsWith(CLAZZ)){
            return super.findClass(name);
        }

        byte[] clazz=null;
        try {
            clazz= this.fileMap.get(name);
            String _name=parseFilName2ClazzName(name);
            Class<?> c=  defineClass(_name,clazz,0,clazz.length);
            if(null == c){
                throw new ClassNotFoundException(name);
            }
            return c;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return super.findClass(name);
    }

    private byte[] loadClazzByte(String filePath) throws IOException {
        byte[] _byte=FileUtils.readFileToByteArray(new File(filePath));
        int i=0;
        for(byte _b:_byte){
            _byte[i]=(byte)(_b-KEY);
            i++;
        }
        return _byte;
    }
    private byte[] loadClazzByte(InputStream is) throws IOException {
        int len=0;
        // 需要根据文件的大小，动态的改变_tmp的大小，
        // 如果_tmp的大小放不下数据，System.arraycopy 时，出现数组越界异常
        byte[] _array=new byte[1024];
        byte[] _tmp=new byte[1024];
        int beginPos=0;
        while ((len=is.read(_array))!=-1){
            System.arraycopy(_array,0,_tmp,beginPos,len);
            beginPos+=len+1;
        }
        return _tmp;
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
