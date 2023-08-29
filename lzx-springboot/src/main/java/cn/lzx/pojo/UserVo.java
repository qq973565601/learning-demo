package cn.lzx.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author lzx
 * @since 2023/8/7
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String userName;

    private Integer age;

    private Integer isDeleted;
}
