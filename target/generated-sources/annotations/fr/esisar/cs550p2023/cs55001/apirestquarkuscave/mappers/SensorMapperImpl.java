package fr.esisar.cs550p2023.cs55001.apirestquarkuscave.mappers;

import fr.esisar.cs550p2023.cs55001.apirestquarkuscave.dto.DTOAnalogicDataFromAnalogicSensor;
import fr.esisar.cs550p2023.cs55001.apirestquarkuscave.dto.DTOAnalogicSensor;
import fr.esisar.cs550p2023.cs55001.apirestquarkuscave.dto.DTOAnalogicSensorFromAnalogicData;
import fr.esisar.cs550p2023.cs55001.apirestquarkuscave.dto.DTOAnalogicSensorFromBox;
import fr.esisar.cs550p2023.cs55001.apirestquarkuscave.dto.DTONumericData;
import fr.esisar.cs550p2023.cs55001.apirestquarkuscave.dto.DTONumericSensor;
import fr.esisar.cs550p2023.cs55001.apirestquarkuscave.dto.DTONumericSensorFromBox;
import fr.esisar.cs550p2023.cs55001.apirestquarkuscave.dto.DTONumericSensorFromNumericData;
import fr.esisar.cs550p2023.cs55001.apirestquarkuscave.dto.DTOUnit;
import fr.esisar.cs550p2023.cs55001.apirestquarkuscave.model.AnalogicData;
import fr.esisar.cs550p2023.cs55001.apirestquarkuscave.model.AnalogicSensor;
import fr.esisar.cs550p2023.cs55001.apirestquarkuscave.model.NumericData;
import fr.esisar.cs550p2023.cs55001.apirestquarkuscave.model.NumericSensor;
import fr.esisar.cs550p2023.cs55001.apirestquarkuscave.model.Unit;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-01-21T23:33:47+0000",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 15.0.2 (Oracle Corporation)"
)
@ApplicationScoped
public class SensorMapperImpl implements SensorMapper {

    private DataMapper dataMapper;
    private BoxMapper boxMapper;
    private TypeMapper typeMapper;
    private StateMapper stateMapper;

    public SensorMapperImpl() {
    }

    @Inject
    public SensorMapperImpl(DataMapper dataMapper, BoxMapper boxMapper, TypeMapper typeMapper, StateMapper stateMapper) {

        this.dataMapper = dataMapper;
        this.boxMapper = boxMapper;
        this.typeMapper = typeMapper;
        this.stateMapper = stateMapper;
    }

    @Override
    public DTOAnalogicSensor fromAnalogicSensor(AnalogicSensor analogicsensor) {
        if ( analogicsensor == null ) {
            return null;
        }

        DTOAnalogicSensor dTOAnalogicSensor = new DTOAnalogicSensor();

        dTOAnalogicSensor.id = analogicsensor.id;
        dTOAnalogicSensor.serialNumber = analogicsensor.serialNumber;
        dTOAnalogicSensor.name = analogicsensor.name;
        dTOAnalogicSensor.box1 = boxMapper.fromBox( analogicsensor.box1 );
        dTOAnalogicSensor.type = typeMapper.fromType( analogicsensor.type );
        dTOAnalogicSensor.unit = unitToDTOUnit( analogicsensor.unit );
        dTOAnalogicSensor.analogicDatas = analogicDataListToDTOAnalogicDataFromAnalogicSensorList( analogicsensor.analogicDatas );

        return dTOAnalogicSensor;
    }

    @Override
    public AnalogicSensor toAnalogicSensor(DTOAnalogicSensor dtoAnalogicSensor) {
        if ( dtoAnalogicSensor == null ) {
            return null;
        }

        AnalogicSensor analogicSensor = new AnalogicSensor();

        analogicSensor.id = dtoAnalogicSensor.id;
        analogicSensor.serialNumber = dtoAnalogicSensor.serialNumber;
        analogicSensor.name = dtoAnalogicSensor.name;
        analogicSensor.box1 = boxMapper.toBox( dtoAnalogicSensor.box1 );
        analogicSensor.type = typeMapper.toType( dtoAnalogicSensor.type );
        analogicSensor.unit = dTOUnitToUnit( dtoAnalogicSensor.unit );
        analogicSensor.analogicDatas = dTOAnalogicDataFromAnalogicSensorListToAnalogicDataList( dtoAnalogicSensor.analogicDatas );

        return analogicSensor;
    }

