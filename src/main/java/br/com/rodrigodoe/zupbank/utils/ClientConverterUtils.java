package br.com.rodrigodoe.zupbank.utils;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;

import br.com.rodrigodoe.zupbank.data.dtos.ClientDTO;
import br.com.rodrigodoe.zupbank.data.models.Client;

public class ClientConverterUtils {
	
	static ModelMapper modelMapper = new ModelMapper();
	
	public static  ClientDTO convertToDto(Client client) {
		ClientDTO clientDto = modelMapper.map(client, ClientDTO.class);
	    return clientDto;
	}
	
	public static  Client convertToEntity(ClientDTO clientDto) {
		Client client = modelMapper.map(clientDto, Client.class);
	    return client;
	}
	
	
	public static <S, T> List<T> convertList(List<S> source, Class<T> targetClass) {
	    return source
	      .stream()
	      .map(element -> modelMapper.map(element, targetClass))
	      .collect(Collectors.toList());
	}

}
