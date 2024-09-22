package fr.esisar.cs550p2023.cs55001.apirestquarkuscave.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

@Entity
public class State extends PanacheEntity {

	@Column(nullable = false)
	public String up;

	@Column(nullable = false)
	public String down;

	@OneToMany(mappedBy = "state")
	public List<NumericSensor> numericSensor;
}
