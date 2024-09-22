package fr.esisar.cs550p2023.cs55001.apirestquarkuscave.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class NumericData extends Data {

	@Column(nullable = false)
	public Boolean numericValue;

	@ManyToOne
	public NumericSensor numericSensor1;

}
