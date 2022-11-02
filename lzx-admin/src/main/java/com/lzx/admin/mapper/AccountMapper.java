package com.lzx.admin.mapper;

import com.lzx.admin.bean.Account;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author lzx
 * @since 2022/11/2
 */
@Mapper
public interface AccountMapper {

    public Account getAccount(Long id);
}
