package cn.lzx.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Getter;

/**
 * @author lzx
 * @since 2023/8/11
 */
@Getter
public enum SexEnum {

    MALE(1, "man"),
    FEMALE(2, "woman");

    // 将注解所标识的属性的值存储到数据库中
    @EnumValue
    private Integer sex;
    private String sexName;

    SexEnum(Integer sex, String sexName) {
        this.sex = sex;
        this.sexName = sexName;
    }
}