    @Override
    public DTOAnalogicSensorFromAnalogicData fromAnalogicSensorFromAnalogicData(AnalogicSensor analogicsensor) {
        if ( analogicsensor == null ) {
            return null;
        }

        DTOAnalogicSensorFromAnalogicData dTOAnalogicSensorFromAnalogicData = new DTOAnalogicSensorFromAnalogicData();

        dTOAnalogicSensorFromAnalogicData.id = analogicsensor.id;
        dTOAnalogicSensorFromAnalogicData.serialNumber = analogicsensor.serialNumber;
        dTOAnalogicSensorFromAnalogicData.name = analogicsensor.name;

        return dTOAnalogicSensorFromAnalogicData;
    }

    @Override
    public AnalogicSensor toAnalogicSensorFromAnalogicData(DTOAnalogicSensorFromAnalogicData dtoAnalogicSensorFromAnalogicData) {
        if ( dtoAnalogicSensorFromAnalogicData == null ) {
            return null;
        }

        AnalogicSensor analogicSensor = new AnalogicSensor();

        analogicSensor.id = dtoAnalogicSensorFromAnalogicData.id;
        analogicSensor.serialNumber = dtoAnalogicSensorFromAnalogicData.serialNumber;
        analogicSensor.name = dtoAnalogicSensorFromAnalogicData.name;

        return analogicSensor;
    }

    @Override
    public DTOAnalogicSensorFromBox fromAnalogicSensorFromBox(AnalogicSensor analogicsensor) {
        if ( analogicsensor == null ) {
            return null;
        }

        DTOAnalogicSensorFromBox dTOAnalogicSensorFromBox = new DTOAnalogicSensorFromBox();

        dTOAnalogicSensorFromBox.id = analogicsensor.id;
        dTOAnalogicSensorFromBox.serialNumber = analogicsensor.serialNumber;
        dTOAnalogicSensorFromBox.name = analogicsensor.name;
        dTOAnalogicSensorFromBox.type = typeMapper.fromType( analogicsensor.type );

        return dTOAnalogicSensorFromBox;
    }

    @Override
    public AnalogicSensor toAnalogicSensorFromBox(DTOAnalogicSensorFromBox dtoAnalogicSensorFromBox) {
        if ( dtoAnalogicSensorFromBox == null ) {
            return null;
        }

        AnalogicSensor analogicSensor = new AnalogicSensor();

        analogicSensor.id = dtoAnalogicSensorFromBox.id;
        analogicSensor.serialNumber = dtoAnalogicSensorFromBox.serialNumber;
        analogicSensor.name = dtoAnalogicSensorFromBox.name;
        analogicSensor.type = typeMapper.toType( dtoAnalogicSensorFromBox.type );

        return analogicSensor;
    }

    @Override
    public DTONumericSensor fromNumericSensor(NumericSensor numericsensor) {
        if ( numericsensor == null ) {
            return null;
        }

        DTONumericSensor dTONumericSensor = new DTONumericSensor();

        dTONumericSensor.id = numericsensor.id;
        dTONumericSensor.serialNumber = numericsensor.serialNumber;
        dTONumericSensor.name = numericsensor.name;
        dTONumericSensor.box1 = boxMapper.fromBox( numericsensor.box1 );
        dTONumericSensor.type = typeMapper.fromType( numericsensor.type );
        dTONumericSensor.state = stateMapper.fromState( numericsensor.state );
        dTONumericSensor.numericDatas = numericDataListToDTONumericDataList( numericsensor.numericDatas );

        return dTONumericSensor;
    }

    @Override
    public NumericSensor toNumericSensor(DTONumericSensor dtoNumericSensor) {
        if ( dtoNumericSensor == null ) {
            return null;
        }

        NumericSensor numericSensor = new NumericSensor();

        numericSensor.id = dtoNumericSensor.id;
        numericSensor.serialNumber = dtoNumericSensor.serialNumber;
        numericSensor.name = dtoNumericSensor.name;
        numericSensor.box1 = boxMapper.toBox( dtoNumericSensor.box1 );
        numericSensor.type = typeMapper.toType( dtoNumericSensor.type );
        numericSensor.state = stateMapper.toState( dtoNumericSensor.state );
        numericSensor.numericDatas = dTONumericDataListToNumericDataList( dtoNumericSensor.numericDatas );

        return numericSensor;
    }

    @Override
    public DTONumericSensorFromNumericData fromNumericSensorFromNumericData(NumericSensor numericsensor) {
        if ( numericsensor == null ) {
            return null;
        }

        DTONumericSensorFromNumericData dTONumericSensorFromNumericData = new DTONumericSensorFromNumericData();

        dTONumericSensorFromNumericData.id = numericsensor.id;
        dTONumericSensorFromNumericData.serialNumber = numericsensor.serialNumber;
        dTONumericSensorFromNumericData.name = numericsensor.name;

        return dTONumericSensorFromNumericData;
    }

