package com.bach.cashControl.mapper;

import java.time.LocalDate;
import java.util.List;

import com.bach.cashControl.dto.ExpenseRequestDTO;
import com.bach.cashControl.dto.ExpenseResponseDTO;
import com.bach.cashControl.model.Expense;

public final class ExpenseMapper {

    private ExpenseMapper() {
    }

    public static ExpenseResponseDTO toDto(Expense expense) {
        if (expense == null) {
            return null;
        }
        return ExpenseResponseDTO.builder()
                .id(expense.getId())
                .description(expense.getDescription())
                .amount(expense.getAmount())
                .date(expense.getDate())
                .category(expense.getCategory())
                .build();
    }

    public static List<ExpenseResponseDTO> toDtoList(List<Expense> expenses) {
        return expenses.stream()
                .map(ExpenseMapper::toDto)
                .toList();
    }

    public static Expense toEntity(ExpenseRequestDTO dto) {
        if (dto == null) {
            return null;
        }
        return Expense.builder()
                .description(dto.getDescription())
                .amount(dto.getAmount())
                .category(dto.getCategory())
                .date(LocalDate.now())
                .build();
    }

    public static void updateEntityFromDto(ExpenseRequestDTO dto, Expense expense) {
        if (dto == null || expense == null) {
            return;
        }
        expense.setDescription(dto.getDescription());
        expense.setAmount(dto.getAmount());
        expense.setCategory(dto.getCategory());
    }
}

