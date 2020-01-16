package com.jayden.springpropagationtest.impl;

import com.jayden.springpropagationtest.IDbVisitor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 *
 */
@Repository
public class NoTxDbVisitor extends AbstractDbVisitor {

    @Override
    public void visitNoError() {
        jdbcTemplate.execute("insert into test(type) values('no tx no error 1')");
        jdbcTemplate.execute("insert into test(type) values('no tx no error 2')");
    }

    @Override
    public void visitErrorMiddle() {
        jdbcTemplate.execute("insert into test(type) values('no tx error middle 1')");
        if (true) {
            throw new RuntimeException();
        }
        jdbcTemplate.execute("insert into test(type) values('no tx error middle 2')");
    }

    @Override
    public void visitErrorLast() {
        jdbcTemplate.execute("insert into test(type) values('no tx error last 1')");
        jdbcTemplate.execute("insert into test(type) values('no tx error last 2')");
        if (true) {
            throw new RuntimeException();
        }
    }
}
