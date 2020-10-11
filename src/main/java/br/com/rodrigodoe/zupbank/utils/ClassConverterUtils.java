package br.com.rodrigodoe.zupbank.utils;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;

public class ClassConverterUtils {
	
	static ModelMapper modelMapper = new ModelMapper();
	
	public static  <S, T> T  convert(S source, Class<T> targetclass) {
		T newTargetClass =  modelMapper.map(source, targetclass);
	    return newTargetClass;
	}
	
	
	public static <S, T> List<T> convertList(List<S> source, Class<T> targetClass) {
	    return source
	      .stream()
	      .map(element -> modelMapper.map(element, targetClass))
	      .collect(Collectors.toList());
	}

}
