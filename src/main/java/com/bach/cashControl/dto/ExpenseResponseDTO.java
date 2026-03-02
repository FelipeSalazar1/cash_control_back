package com.bach.cashControl.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.bach.cashControl.model.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExpenseResponseDTO {

    private String id;
    private String description;
    private BigDecimal amount;
    private LocalDate date;
    private Category category;
}

