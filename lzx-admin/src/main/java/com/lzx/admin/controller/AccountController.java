package com.lzx.admin.controller;

import com.lzx.admin.bean.Account;
import com.lzx.admin.service.serviceImp.AccountServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author lzx
 * @since 2022/11/2
 */
@Controller
public class AccountController {

    @Autowired
    AccountServiceImp accountServiceImp;

    @GetMapping("/acct")
    public Account getById(@RequestParam("id") Long id) {
        return accountServiceImp.getAcctById(id);
    }
}
