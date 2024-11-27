package com.devsu.hackerearth.backend.client.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.devsu.hackerearth.backend.client.mapper.ClientMapper;
import com.devsu.hackerearth.backend.client.model.Client;
import com.devsu.hackerearth.backend.client.model.dto.ClientDto;
import com.devsu.hackerearth.backend.client.model.dto.PartialClientDto;
import com.devsu.hackerearth.backend.client.repository.ClientRepository;

@Service
public class ClientServiceImpl implements ClientService {

	private final ClientRepository clientRepository;
	private final ClientMapper clientMapper;

	public ClientServiceImpl(ClientRepository clientRepository, ClientMapper clientMapper) {
		this.clientRepository = clientRepository;
		this.clientMapper = clientMapper;
	}

	@Override
	public List<ClientDto> getAll() {
		// Get all clients
		return clientRepository.findAll().stream().map(clientMapper::toResponse).collect(Collectors.toList());
	}

	@Override
	public ClientDto getById(Long id) {
		// Get clients by id
		Client clientOptional = clientRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Client not found"));
		return clientMapper.toResponse(clientOptional);
	}

	@Override
	public ClientDto create(ClientDto clientDto) {
		// Create client
		Optional<ClientDto> exist = clientRepository.findByDni(clientDto.getDni()).map(clientMapper::toResponse);
		if (exist.isPresent()) {
			throw new RuntimeException("Client exist");
		}
		Client client = clientMapper.clientDtoToEntity(clientDto);
		client = clientRepository.save(client);
		return clientMapper.toResponse(client);
	}

	@Override
	public ClientDto update(ClientDto clientDto) {
		// Update client
		Client client = clientRepository.findById(clientDto.getId())
				.orElseThrow(() -> new RuntimeException("Client not found"));
		clientMapper.updateInfoEntity(clientDto, client);
		Client savedClient = clientRepository.save(client);
		return clientMapper.toResponse(savedClient);
	}

	@Override
	public ClientDto partialUpdate(Long id, PartialClientDto partialClientDto) {
		// Partial update account
		Client client = clientRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Client not found"));
		clientMapper.updatePartialInfoEntity(partialClientDto, client);
		Client savedClient = clientRepository.save(client);
		return clientMapper.toResponse(savedClient);
	}

	@Override
	public void deleteById(Long id) {
		// Delete client
		Client clientOptional = clientRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Client not found"));
		clientOptional.setActive(false);
		clientRepository.save(clientOptional);
	}
}
