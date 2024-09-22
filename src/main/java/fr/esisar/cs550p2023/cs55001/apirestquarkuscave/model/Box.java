package fr.esisar.cs550p2023.cs55001.apirestquarkuscave.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

@Entity
public class Box extends PanacheEntity {

	@Column(unique = true, nullable = false)
	public String serialNumber;

	@Column(unique = true, nullable = false)
	public String name;

	@ManyToOne
	public Cellar boxCellar;

	@OneToOne(mappedBy = "box")
	public Barrel barrel;

	/*@ManyToOne
	public Type type;*/

	//@OneToMany(mappedBy = "box1")
	//public List<Sensor> sensors;

}
