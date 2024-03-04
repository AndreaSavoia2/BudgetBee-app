package com.sn.budgetbee.repos;

import com.sn.budgetbee.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDAO extends JpaRepository<User, Integer> {
}
