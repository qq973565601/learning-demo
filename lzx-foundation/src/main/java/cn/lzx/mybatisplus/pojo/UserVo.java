package cn.lzx.mybatisplus.pojo;

import cn.lzx.mybatisplus.enums.SexEnum;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author lzx
 * @since 2023/8/7
 */
@Data
public class UserVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("user_name")
    private String name;

    private Integer age;

    private String email;

    private SexEnum sex;

    private Timestamp createTime;

    @TableLogic
    private Integer isDeleted;

}
