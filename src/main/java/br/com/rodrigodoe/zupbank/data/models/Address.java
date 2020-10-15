package br.com.rodrigodoe.zupbank.data.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import br.com.rodrigodoe.zupbank.enums.ClientStatus;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import net.bytebuddy.agent.builder.AgentBuilder.ClassFileBufferStrategy.Default;

@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long id;

	@Column(nullable = false)
	private String zipcode;

	@Column(nullable = false)
	private String addressLine1;

	@Column(nullable = false)
	private String addressLine2;

	@Column(nullable = false)
	private String suburb;

	@Column(nullable = false)
	private String city;

	@Column(nullable = false)
	private String state;

	@OneToOne
	private Client client;


}
