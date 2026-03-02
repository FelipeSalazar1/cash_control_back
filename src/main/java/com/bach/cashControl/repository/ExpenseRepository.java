package com.bach.cashControl.repository;

import com.bach.cashControl.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bach.cashControl.model.Expense;

import java.util.Optional;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, String> {
    Optional<Expense> findByCategory(Category category);
}

