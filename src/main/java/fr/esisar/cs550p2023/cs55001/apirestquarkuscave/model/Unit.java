package fr.esisar.cs550p2023.cs55001.apirestquarkuscave.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

@Entity
public class Unit extends PanacheEntity {

	@Column(unique = true, nullable = false)
	public String name;

	@Column(unique = true, nullable = false)
	public String symbol;

	@OneToMany(mappedBy = "unit")
	public List<AnalogicSensor> analogicSensor;
}
