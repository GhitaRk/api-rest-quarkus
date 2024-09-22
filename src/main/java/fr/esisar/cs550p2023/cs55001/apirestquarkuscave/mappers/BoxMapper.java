package fr.esisar.cs550p2023.cs55001.apirestquarkuscave.mappers;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import fr.esisar.cs550p2023.cs55001.apirestquarkuscave.dto.DTOBox;
import fr.esisar.cs550p2023.cs55001.apirestquarkuscave.model.Box;

@Mapper(componentModel = "cdi",uses = {BarrelMapper.class},injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface BoxMapper {
	DTOBox fromBox(Box box);

	@InheritInverseConfiguration(name = "fromBox")
	Box toBox(DTOBox box);

}


