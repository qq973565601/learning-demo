package cn.lzx.data_convert.json_lambda;

import cn.lzx.data_convert.json.AutoGetLambdaJson;
import cn.lzx.data_convert.json.entity.Author;
import org.junit.Test;

import java.util.List;
import java.util.function.Predicate;

/**
 * 函数式接口
 *
 * @author lzx
 * @since 2023/8/29
 */
public class FunctionInterfaceTest {

    /**
     * and连接，用于自定义lambda方法情景使用
     */
    @Test
    public void andTest() {
        List<Author> authors = AutoGetLambdaJson.jsonAuthors();
        authors.stream()
                .filter(new Predicate<Author>() {
                    @Override
                    public boolean test(Author author) {
                        return author.getAge() > 18;
                    }
                }.and(new Predicate<Author>() {
                    @Override
                    public boolean test(Author author) {
                        return author.getName().length() > 2;
                    }
                })).forEach(author -> System.out.println(author.getName()));
    }
}
