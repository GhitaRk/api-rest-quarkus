package fr.esisar.cs550p2023.cs55001.apirestquarkuscave.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class AnalogicSensor extends Sensor {

	@ManyToOne
	public Unit unit;

	@OneToMany(mappedBy = "analogicSensor1")
	public List<AnalogicData> analogicDatas;

}
