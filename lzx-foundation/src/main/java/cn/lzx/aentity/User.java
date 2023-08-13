package cn.lzx.aentity;

import lombok.Data;

import java.sql.Timestamp;

/**
 * @author lzx
 * @since 2023/8/13
 */
@Data
public class User {

    private Long id;

    private String userName;

    private Integer age;

    private String address;

    private Timestamp creatTime;
}
