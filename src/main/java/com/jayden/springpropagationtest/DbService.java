package com.jayden.springpropagationtest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;

/**
 * @author Jayden Sun（089245）
 * @since 2020/1/16
 */
@Service
public class DbService implements ApplicationListener<ApplicationStartedEvent> {
    @Autowired
//    @Qualifier("noTxDbVisitor")
//    @Qualifier("txDefaultDbVisitor")
    @Qualifier("txNotSupportedDbVisitor")
    private IDbVisitor dbVisitor;

    @Override
    public void onApplicationEvent(ApplicationStartedEvent event) {
        dbVisitor.clearData();

        dbVisitor.visitNoError();
        try {
            dbVisitor.visitErrorMiddle();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            dbVisitor.visitErrorLast();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
