package fr.esisar.cs550p2023.cs55001.apirestquarkuscave.mappers;

import fr.esisar.cs550p2023.cs55001.apirestquarkuscave.dto.DTOBarrelFromCellar;
import fr.esisar.cs550p2023.cs55001.apirestquarkuscave.dto.DTOBoxFromCellar;
import fr.esisar.cs550p2023.cs55001.apirestquarkuscave.dto.DTOCellar;
import fr.esisar.cs550p2023.cs55001.apirestquarkuscave.model.Barrel;
import fr.esisar.cs550p2023.cs55001.apirestquarkuscave.model.Box;
import fr.esisar.cs550p2023.cs55001.apirestquarkuscave.model.Cellar;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import javax.enterprise.context.ApplicationScoped;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-01-21T23:33:48+0000",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 15.0.2 (Oracle Corporation)"
)
@ApplicationScoped
public class CellarMapperImpl implements CellarMapper {

    @Override
    public DTOCellar fromCellar(Cellar cellar) {
        if ( cellar == null ) {
            return null;
        }

        DTOCellar dTOCellar = new DTOCellar();

        dTOCellar.id = cellar.id;
        dTOCellar.name = cellar.name;
        dTOCellar.capacity = cellar.capacity;
        dTOCellar.boxs = boxListToDTOBoxFromCellarList( cellar.boxs );
        dTOCellar.barrels = barrelListToDTOBarrelFromCellarList( cellar.barrels );

        return dTOCellar;
    }

    @Override
    public Cellar toCellar(DTOCellar cellar) {
        if ( cellar == null ) {
            return null;
        }

        Cellar cellar1 = new Cellar();

        cellar1.id = cellar.id;
        cellar1.name = cellar.name;
        cellar1.capacity = cellar.capacity;
        cellar1.boxs = dTOBoxFromCellarListToBoxList( cellar.boxs );
        cellar1.barrels = dTOBarrelFromCellarListToBarrelList( cellar.barrels );

        return cellar1;
    }

    protected DTOBoxFromCellar boxToDTOBoxFromCellar(Box box) {
        if ( box == null ) {
            return null;
        }

        DTOBoxFromCellar dTOBoxFromCellar = new DTOBoxFromCellar();

        dTOBoxFromCellar.id = box.id;
        dTOBoxFromCellar.serialNumber = box.serialNumber;
        dTOBoxFromCellar.name = box.name;

        return dTOBoxFromCellar;
    }

    protected List<DTOBoxFromCellar> boxListToDTOBoxFromCellarList(List<Box> list) {
        if ( list == null ) {
            return null;
        }

        List<DTOBoxFromCellar> list1 = new ArrayList<DTOBoxFromCellar>( list.size() );
        for ( Box box : list ) {
            list1.add( boxToDTOBoxFromCellar( box ) );
        }

        return list1;
    }

    protected DTOBarrelFromCellar barrelToDTOBarrelFromCellar(Barrel barrel) {
        if ( barrel == null ) {
            return null;
        }

        DTOBarrelFromCellar dTOBarrelFromCellar = new DTOBarrelFromCellar();

        dTOBarrelFromCellar.id = barrel.id;
        dTOBarrelFromCellar.name = barrel.name;
        dTOBarrelFromCellar.load = barrel.load;

        return dTOBarrelFromCellar;
    }

    protected List<DTOBarrelFromCellar> barrelListToDTOBarrelFromCellarList(List<Barrel> list) {
        if ( list == null ) {
            return null;
        }

        List<DTOBarrelFromCellar> list1 = new ArrayList<DTOBarrelFromCellar>( list.size() );
        for ( Barrel barrel : list ) {
            list1.add( barrelToDTOBarrelFromCellar( barrel ) );
        }

        return list1;
    }

    protected Box dTOBoxFromCellarToBox(DTOBoxFromCellar dTOBoxFromCellar) {
        if ( dTOBoxFromCellar == null ) {
            return null;
        }

        Box box = new Box();

        box.id = dTOBoxFromCellar.id;
        box.serialNumber = dTOBoxFromCellar.serialNumber;
        box.name = dTOBoxFromCellar.name;

        return box;
    }

    protected List<Box> dTOBoxFromCellarListToBoxList(List<DTOBoxFromCellar> list) {
        if ( list == null ) {
            return null;
        }

        List<Box> list1 = new ArrayList<Box>( list.size() );
        for ( DTOBoxFromCellar dTOBoxFromCellar : list ) {
            list1.add( dTOBoxFromCellarToBox( dTOBoxFromCellar ) );
        }

        return list1;
    }

    protected Barrel dTOBarrelFromCellarToBarrel(DTOBarrelFromCellar dTOBarrelFromCellar) {
        if ( dTOBarrelFromCellar == null ) {
            return null;
        }

        Barrel barrel = new Barrel();

        barrel.id = dTOBarrelFromCellar.id;
        barrel.name = dTOBarrelFromCellar.name;
        barrel.load = dTOBarrelFromCellar.load;

        return barrel;
    }

    protected List<Barrel> dTOBarrelFromCellarListToBarrelList(List<DTOBarrelFromCellar> list) {
        if ( list == null ) {
            return null;
        }

        List<Barrel> list1 = new ArrayList<Barrel>( list.size() );
        for ( DTOBarrelFromCellar dTOBarrelFromCellar : list ) {
            list1.add( dTOBarrelFromCellarToBarrel( dTOBarrelFromCellar ) );
        }

        return list1;
    }
}