    @Override
    public NumericSensor toNumericSensorFromNumericData(DTONumericSensorFromNumericData dtoNumericSensorFromNumericData) {
        if ( dtoNumericSensorFromNumericData == null ) {
            return null;
        }

        NumericSensor numericSensor = new NumericSensor();

        numericSensor.id = dtoNumericSensorFromNumericData.id;
        numericSensor.serialNumber = dtoNumericSensorFromNumericData.serialNumber;
        numericSensor.name = dtoNumericSensorFromNumericData.name;

        return numericSensor;
    }

    @Override
    public DTONumericSensorFromBox fromNumericSensorFromBox(NumericSensor numericsensor) {
        if ( numericsensor == null ) {
            return null;
        }

        DTONumericSensorFromBox dTONumericSensorFromBox = new DTONumericSensorFromBox();

        dTONumericSensorFromBox.id = numericsensor.id;
        dTONumericSensorFromBox.serialNumber = numericsensor.serialNumber;
        dTONumericSensorFromBox.name = numericsensor.name;
        dTONumericSensorFromBox.type = typeMapper.fromType( numericsensor.type );

        return dTONumericSensorFromBox;
    }

    @Override
    public NumericSensor toNumericSensorFromBox(DTONumericSensorFromBox dtoNumericSensorFromBox) {
        if ( dtoNumericSensorFromBox == null ) {
            return null;
        }

        NumericSensor numericSensor = new NumericSensor();

        numericSensor.id = dtoNumericSensorFromBox.id;
        numericSensor.serialNumber = dtoNumericSensorFromBox.serialNumber;
        numericSensor.name = dtoNumericSensorFromBox.name;
        numericSensor.type = typeMapper.toType( dtoNumericSensorFromBox.type );

        return numericSensor;
    }

    protected DTOUnit unitToDTOUnit(Unit unit) {
        if ( unit == null ) {
            return null;
        }

        DTOUnit dTOUnit = new DTOUnit();

        dTOUnit.id = unit.id;
        dTOUnit.name = unit.name;
        dTOUnit.symbol = unit.symbol;

        return dTOUnit;
    }

    protected List<DTOAnalogicDataFromAnalogicSensor> analogicDataListToDTOAnalogicDataFromAnalogicSensorList(List<AnalogicData> list) {
        if ( list == null ) {
            return null;
        }

        List<DTOAnalogicDataFromAnalogicSensor> list1 = new ArrayList<DTOAnalogicDataFromAnalogicSensor>( list.size() );
        for ( AnalogicData analogicData : list ) {
            list1.add( dataMapper.fromAnalogicDataFromSensor( analogicData ) );
        }

        return list1;
    }

    protected Unit dTOUnitToUnit(DTOUnit dTOUnit) {
        if ( dTOUnit == null ) {
            return null;
        }

        Unit unit = new Unit();

        unit.id = dTOUnit.id;
        unit.name = dTOUnit.name;
        unit.symbol = dTOUnit.symbol;

        return unit;
    }

    protected List<AnalogicData> dTOAnalogicDataFromAnalogicSensorListToAnalogicDataList(List<DTOAnalogicDataFromAnalogicSensor> list) {
        if ( list == null ) {
            return null;
        }

        List<AnalogicData> list1 = new ArrayList<AnalogicData>( list.size() );
        for ( DTOAnalogicDataFromAnalogicSensor dTOAnalogicDataFromAnalogicSensor : list ) {
            list1.add( dataMapper.toAnalogicData( dTOAnalogicDataFromAnalogicSensor ) );
        }

        return list1;
    }

    protected List<DTONumericData> numericDataListToDTONumericDataList(List<NumericData> list) {
        if ( list == null ) {
            return null;
        }

        List<DTONumericData> list1 = new ArrayList<DTONumericData>( list.size() );
        for ( NumericData numericData : list ) {
            list1.add( dataMapper.fromNumericData( numericData ) );
        }

        return list1;
    }

    protected List<NumericData> dTONumericDataListToNumericDataList(List<DTONumericData> list) {
        if ( list == null ) {
            return null;
        }

        List<NumericData> list1 = new ArrayList<NumericData>( list.size() );
        for ( DTONumericData dTONumericData : list ) {
            list1.add( dataMapper.toNumericData( dTONumericData ) );
        }

        return list1;
    }
}
