package fr.esisar.cs550p2023.cs55001.apirestquarkuscave.dto;

public class DTOBox {

	public Long id;
	public String serialNumber;
	public String name;

	// public List<DTOSensorFromBox> sensors ;
	public DTOBarrelFromBox barrel;
	public DTOCellarFromBox boxCellar;


}
