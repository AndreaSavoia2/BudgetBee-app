package com.sn.budgetbee.services;

import com.sn.budgetbee.dto.ExitDTO;
import com.sn.budgetbee.dto.TransactionDTO;
import com.sn.budgetbee.entities.Exit;
import com.sn.budgetbee.entities.Transaction;
import com.sn.budgetbee.repos.EntranceIconDAO;
import com.sn.budgetbee.repos.ExitIconDAO;
import com.sn.budgetbee.repos.TransactionDAO;
import com.sn.budgetbee.utils.EntranceCategories;
import com.sn.budgetbee.utils.ExitCategories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService{

    private final TransactionDAO TRANSACTION_DAO;
    private final ExitIconDAO EXIT_ICON_DAO;
    private final EntranceIconDAO ENRANCE_ICON_DAO;

    @Autowired
    public TransactionServiceImpl(TransactionDAO TRANSACTION_DAO, ExitIconDAO EXIT_ICON_DAO, EntranceIconDAO ENRANCE_ICON_DAO) {
        this.TRANSACTION_DAO = TRANSACTION_DAO;
        this.EXIT_ICON_DAO = EXIT_ICON_DAO;
        this.ENRANCE_ICON_DAO = ENRANCE_ICON_DAO;
    }

    @Override
   public List<TransactionDTO> findAll(Integer id) {
       List<Transaction> transactions = TRANSACTION_DAO.findTransactionsByBudgetId(id);
       List<TransactionDTO> transactionsDTO = new ArrayList<>();
       String link = null;

       DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("dd/MM/yyyy");
       DateTimeFormatter formatterHor = DateTimeFormatter.ofPattern("HH:mm");

       for (Transaction transaction : transactions){

           if (transaction.getType().equalsIgnoreCase("exit")){
               link = EXIT_ICON_DAO.findLink(ExitCategories.valueOf(transaction.getCategory()));
           }

           if(transaction.getType().equalsIgnoreCase("entrance")){
               link = ENRANCE_ICON_DAO.findLink(EntranceCategories.valueOf(transaction.getCategory()));
           }

           transactionsDTO.add(new TransactionDTO(
                   transaction.getId(),
                   transaction.getTransaction(),
                   transaction.getDescription(),
                   transaction.getTransactionDate().format(formatterDate),
                   transaction.getTransactionDate().format(formatterHor),
                   transaction.getCategory(),
                   link));
       }
       return transactionsDTO;
   }
}
