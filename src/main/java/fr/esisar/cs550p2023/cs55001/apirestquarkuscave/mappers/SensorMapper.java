package fr.esisar.cs550p2023.cs55001.apirestquarkuscave.mappers;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import fr.esisar.cs550p2023.cs55001.apirestquarkuscave.dto.DTOAnalogicSensor;
import fr.esisar.cs550p2023.cs55001.apirestquarkuscave.dto.DTOAnalogicSensorFromAnalogicData;
import fr.esisar.cs550p2023.cs55001.apirestquarkuscave.dto.DTOAnalogicSensorFromBox;
import fr.esisar.cs550p2023.cs55001.apirestquarkuscave.dto.DTONumericSensor;
import fr.esisar.cs550p2023.cs55001.apirestquarkuscave.dto.DTONumericSensorFromBox;
import fr.esisar.cs550p2023.cs55001.apirestquarkuscave.dto.DTONumericSensorFromNumericData;
import fr.esisar.cs550p2023.cs55001.apirestquarkuscave.dto.DTOSensor;
import fr.esisar.cs550p2023.cs55001.apirestquarkuscave.dto.DTOSensorFromBox;
import fr.esisar.cs550p2023.cs55001.apirestquarkuscave.dto.DTOSensorFromData;
import fr.esisar.cs550p2023.cs55001.apirestquarkuscave.model.AnalogicSensor;
import fr.esisar.cs550p2023.cs55001.apirestquarkuscave.model.NumericSensor;
import fr.esisar.cs550p2023.cs55001.apirestquarkuscave.model.Sensor;

@Mapper(componentModel = "cdi", uses = { DataMapper.class, BoxMapper.class, TypeMapper.class,
		StateMapper.class }, injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface SensorMapper {

	DTOAnalogicSensor fromAnalogicSensor(AnalogicSensor analogicsensor);

	@InheritInverseConfiguration(name = "fromAnalogicSensor")
	AnalogicSensor toAnalogicSensor(DTOAnalogicSensor dtoAnalogicSensor);

	DTOAnalogicSensorFromAnalogicData fromAnalogicSensorFromAnalogicData(AnalogicSensor analogicsensor);

	@InheritInverseConfiguration(name = "fromAnalogicSensorFromAnalogicData")
	AnalogicSensor toAnalogicSensorFromAnalogicData(
			DTOAnalogicSensorFromAnalogicData dtoAnalogicSensorFromAnalogicData);

	DTOAnalogicSensorFromBox fromAnalogicSensorFromBox(AnalogicSensor analogicsensor);

	@InheritInverseConfiguration(name = "fromAnalogicSensorFromBox")
	AnalogicSensor toAnalogicSensorFromBox(DTOAnalogicSensorFromBox dtoAnalogicSensorFromBox);

	DTONumericSensor fromNumericSensor(NumericSensor numericsensor);

	@InheritInverseConfiguration(name = "fromNumericSensor")
	NumericSensor toNumericSensor(DTONumericSensor dtoNumericSensor);

	DTONumericSensorFromNumericData fromNumericSensorFromNumericData(NumericSensor numericsensor);

	@InheritInverseConfiguration(name = "fromNumericSensorFromNumericData")
	NumericSensor toNumericSensorFromNumericData(DTONumericSensorFromNumericData dtoNumericSensorFromNumericData);

	DTONumericSensorFromBox fromNumericSensorFromBox(NumericSensor numericsensor);

	@InheritInverseConfiguration(name = "fromNumericSensorFromBox")
	NumericSensor toNumericSensorFromBox(DTONumericSensorFromBox dtoNumericSensorFromBox);

	default DTOSensor fromSensor(Sensor sensor) {
		if (sensor instanceof AnalogicSensor) {
			return fromAnalogicSensor((AnalogicSensor) sensor);
		}
		if (sensor instanceof NumericSensor) {
			return fromNumericSensor((NumericSensor) sensor);
		}
		throw new IllegalArgumentException("Sensor type unknown");
	}

	default DTOSensorFromData fromSensorFromData(Sensor sensor) {
		if (sensor instanceof AnalogicSensor) {
			return fromAnalogicSensorFromAnalogicData((AnalogicSensor) sensor);
		}
		if (sensor instanceof NumericSensor) {
			return fromNumericSensorFromNumericData((NumericSensor) sensor);
		}
		throw new IllegalArgumentException("Sensor type unknown");
	}

	default DTOSensorFromBox fromSensorFromBox(Sensor sensor) {
		if (sensor instanceof AnalogicSensor) {
			return fromAnalogicSensorFromBox((AnalogicSensor) sensor);
		}
		if (sensor instanceof NumericSensor) {
			return fromNumericSensorFromBox((NumericSensor) sensor);
		}
		throw new IllegalArgumentException("Sensor type unknown");
	}

	default Sensor toSensor(DTOSensor dtoSensor) {
		if (dtoSensor instanceof DTOAnalogicSensor) {
			return toAnalogicSensor((DTOAnalogicSensor) dtoSensor);
		}
		if (dtoSensor instanceof DTONumericSensor) {
			return toNumericSensor((DTONumericSensor) dtoSensor);
		}

		throw new IllegalArgumentException("Sensor type unknown");
	}

	default Sensor toSensorFromData(DTOSensorFromData dtoSensor) {
		if (dtoSensor instanceof DTOAnalogicSensorFromAnalogicData) {
			return toAnalogicSensorFromAnalogicData((DTOAnalogicSensorFromAnalogicData) dtoSensor);
		}
		if (dtoSensor instanceof DTONumericSensorFromNumericData) {
			return toNumericSensorFromNumericData((DTONumericSensorFromNumericData) dtoSensor);
		}

		throw new IllegalArgumentException("Sensor type unknown");
	}

	default Sensor toSensorFromBox(DTOSensorFromBox dtoSensorFromBox) {

		if (dtoSensorFromBox instanceof DTOAnalogicSensorFromBox) {
			return toAnalogicSensorFromBox((DTOAnalogicSensorFromBox) dtoSensorFromBox);
		}

		if (dtoSensorFromBox instanceof DTONumericSensorFromBox) {
			return toNumericSensorFromBox((DTONumericSensorFromBox) dtoSensorFromBox);
		}
		throw new IllegalArgumentException("Sensor type unknown");
	}

}
