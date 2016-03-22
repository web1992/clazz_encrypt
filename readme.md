1,执行main方法
-----------
    gradle run -q
    会执行 launch 项目的Main 方法
    注意：这的 settings.gradle 必须使用 include 而不是 includeFlat

2,小技巧
-----------
    gradle idea
    gradle eclipse
使用idea 插件生成idea相关的文件，这样在就可以找到相关的工具类
    如 StringUtils.isBlank("") 引用apache.commons.lang 工具类
    
3,其他
-----------
    http://commons.apache.org/ 工具类总结


4,jni 
---------
    linux 编译
    http://www.cnblogs.com/mandroid/archive/2011/06/15/2081093.html
    
    g++  -I/usr/local/java/include -I/usr/local/java/include/linux  -o libHello.so -g -shared -fPIC  jni_helloworldImpl.cpp 
