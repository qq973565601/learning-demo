### JAVA基础

> **java三大特性**

1、封装：属性可以私有化

- public：是公共的，被public所修饰的成员可以在任何类中都能被访问到。
- protected：所修饰的成员会被位于同一package中的所有类访问到。同时，被protected所修饰的成员也能被该类的所有子类继承下来。
- default：同一package中的所有类都能访问。被default所修饰的成员只能被该类所在同一个package中的子类所继承下来。
- private：是私有的，即只能在当前类中被访问到。

2、继承：子类继承父类，继承属性，重写方法

3、多态：父类引用指向子类对象(向上转型)

> **数据类型**

- 基础数据类型：byte，short，int，long，float，double，boolean，char
- 引用数据类型：string

> **static，final关键字**

static：可以修饰方法和变量，以及内部类，不能修饰普通类。方便在没有创建对象的情况下来进行调用（方法/变量）

final：可以用来修饰引用、方法和类。

- 引用为基本数据类型，则该引用为常量，如果引用为引用数据类型，比如对象、数组，则该对象、数组本身可以修改，但指向该对象或数组的地址的引用不能修改。
- 修饰方法时，这个方法将成为最终方法，无法被子类重写。但是，该方法仍然可以被继承。
- 修饰类时，该类成为最终类，无法被继承。

> **类与接口**

抽象类和接口：抽象类是用来捕捉子类的通用特性的。接口是抽象方法的集合。

接口和抽象类都不能实例化
都位于继承的顶端，用于被其他实现或继承
都包含抽象方法，其子类都必须覆写这些抽象方法

> **== 和 equals 的区别是什么**

- **==** : 它的作用是判断两个对象的地址是不是相等。即，判断两个对象是不是同一个对象。(基本数据类型 == 比较的是值，引用数据类型 == 比较的是内存地址)。

- **equals()** : 它的作用也是判断两个对象是否相等。覆盖 equals() 方法来对比两个对象的内容相等。

> IO流

1. InputStream/Reader: 所有的输入流的基类，前者是字节输入流，后者是字符输入流。
2. OutputStream/Writer: 所有输出流的基类，前者是字节输出流，后者是字符输出流。

> 反射

JAVA反射机制是在运行状态中，对于任意一个类，都能够知道这个类的所有属性和方法；对于任意一个对象，都能够调用它的任意一个方法和属性；这种动态获取的信息以及动态调用对象的方法的功能称为java语言的反射机制

```java
// 获取反射的三种方法
public class Get {
    public static void main(String[] args) throws ClassNotFoundException {
        // 通过new对象实现反射机制
        Students stu = new Students();
        Class<? extends Students> aClass = stu.getClass();
        System.out.println(aClass.getName());
        // 通过路径实现反射机制
        Class<?> bClass = Class.forName("cn.lzx.lzxbasics.reflex.Students");
        System.out.println(bClass.getName());
        // 通过类名实现反射机制
        Class<Students> cClass = Students.class;
        System.out.println(cClass.getName());
    }
}
```



> String

String 底层就是一个 char 类型的数组

String有哪些特性

- 不变性：String 是只读字符串，是一个典型的 immutable 对象，对它进行任何操作，其实都是创建一个新的对象，再把引用指向该对象。不变模式的主要作用在于当一个对象需要被多线程共享并频繁访问时，可以保证数据的一致性。
- 常量池优化：String 对象创建之后，会在字符串常量池中进行缓存，如果下次创建同样的对象时，会直接返回缓存的引用。
- final：使用 final 来定义 String 类，表示 String 类不能被继承，提高了系统的安全性。

常用方法都有那些

- indexOf()：返回指定字符的索引。
- charAt()：返回指定索引处的字符。
- replace()：字符串替换。
- trim()：去除字符串两端空白。
- split()：分割字符串，返回一个分割后的字符串数组。
- getBytes()：返回字符串的 byte 类型数组。
- length()：返回字符串长度。
- toLowerCase()：将字符串转成小写字母。
- toUpperCase()：将字符串转成大写字符。
- substring()：截取字符串。
- equals()：字符串比较。

String和StringBuffer、StringBuilder的区别是什么

> 集合

