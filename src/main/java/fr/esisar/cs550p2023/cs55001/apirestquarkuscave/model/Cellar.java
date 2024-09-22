package fr.esisar.cs550p2023.cs55001.apirestquarkuscave.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.Min;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

@Entity
public class Cellar extends PanacheEntity {

	@Column(unique = true, nullable = false)
	public String name;

	@Column(nullable = false)
	@Min(value = 1)
	public Integer capacity;

	@OneToMany(mappedBy = "boxCellar")
	public List<Box> boxs;

	@OneToMany(mappedBy = "barrelCellar")
	public List<Barrel> barrels;

}
