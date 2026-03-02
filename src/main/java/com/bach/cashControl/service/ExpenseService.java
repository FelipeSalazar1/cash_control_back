package com.bach.cashControl.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bach.cashControl.dto.ExpenseRequestDTO;
import com.bach.cashControl.dto.ExpenseResponseDTO;
import com.bach.cashControl.mapper.ExpenseMapper;
import com.bach.cashControl.model.Category;
import com.bach.cashControl.model.Expense;
import com.bach.cashControl.repository.ExpenseRepository;

import java.util.List;

@Service
@Transactional
public class ExpenseService {

    private final ExpenseRepository expenseRepository;

    public ExpenseService(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    @Transactional(readOnly = true)
    public List<ExpenseResponseDTO> findAll() {
        List<Expense> expenses = expenseRepository.findAll();
        return ExpenseMapper.toDtoList(expenses);
    }

    @Transactional(readOnly = true)
    public ExpenseResponseDTO findById(String id) {
        Expense expense = expenseRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Expense not found with id " + id));
        return ExpenseMapper.toDto(expense);
    }

    @Transactional(readOnly = true)
    public ExpenseResponseDTO findByCategory(Category category) {
        Expense expense = expenseRepository.findByCategory(category)
                .orElseThrow(() -> new IllegalArgumentException("Expense not found with category " + category));
        return ExpenseMapper.toDto(expense);
    }

    public ExpenseResponseDTO create(ExpenseRequestDTO dto) {
        Expense expense = ExpenseMapper.toEntity(dto);
        Expense saved = expenseRepository.save(expense);
        return ExpenseMapper.toDto(saved);
    }

    public ExpenseResponseDTO update(String id, ExpenseRequestDTO dto) {
        Expense existing = expenseRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Expense not found with id " + id));
        ExpenseMapper.updateEntityFromDto(dto, existing);
        Expense saved = expenseRepository.save(existing);
        return ExpenseMapper.toDto(saved);
    }

    public void delete(String id) {
        if (!expenseRepository.existsById(id)) {
            throw new IllegalArgumentException("Expense not found with id " + id);
        }
        expenseRepository.deleteById(id);
    }
}


