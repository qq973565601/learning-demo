package cn.lzx.aentity;

import lombok.Data;

import java.awt.print.Book;
import java.util.List;

/**
 * @author lzx
 * @since 2022/9/30
 */
@Data
public class Author {

    private Long id;

    private String name;

    private String introduction;

    private Integer age;

    private List<Book> bookList;
}
