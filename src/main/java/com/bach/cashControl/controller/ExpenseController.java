package com.bach.cashControl.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bach.cashControl.dto.ExpenseRequestDTO;
import com.bach.cashControl.dto.ExpenseResponseDTO;
import com.bach.cashControl.model.Category;
import com.bach.cashControl.service.ExpenseService;

import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/api/v1/expenses")
@CrossOrigin(origins = {
    "http://localhost:4200", 
    "https://cash-control-front.vercel.app"
})
@Validated
public class ExpenseController {

    private final ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @GetMapping
    public ResponseEntity<List<ExpenseResponseDTO>> getAll() {
        return ResponseEntity.ok(expenseService.findAll());
    }

    @GetMapping("/{category}")
    public ResponseEntity<ExpenseResponseDTO> getByCategory(@PathVariable Category category) {
        return ResponseEntity.ok(expenseService.findByCategory(category));
    }

    @PostMapping
    public ResponseEntity<ExpenseResponseDTO> create(@Valid @RequestBody ExpenseRequestDTO request) {
        ExpenseResponseDTO created = expenseService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ExpenseResponseDTO> update(@PathVariable String id,
                                                     @Valid @RequestBody ExpenseRequestDTO request) {
        ExpenseResponseDTO updated = expenseService.update(id, request);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        expenseService.delete(id);
        return ResponseEntity.noContent().build();
    }
}


