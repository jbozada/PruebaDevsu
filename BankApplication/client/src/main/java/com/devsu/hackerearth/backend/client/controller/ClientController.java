package com.devsu.hackerearth.backend.client.controller;

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

import com.devsu.hackerearth.backend.client.model.dto.ClientDto;
import com.devsu.hackerearth.backend.client.model.dto.PartialClientDto;
import com.devsu.hackerearth.backend.client.service.ClientService;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

	private final ClientService clientService;

	public ClientController(ClientService clientService) {
		this.clientService = clientService;
	}

	@GetMapping
	public ResponseEntity<List<ClientDto>> getAll() {
		// api/clients
		// Get all clients
		List<ClientDto> clients = clientService.getAll();
		if (clients.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(clients);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ClientDto> get(@PathVariable Long id) {
		// api/clients/{id}
		// Get clients by id
		return ResponseEntity.ok(clientService.getById(id));
	}

	@PostMapping
	public ResponseEntity<ClientDto> create(@RequestBody ClientDto clientDto) {
		// api/clients
		// Create client
		ClientDto savedClient = clientService.create(clientDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedClient);
	}

	@PutMapping("/{id}")
	public ResponseEntity<ClientDto> update(@PathVariable Long id, @RequestBody ClientDto clientDto) {
		// api/clients/{id}
		// Update client
		clientDto.setId(id);
		ClientDto updatedClient = clientService.update(clientDto);
		return ResponseEntity.ok(updatedClient);
	}

	@PatchMapping("/{id}")
	public ResponseEntity<ClientDto> partialUpdate(@PathVariable Long id,
			@RequestBody PartialClientDto partialClientDto) {
		// api/clients/{id}
		// Partial update client
		ClientDto updatedClient = clientService.partialUpdate(id, partialClientDto);
		return ResponseEntity.ok(updatedClient);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		// api/clients/{id}
		// Delete client
		clientService.deleteById(id);
		return ResponseEntity.ok().build();
	}
}
