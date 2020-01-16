package com.jayden.springpropagationtest;

/**
 * @author Jayden Sun（089245）
 * @since 2020/1/16
 */
public interface IDbVisitor {
    void clearData();

    void visitNoError();

    void visitErrorMiddle();

    void visitErrorLast();
}
