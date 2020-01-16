package com.jayden.springpropagationtest.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Propagation.NOT_SUPPORTED
 */
@Repository
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class TxNotSupportedDbVisitor extends AbstractDbVisitor {

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
