package com.man.studentcenter.model.service.email;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author ruary
 * @Version 1.0
 * @Describe To implement decorator design pattern
 **/
public interface Notifier {
    List<String> stringList = new ArrayList<>();
    List<String> assemble();
}
