package com.lzx.admin.service.serviceImp;

import com.lzx.admin.bean.Account;
import com.lzx.admin.mapper.AccountMapper;
import com.lzx.admin.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author lzx
 * @since 2022/11/2
 */
@Service
public class AccountServiceImp implements AccountService {

    @Autowired
    AccountMapper accountMapper;


    @Override
    public Account getAcctById(Long id) {
        return accountMapper.getAccount(id);
    }
}
