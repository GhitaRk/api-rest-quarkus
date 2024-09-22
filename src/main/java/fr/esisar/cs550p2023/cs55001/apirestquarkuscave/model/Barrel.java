package fr.esisar.cs550p2023.cs55001.apirestquarkuscave.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

@Entity
public class Barrel extends PanacheEntity {

	@Column(unique = true, nullable = false)
	public String name;

	@Column(nullable = false)
	public Double load;

	@ManyToOne
	public Cellar barrelCellar;

	@OneToOne
	public Box box;
}
