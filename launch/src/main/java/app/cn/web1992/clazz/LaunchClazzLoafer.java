package app.cn.web1992.clazz;

/**
 * Created by erbao.wang on 2016/3/15.
 *
 * @desc
 */
public class LaunchClazzLoafer extends ClassLoader {
    public LaunchClazzLoafer() {
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        return super.findClass(name);
    }

    @Override
    protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
        return super.loadClass(name, resolve);
    }
}
