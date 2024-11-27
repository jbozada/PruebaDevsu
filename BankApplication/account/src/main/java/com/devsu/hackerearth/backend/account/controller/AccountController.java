package com.devsu.hackerearth.backend.account.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devsu.hackerearth.backend.account.model.dto.AccountDto;
import com.devsu.hackerearth.backend.account.model.dto.PartialAccountDto;
import com.devsu.hackerearth.backend.account.service.AccountService;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

	private final AccountService accountService;

	public AccountController(AccountService accountService) {
		this.accountService = accountService;
	}

	@GetMapping
	public ResponseEntity<List<AccountDto>> getAll() {
		// api/accounts
		// Get all accounts
		List<AccountDto> accounts = accountService.getAll();
		if (accounts.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(accounts);
	}

	@GetMapping("/{id}")
	public ResponseEntity<AccountDto> get(@PathVariable Long id) {
		// api/accounts/{id}
		// Get accounts by id
		return ResponseEntity.ok(accountService.getById(id));
	}

	@PostMapping
	public ResponseEntity<AccountDto> create(@RequestBody AccountDto accountDto) {
		// api/accounts
		// Create accounts
		AccountDto savedAccount = accountService.create(accountDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedAccount);
	}

	@PutMapping("/{id}")
	public ResponseEntity<AccountDto> update(@PathVariable Long id, @RequestBody AccountDto accountDto) {
		// api/accounts/{id}
		// Update accounts
		accountDto.setId(id);
		AccountDto updatedAccount = accountService.update(accountDto);
		return ResponseEntity.ok(updatedAccount);
	}

	@PatchMapping("/{id}")
	public ResponseEntity<AccountDto> partialUpdate(@PathVariable Long id,
			@RequestBody PartialAccountDto partialAccountDto) {
		// api/accounts/{id}
		AccountDto updatedAccount = accountService.partialUpdate(id, partialAccountDto);
		return ResponseEntity.ok(updatedAccount);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		// api/accounts/{id}
		// Delete accounts
		accountService.deleteById(id);
		return ResponseEntity.ok().build();
	}
}
