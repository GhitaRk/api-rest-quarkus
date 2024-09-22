package fr.esisar.cs550p2023.cs55001.apirestquarkuscave.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Sensor extends PanacheEntity {

	@Column(unique = true, nullable = false)
	public String serialNumber;

	@Column(unique = true, nullable = false)
	public String name;

	@ManyToOne
	public Box box1;

	@ManyToOne
	public Type type;

}
