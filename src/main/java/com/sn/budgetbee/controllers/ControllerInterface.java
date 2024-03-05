package com.sn.budgetbee.controllers;

import java.util.List;

public interface ControllerInterface<T> {

    List<T> getAllElements();
    T getElementById(Integer id);

    T setElement(T t);

    T updateElement(T t);

    boolean deleteElementById(Integer id);
}
