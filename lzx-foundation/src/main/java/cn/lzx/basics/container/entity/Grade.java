package cn.lzx.basics.container.entity;

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

    /**
     * 学号
     */
    private String stuNo;

    /**
     * 姓名
     */
    private String stuName;

    /**
     * 班级
     */
    private String stuClass;

    /**
     * 语文成绩
     */
    private Double chineseScore;

    /**
     * 数学成绩
     */
    private Double mathScore;

    /**
     * 英语成绩
     */
    private Double englishScore;

}
