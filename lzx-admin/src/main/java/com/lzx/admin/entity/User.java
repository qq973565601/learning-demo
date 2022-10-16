package com.lzx.admin.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author lzx
 * @since 2022/10/13
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {
    String userName;
    String password;
}
