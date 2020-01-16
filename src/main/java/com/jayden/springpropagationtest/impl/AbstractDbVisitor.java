package com.jayden.springpropagationtest.impl;

import com.jayden.springpropagationtest.IDbVisitor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @author Jayden Sun（089245）
 * @since 2020/1/16
 */
public abstract class AbstractDbVisitor implements IDbVisitor {
    @Autowired
    protected JdbcTemplate jdbcTemplate;

    public void clearData() {
        jdbcTemplate.execute("truncate table test");
    }
}
