package com.fang.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 控制器
 *
 * @author fwj
 * @date 2019-04-24 17:02
 **/
@RestController
public class TransactionController {
    @Autowired
    @Qualifier("primaryJdbcTemplate")
    private JdbcTemplate primaryJdbcTemplate;
    @Autowired
    @Qualifier("secondJdbcTemplate")
    private JdbcTemplate secondJdbcTemplate;

    @Transactional(rollbackFor = RuntimeException.class)
    @GetMapping("/test")
    public String Test(@RequestParam String name) {
        primaryJdbcTemplate.execute("insert into t_transaction (name) values ('" + name + "');");
        int a = 1 / 0;
        primaryJdbcTemplate.execute("insert into t_transaction (name) values ('" + name + "2');");
        return "success";
    }
}
