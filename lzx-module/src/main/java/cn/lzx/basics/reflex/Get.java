package cn.lzx.basics.reflex;

/**
 * 获取反射的三种方法
 *
 * @author lzx
 */
public class Get {
    public static void main(String[] args) throws ClassNotFoundException {
        // 通过new对象实现反射机制
        Students stu = new Students();
        Class<? extends Students> aClass = stu.getClass();
        System.out.println(aClass.getName());
        // 通过路径实现反射机制
        Class<?> bClass = Class.forName("cn.lzx.basics.reflex.Students");
        System.out.println(bClass.getName());
        // 通过类名实现反射机制
        Class<Students> cClass = Students.class;
        System.out.println(cClass.getName());
    }
}
