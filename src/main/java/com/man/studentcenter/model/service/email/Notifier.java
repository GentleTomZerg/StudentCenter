package com.man.studentcenter.model.service.email;

/**
 * @Author ruary
 * @Version 1.0
 * @Describe To implement decorator design pattern
 **/
public interface Notifier {
    void assemble();
    void send(String message);
}
