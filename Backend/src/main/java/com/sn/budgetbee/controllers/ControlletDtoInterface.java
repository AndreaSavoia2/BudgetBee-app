package com.sn.budgetbee.controllers;

import java.util.List;

public interface ControlletDtoInterface <T>{

    List<T> getAllElementsDto();
    T getElementDtoById(Integer id);
}
