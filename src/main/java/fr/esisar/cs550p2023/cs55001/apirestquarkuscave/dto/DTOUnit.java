package fr.esisar.cs550p2023.cs55001.apirestquarkuscave.dto;

import javax.persistence.Column;

public class DTOUnit {
	public Long id;
	
	@Column(unique = true, nullable = false)
	public String name;

	@Column(unique = true, nullable = false)
	public String symbol;

}
