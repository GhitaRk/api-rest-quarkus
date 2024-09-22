package fr.esisar.cs550p2023.cs55001.apirestquarkuscave.mappers;

import fr.esisar.cs550p2023.cs55001.apirestquarkuscave.dto.DTOAnalogicData;
import fr.esisar.cs550p2023.cs55001.apirestquarkuscave.dto.DTOAnalogicDataFromAnalogicSensor;
import fr.esisar.cs550p2023.cs55001.apirestquarkuscave.dto.DTONumericData;
import fr.esisar.cs550p2023.cs55001.apirestquarkuscave.dto.DTONumericDataFromNumericSensor;
import fr.esisar.cs550p2023.cs55001.apirestquarkuscave.model.AnalogicData;
import fr.esisar.cs550p2023.cs55001.apirestquarkuscave.model.NumericData;
import javax.annotation.processing.Generated;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-01-21T23:33:48+0000",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 15.0.2 (Oracle Corporation)"
)
@ApplicationScoped
public class DataMapperImpl implements DataMapper {

    private SensorMapper sensorMapper;

    public DataMapperImpl() {
    }

    @Inject
    public DataMapperImpl(SensorMapper sensorMapper) {

        this.sensorMapper = sensorMapper;
    }

    @Override
    public DTOAnalogicData fromAnalogicData(AnalogicData analogicData) {
        if ( analogicData == null ) {
            return null;
        }

        DTOAnalogicData dTOAnalogicData = new DTOAnalogicData();

        dTOAnalogicData.id = analogicData.id;
        dTOAnalogicData.measureDate = analogicData.measureDate;
        dTOAnalogicData.analogicValue = analogicData.analogicValue;
        dTOAnalogicData.analogicSensor1 = sensorMapper.fromAnalogicSensorFromAnalogicData( analogicData.analogicSensor1 );

        return dTOAnalogicData;
    }

    @Override
    public DTONumericData fromNumericData(NumericData numericData) {
        if ( numericData == null ) {
            return null;
        }

        DTONumericData dTONumericData = new DTONumericData();

        dTONumericData.id = numericData.id;
        dTONumericData.measureDate = numericData.measureDate;
        dTONumericData.numericValue = numericData.numericValue;
        dTONumericData.numericSensor1 = sensorMapper.fromNumericSensorFromNumericData( numericData.numericSensor1 );

        return dTONumericData;
    }

    @Override
    public DTOAnalogicDataFromAnalogicSensor fromAnalogicDataFromSensor(AnalogicData analogicData) {
        if ( analogicData == null ) {
            return null;
        }

        DTOAnalogicDataFromAnalogicSensor dTOAnalogicDataFromAnalogicSensor = new DTOAnalogicDataFromAnalogicSensor();

        dTOAnalogicDataFromAnalogicSensor.id = analogicData.id;
        dTOAnalogicDataFromAnalogicSensor.measureDate = analogicData.measureDate;
        dTOAnalogicDataFromAnalogicSensor.analogicValue = analogicData.analogicValue;

        return dTOAnalogicDataFromAnalogicSensor;
    }

    @Override
    public DTONumericDataFromNumericSensor fromNumericDataFromSensor(NumericData numericData) {
        if ( numericData == null ) {
            return null;
        }

        DTONumericDataFromNumericSensor dTONumericDataFromNumericSensor = new DTONumericDataFromNumericSensor();

        dTONumericDataFromNumericSensor.id = numericData.id;
        dTONumericDataFromNumericSensor.measureDate = numericData.measureDate;
        dTONumericDataFromNumericSensor.numericValue = numericData.numericValue;

        return dTONumericDataFromNumericSensor;
    }

    @Override
    public AnalogicData toAnalogicData(DTOAnalogicData dtoAnalogicData) {
        if ( dtoAnalogicData == null ) {
            return null;
        }

        AnalogicData analogicData = new AnalogicData();

        analogicData.id = dtoAnalogicData.id;
        analogicData.measureDate = dtoAnalogicData.measureDate;
        analogicData.analogicValue = dtoAnalogicData.analogicValue;
        analogicData.analogicSensor1 = sensorMapper.toAnalogicSensorFromAnalogicData( dtoAnalogicData.analogicSensor1 );

        return analogicData;
    }

    @Override
    public NumericData toNumericData(DTONumericData dtoNumericData) {
        if ( dtoNumericData == null ) {
            return null;
        }

        NumericData numericData = new NumericData();

        numericData.id = dtoNumericData.id;
        numericData.measureDate = dtoNumericData.measureDate;
        numericData.numericValue = dtoNumericData.numericValue;
        numericData.numericSensor1 = sensorMapper.toNumericSensorFromNumericData( dtoNumericData.numericSensor1 );

        return numericData;
    }

    @Override
    public AnalogicData toAnalogicData(DTOAnalogicDataFromAnalogicSensor dtoAnalogicDataFromAnalogicSensor) {
        if ( dtoAnalogicDataFromAnalogicSensor == null ) {
            return null;
        }

        AnalogicData analogicData = new AnalogicData();

        analogicData.id = dtoAnalogicDataFromAnalogicSensor.id;
        analogicData.measureDate = dtoAnalogicDataFromAnalogicSensor.measureDate;
        analogicData.analogicValue = dtoAnalogicDataFromAnalogicSensor.analogicValue;

        return analogicData;
    }

    @Override
    public NumericData toNumericData(DTONumericDataFromNumericSensor dtoNumericDataFromNumericSensor) {
        if ( dtoNumericDataFromNumericSensor == null ) {
            return null;
        }

        NumericData numericData = new NumericData();

        numericData.id = dtoNumericDataFromNumericSensor.id;
        numericData.measureDate = dtoNumericDataFromNumericSensor.measureDate;
        numericData.numericValue = dtoNumericDataFromNumericSensor.numericValue;

        return numericData;
    }
}
