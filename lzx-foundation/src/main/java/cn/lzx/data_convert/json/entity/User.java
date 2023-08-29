package cn.lzx.data_convert.json.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

/**
 * @author lzx
 * @since 2023/8/13
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class User {

    private Long id;

    private String userName;

    private Integer age;

    private Address addr;

    private Timestamp creatTime;
}
