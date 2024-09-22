package fr.esisar.cs550p2023.cs55001.apirestquarkuscave.dto;

import java.util.List;

public class DTOCellar {

	public Long id;
	public String name;
	public Integer capacity;

	public List<DTOBoxFromCellar> boxs;
	public List<DTOBarrelFromCellar> barrels;

}
