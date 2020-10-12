package br.com.rodrigodoe.zupbank.data.models;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Client {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long id;
	
	@Column(nullable = false)
	private String firstName;
	@Column(nullable = false)
	private String lastName;
	@Column(nullable = false, unique = true)
	private String email;
	
	@Column(nullable = false)
	private LocalDate birthDay;
	@Column(nullable = false)
	private String cpf;
	
	@OneToOne(mappedBy = "client")
	@Cascade(CascadeType.DELETE)
	private Address address;
	
	@OneToOne(mappedBy = "client")
	@Cascade(CascadeType.DELETE)
	private FileStorage fotoCPF;


	

}
