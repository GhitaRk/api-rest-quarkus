package fr.esisar.cs550p2023.cs55001.apirestquarkuscave.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.validation.constraints.Past;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Data extends PanacheEntity {

	@Column(nullable = false)
	@Past
	public LocalDateTime measureDate;

}
