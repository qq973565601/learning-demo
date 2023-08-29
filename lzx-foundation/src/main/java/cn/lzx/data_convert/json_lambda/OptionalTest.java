package cn.lzx.data_convert.json_lambda;

import cn.lzx.data_convert.json.entity.Address;
import cn.lzx.data_convert.json.entity.Author;
import cn.lzx.data_convert.json.AutoGetLambdaJson;
import cn.lzx.data_convert.json.entity.User;
import org.junit.Test;

import java.util.Optional;

/**
 * Optional应用
 *
 * @author lzx
 * @since 2023/8/28
 */
public class OptionalTest {

    /**
     * 创建对象
     */
    @Test
    public void createObject() {
        Author author = AutoGetLambdaJson.jsonAuthor();
        // 入参可为null
        Optional<Author> optionalAuthor1 = Optional.ofNullable(author);
        // 入参不能为null
        Optional<Author> optionalAuthor2 = Optional.of(author);
        // 安全消费，如果不为null，则执行
        optionalAuthor1.ifPresent(author1 -> System.out.println(author1.getName()));
        // 不安全获取值
        Author author1 = optionalAuthor1.get();
        // 安全获取值:设置默认值，不为null时返回optional对象里的值，为null时返回默认值
        optionalAuthor1.orElseGet(Author::new);
        // 安全抛出异常
        optionalAuthor1.orElseThrow(() -> new RuntimeException("数据异常..."));
    }

    /**
     * 过滤
     */
    @Test
    public void filterTest() {
        Author author = AutoGetLambdaJson.jsonAuthor();
        Optional<Author> optionalAuthor = Optional.ofNullable(author);
        optionalAuthor.filter(author1 -> author1.getAge() > 12)
                .ifPresent(author1 -> System.out.println(author1.getName()));
    }

    /**
     * 判断是否存在
     */
    @Test
    public void isPresentTest() {
        Author author = AutoGetLambdaJson.jsonAuthor();
        Optional<Author> optionalAuthor = Optional.ofNullable(author);
        if (optionalAuthor.isPresent()) {
            System.out.println(optionalAuthor.get().getName());
        }
    }

    @Test
    public void toMapTest() {
        Author author = AutoGetLambdaJson.jsonAuthor();
        Optional<Author> optionalAuthor = Optional.ofNullable(author);
        optionalAuthor.map(Author::getBooks)
                .ifPresent(System.out::println);
    }

    @Test
    public void orElseTest() {
        User user = AutoGetLambdaJson.jsonUser();
        //String trim = user.getAddr().getCity().trim();
        String result = Optional.ofNullable(user)
                .map(User::getAddr)
                .map(Address::getCity)
                .map(String::trim)
                .orElse(null);
        System.out.println(result);
    }

}
