package fr.esisar.cs550p2023.cs55001.apirestquarkuscave.dto;

public abstract class DTOSensor {

	public Long id;
	public String serialNumber;
	public String name;
	public DTOBox box1;
	public DTOType type;
}
