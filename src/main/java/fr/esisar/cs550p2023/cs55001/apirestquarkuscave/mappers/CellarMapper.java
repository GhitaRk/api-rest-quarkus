package fr.esisar.cs550p2023.cs55001.apirestquarkuscave.mappers;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import fr.esisar.cs550p2023.cs55001.apirestquarkuscave.dto.DTOCellar;
import fr.esisar.cs550p2023.cs55001.apirestquarkuscave.model.Cellar;

@Mapper(componentModel = "cdi", uses = {BoxMapper.class,BarrelMapper.class}, injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface CellarMapper {

	DTOCellar fromCellar(Cellar cellar);
	
	@InheritInverseConfiguration(name = "fromCellar")
	Cellar toCellar(DTOCellar cellar);
	

}
