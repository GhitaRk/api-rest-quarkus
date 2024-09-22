package fr.esisar.cs550p2023.cs55001.apirestquarkuscave.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class AnalogicData extends Data {

	@Column(nullable = false)
	public Float analogicValue;

	@ManyToOne
	public AnalogicSensor analogicSensor1;

}
