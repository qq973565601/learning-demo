package cn.lzx.java_se.container.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 成绩实体类
 *
 * @author lzx
 * @since 2023/1/16
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Grade {

    private String stuNo;

    private String stuName;

    private String stuClass;

    private Double chineseScore;

    private Double mathScore;

    private Double englishScore;

}
