package app.cn.web1992.encrypt;

/**
 * Created by erbao.wang on 2016/3/17.
 *
 * @desc
 */
public class EncryptClazz {


    private final static int KEY=3;

    public static  synchronized byte[] encryptByte(final byte[] _byte){
        byte[] _newByte=new byte[_byte.length];
        int index=0;
        for(byte _b:_byte){
            _newByte[index]=(byte)(_b+KEY);
            index++;
        }
        return _newByte;
    }

    public static  synchronized byte[] decipherByte(final byte[] _byte){
        byte[] _newByte=new byte[_byte.length];
        int index=0;
        for(byte _b:_byte){
            _newByte[index]=(byte)(_b-KEY);
            index++;
        }
        return _newByte;
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

}
