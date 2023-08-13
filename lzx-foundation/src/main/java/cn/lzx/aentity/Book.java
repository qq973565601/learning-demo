package cn.lzx.aentity;

import lombok.Data;

/**
 * @author lzx
 * @since 2022/9/30
 */
@Data
public class Book {

    private Long id;

    private String category;

    private String name;

    private Double score;

    private String introduction;
}
