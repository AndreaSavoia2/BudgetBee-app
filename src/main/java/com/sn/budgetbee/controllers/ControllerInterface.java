package com.sn.budgetbee.controllers;

import com.sn.budgetbee.entities.User;

import java.util.List;

public interface ControllerInterface<T> {

    List<T> GetAllElements();
    T GetElementById(Integer id);

    T SetElement(T t);

    T UpdateElement(T t);

    boolean DeleteElementById(Integer id);
}
