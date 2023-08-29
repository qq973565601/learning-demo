package cn.lzx.data_convert.json.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author lzx
 * @since 2022/9/30
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Author {

    private Long id;

    private String name;

    private Integer age;

    private List<Book> books;
}
