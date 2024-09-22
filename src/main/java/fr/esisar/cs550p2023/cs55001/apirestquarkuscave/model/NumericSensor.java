package fr.esisar.cs550p2023.cs55001.apirestquarkuscave.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class NumericSensor extends Sensor {

	@ManyToOne
	public State state;

	@OneToMany(mappedBy = "numericSensor1")
	public List<NumericData> numericDatas;

}
