package com.sn.budgetbee.services;

import com.sn.budgetbee.dto.TransactionDTO;

import java.util.List;

public interface TransactionService {

    List<TransactionDTO> findAll(Integer id);
}
