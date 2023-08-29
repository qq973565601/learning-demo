package cn.lzx.data_convert.json;

import cn.lzx.data_convert.json.entity.Address;
import cn.lzx.data_convert.json.entity.Author;
import cn.lzx.data_convert.json.entity.Book;
import cn.lzx.data_convert.json.entity.User;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Lambda测试数据
 *
 * @author lzx
 * @since 2023/8/29
 */
public class AutoGetLambdaJson {

    public static List<Author> jsonAuthors() {
        Book b1 = new Book(1L, "爱情", "红楼梦", 96);
        Book b2 = new Book(2L, "战争", "三国演义", 94);
        Book b3 = new Book(3L, "江湖", "水浒传", 92);
        Book b4 = new Book(4L, "玄幻", "西游记", 95);
        List<Book> bs1 = new ArrayList<>();
        bs1.add(b1);
        bs1.add(b2);
        List<Book> bs2 = new ArrayList<>();
        bs2.add(b3);
        bs2.add(b4);
        List<Book> bs3 = new ArrayList<>();
        bs3.add(b1);
        bs3.add(b4);
        Author author1 = new Author(1L, "张三", 18, bs1);
        Author author2 = new Author(2L, "李四", 28, bs1);
        Author author3 = new Author(3L, "王五", 38, bs1);
        List<Author> authorList = new ArrayList<>();
        authorList.add(author1);
        authorList.add(author2);
        authorList.add(author3);
        return authorList;
    }

    public static Author jsonAuthor() {
        Book b1 = new Book(1L, "爱情", "红楼梦", 96);
        List<Book> bs1 = new ArrayList<>();
        bs1.add(b1);
        return new Author(1L, "张三", 18, bs1);
    }

    public static User jsonUser() {
        Address address = new Address("合肥");
        return new User(1L, "林宗星", 28, address, Timestamp.valueOf("1900-01-01 00:00:00"));
    }
}

