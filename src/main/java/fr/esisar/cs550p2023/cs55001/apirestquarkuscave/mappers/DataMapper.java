package fr.esisar.cs550p2023.cs55001.apirestquarkuscave.mappers;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import fr.esisar.cs550p2023.cs55001.apirestquarkuscave.dto.DTOAnalogicData;
import fr.esisar.cs550p2023.cs55001.apirestquarkuscave.dto.DTOAnalogicDataFromAnalogicSensor;
import fr.esisar.cs550p2023.cs55001.apirestquarkuscave.dto.DTOData;
import fr.esisar.cs550p2023.cs55001.apirestquarkuscave.dto.DTONumericData;
import fr.esisar.cs550p2023.cs55001.apirestquarkuscave.dto.DTONumericDataFromNumericSensor;
import fr.esisar.cs550p2023.cs55001.apirestquarkuscave.model.AnalogicData;
import fr.esisar.cs550p2023.cs55001.apirestquarkuscave.model.Data;
import fr.esisar.cs550p2023.cs55001.apirestquarkuscave.model.NumericData;

@Mapper(componentModel = "cdi", uses = { SensorMapper.class }, injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface DataMapper {

	DTOAnalogicData fromAnalogicData(AnalogicData analogicData);

	DTONumericData fromNumericData(NumericData numericData);

	DTOAnalogicDataFromAnalogicSensor fromAnalogicDataFromSensor(AnalogicData analogicData);

	DTONumericDataFromNumericSensor fromNumericDataFromSensor(NumericData numericData);

	@InheritInverseConfiguration(name = "fromAnalogicData")
	AnalogicData toAnalogicData(DTOAnalogicData dtoAnalogicData);

	@InheritInverseConfiguration(name = "fromNumericData")
	NumericData toNumericData(DTONumericData dtoNumericData);

	@InheritInverseConfiguration(name = "fromAnalogicDataFromSensor")
	AnalogicData toAnalogicData(DTOAnalogicDataFromAnalogicSensor dtoAnalogicDataFromAnalogicSensor);

	@InheritInverseConfiguration(name = "fromNumericDataFromSensor")
	NumericData toNumericData(DTONumericDataFromNumericSensor dtoNumericDataFromNumericSensor);

	default DTOData fromData(Data data) {
		if (data instanceof AnalogicData) {
			return fromAnalogicData((AnalogicData) data);
		}
		if (data instanceof NumericData) {
			return fromNumericData((NumericData) data);
		}
		throw new IllegalArgumentException("Data type unknown");
	}

	default DTOData fromDataFromSensor(Data data) {
		if (data instanceof AnalogicData) {
			return fromAnalogicDataFromSensor((AnalogicData) data);
		}
		if (data instanceof NumericData) {
			return fromNumericDataFromSensor((NumericData) data);
		}
		throw new IllegalArgumentException("Data type unknown");
	}

	default Data toData(DTOData dtoData) {
		if (dtoData instanceof DTOAnalogicData) {
			return toAnalogicData((DTOAnalogicData) dtoData);
		}
		if (dtoData instanceof DTONumericData) {
			return toNumericData((DTONumericData) dtoData);
		}
		if (dtoData instanceof DTOAnalogicDataFromAnalogicSensor) {
			return toAnalogicData((DTOAnalogicDataFromAnalogicSensor) dtoData);
		}
		if (dtoData instanceof DTONumericDataFromNumericSensor) {
			return toNumericData((DTONumericDataFromNumericSensor) dtoData);
		}
		throw new IllegalArgumentException("Data type unknown");
	}

}