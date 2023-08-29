package cn.lzx.data_convert.json_lambda;

import cn.lzx.data_convert.json.entity.Author;
import cn.lzx.data_convert.json.entity.Book;
import cn.lzx.data_convert.json.AutoGetLambdaJson;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Stream流应用
 *
 * @author lzx
 * @since 2022/9/30
 */
public class StreamTest {

    @Test
    public void filterTest() {
        List<Author> authors = AutoGetLambdaJson.jsonAuthors();
        // 获取所有name长度大于1的实例打印
        authors.stream()
                .filter(author -> author.getName().length() > 1)
                .forEach(author -> System.out.println(author.getName()));
    }

    @Test
    public void mapTest() {
        List<Author> authors = AutoGetLambdaJson.jsonAuthors();
        // 打印所有age并且加1
        authors.stream()
                .map(Author::getAge)
                .map(age -> age + 1)
                .forEach(System.out::println);
    }

    @Test
    public void distinctTest() {
        List<Author> authors = AutoGetLambdaJson.jsonAuthors();
        // 获取所有name且不重复
        authors.stream()
                .distinct()
                .forEach(author -> System.out.println(author.getName()));
    }

    @Test
    public void sortTest() {
        List<Author> authors = AutoGetLambdaJson.jsonAuthors();
        // 对所有元素按照年龄降序排序，并且不能有重复元素
        authors.stream()
                .distinct()
                .sorted((o1, o2) -> o2.getAge() - o1.getAge())
                .forEach(author -> System.out.println(author.getAge()));
    }

    @Test
    public void limitTest() {
        List<Author> authors = AutoGetLambdaJson.jsonAuthors();
        authors.stream()
                .distinct()
                .limit(2)
                .forEach(author -> System.out.println(author.getName()));
    }

    @Test
    public void skipTest() {
        List<Author> authors = AutoGetLambdaJson.jsonAuthors();
        authors.stream()
                .distinct()
                .skip(1)
                .forEach(author -> System.out.println(author.getName()));
    }

    @Test
    public void flatMapTest() {
        List<Author> authors = AutoGetLambdaJson.jsonAuthors();
        // 打印所有book名称并去重
        authors.stream()
                .flatMap(author -> author.getBooks().stream())
                .distinct()
                .forEach(book -> System.out.println(book.getName()));

        // 打印所有数据的所有分类，要求对分类进行去重，，不能出现这种格式：哲学，爱情
        authors.stream()
                .flatMap(author -> author.getBooks().stream())
                .distinct()
                .flatMap(book -> Arrays.stream(book.getCategory().split("，")))
                .distinct()
                .forEach(System.out::println);
    }

    @Test
    public void foreachTest() {
        List<Author> authors = AutoGetLambdaJson.jsonAuthors();
        authors.stream()
                .map(Author::getName)
                .forEach(System.out::println);
    }

    @Test
    public void countTest() {
        List<Author> authors = AutoGetLambdaJson.jsonAuthors();
        long count = authors.stream()
                .flatMap(author -> author.getBooks().stream())
                .distinct()
                .count();
    }

    @Test
    public void maxOrMinTest() {
        List<Author> authors = AutoGetLambdaJson.jsonAuthors();
        Optional<Integer> max = authors.stream()
                .flatMap(author -> author.getBooks().stream())
                .map(Book::getScore)
                .max((o1, o2) -> o1 - o2);
    }

    @Test
    public void collectTest() {
        List<Author> authors = AutoGetLambdaJson.jsonAuthors();
        List<String> list = authors.stream()
                .map(Author::getName)
                .collect(Collectors.toList());
        Set<String> set = authors.stream()
                .flatMap(author -> author.getBooks().stream())
                .map(Book::getName)
                .collect(Collectors.toSet());
        Map<String, List<Book>> map = authors.stream()
                .distinct()
                .collect(Collectors.toMap(Author::getName, Author::getBooks));
    }

    @Test
    public void anyMatchTest() {
        List<Author> authors = AutoGetLambdaJson.jsonAuthors();
        boolean b = authors.stream()
                .anyMatch(author -> author.getAge() > 29);
    }

    @Test
    public void allMatchTest() {
        List<Author> authors = AutoGetLambdaJson.jsonAuthors();
        boolean b = authors.stream()
                .allMatch(author -> author.getAge() > 29);
    }

    @Test
    public void noneMatchTest() {
        List<Author> authors = AutoGetLambdaJson.jsonAuthors();
        boolean b = authors.stream()
                .noneMatch(author -> author.getAge() > 29);
    }

    @Test
    public void findAnyTest() {
        List<Author> authors = AutoGetLambdaJson.jsonAuthors();
        // 获取任意一个年龄大于18的元素，存在输出name
        Optional<Author> any = authors.stream()
                .filter(author -> author.getAge() > 18)
                .findAny();
        any.ifPresent(author -> System.out.println(author.getName()));
    }

    @Test
    public void findFirstTest() {
        List<Author> authors = AutoGetLambdaJson.jsonAuthors();
        // 获取任意一个年龄大于18的元素，存在输出name
        Optional<Author> first = authors.stream()
                .sorted((o1, o2) -> o1.getAge() - o2.getAge())
                .findFirst();
        first.ifPresent(author -> System.out.println(author.getName()));
    }

    @Test
    public void reduceTest() {
        List<Author> authors = AutoGetLambdaJson.jsonAuthors();
        // 年龄求和
        Integer reduce = authors.stream()
                .map(Author::getAge)
                .distinct()
                .reduce(0, (result, element) -> result + element);
        System.out.println(reduce);
    }

}
