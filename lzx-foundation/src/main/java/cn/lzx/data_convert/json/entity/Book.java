package cn.lzx.data_convert.json.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author lzx
 * @since 2022/9/30
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Book {

    private Long id;

    private String category;

    private String name;

    private Integer score;
}
