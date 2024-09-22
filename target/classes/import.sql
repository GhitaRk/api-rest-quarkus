--Ajout dans la BDD de Unit
INSERT INTO Unit(id,name,symbol) VALUES (nextval('hibernate_sequence'),'Degrés','C°');
INSERT INTO Unit(id,name,symbol) VALUES (nextval('hibernate_sequence'),'Humidité',' g/m3');

--Ajout dans la BDD de Type
INSERT INTO Type(id,nameType) VALUES (nextval('hibernate_sequence'),'Analogic');
INSERT INTO Type(id,nameType) VALUES (nextval('hibernate_sequence'),'Numeric');
INSERT INTO Type (id, nameType ) VALUES (nextval('hibernate_sequence'), 'type1');
INSERT INTO Type (id, nameType ) VALUES (nextval('hibernate_sequence'), 'type2');
INSERT INTO Type (id, nameType ) VALUES (nextval('hibernate_sequence'), 'type3');
INSERT INTO Type (id, nameType ) VALUES (nextval('hibernate_sequence'), 'type4');
INSERT INTO Type (id, nameType ) VALUES (nextval('hibernate_sequence'), 'type5');

--Ajout dans la BDD de State
INSERT INTO State(id,up,down) VALUES (nextval('hibernate_sequence'),'True','False');
INSERT INTO State(id,up,down) VALUES (nextval('hibernate_sequence'),'False','True');
INSERT INTO State (id, up,down) VALUES (nextval('hibernate_sequence'),'up1','down1');
INSERT INTO State (id, up,down) VALUES (nextval('hibernate_sequence'),'up2','down2');
INSERT INTO State (id, up,down) VALUES (nextval('hibernate_sequence'),'up3','down3');
INSERT INTO State (id, up,down) VALUES (nextval('hibernate_sequence'),'up4','down4');
INSERT INTO State (id, up,down) VALUES (nextval('hibernate_sequence'),'up5','down5');

--Ajout dans la BDD de AnalogicSensor
INSERT INTO AnalogicSensor(id,serialNumber,name,type_id,unit_id) VALUES (nextval('hibernate_sequence'),'0001','Capteur de Température1',3,1);
INSERT INTO AnalogicSensor(id,serialNumber,name,type_id,unit_id) VALUES (nextval('hibernate_sequence'),'0002','Capteur d humidité1',3,2);
INSERT INTO AnalogicSensor(id,serialNumber,name,type_id,unit_id) VALUES (nextval('hibernate_sequence'),'0003','Capteur de Température2',3,1);
INSERT INTO AnalogicSensor(id,serialNumber,name,type_id,unit_id) VALUES (nextval('hibernate_sequence'),'0004','Capteur d humidité2',3,2);

--Ajout dans la BDD de NumericSensor
INSERT INTO NumericSensor(id,serialNumber,name,type_id,state_id) VALUES (nextval('hibernate_sequence'),'0005','Capteur de présence1',4,10);
INSERT INTO NumericSensor(id,serialNumber,name,type_id,state_id) VALUES (nextval('hibernate_sequence'),'0006','Capteur de présence2',4,11);
INSERT INTO NumericSensor(id,serialNumber,name,type_id,state_id) VALUES (nextval('hibernate_sequence'),'0007','Capteur de présence3',4,10);
INSERT INTO NumericSensor(id,serialNumber,name,type_id,state_id) VALUES (nextval('hibernate_sequence'),'0008','Capteur de présence4',4,11);

--Ajout dans la BDD de AnalogicData
INSERT INTO AnalogicData(id,measureDate,analogicValue,analogicSensor1_id) VALUES (nextval('hibernate_sequence'),'2022-11-27 12:34:12',27.5,17);
INSERT INTO AnalogicData(id,measureDate,analogicValue,analogicSensor1_id) VALUES (nextval('hibernate_sequence'),'2020-01-01 12:34:12',123.456,18);

--Ajout dans la BDD de NumericData
INSERT INTO NumericData(id,measureDate,numericValue,numericSensor1_id) VALUES (nextval('hibernate_sequence'),'2022-11-27 14:54:9',true,22);

--Ajout dans la BDD de Box
INSERT INTO Box(id, name, serialNumber) VALUES (nextval('hibernate_sequence'), 'box 1', '01');
INSERT INTO Box(id, name, serialNumber) VALUES (nextval('hibernate_sequence'), 'box 2', '02');
INSERT INTO Box(id, name, serialNumber) VALUES (nextval('hibernate_sequence'), 'box 3', '03');
INSERT INTO Box(id, name, serialNumber) VALUES (nextval('hibernate_sequence'), 'box 4', '04');

--Ajout dans la BDD de Cellar
INSERT INTO Cellar(id, name, capacity) VALUES (nextval('hibernate_sequence'), 'cellar 1', 100);
INSERT INTO Cellar(id, name, capacity) VALUES (nextval('hibernate_sequence'), 'cellar 2', 150);

--Ajout dans la BDD de Barrel
INSERT INTO Barrel(id, name, load, barrelCellar_id, box_id) VALUES (nextval('hibernate_sequence'), 'barrel 1', 100.5, 32, 28);
INSERT INTO Barrel(id, name, load, barrelCellar_id, box_id) VALUES (nextval('hibernate_sequence'), 'barrel 2', 98.6, 32, 29);
INSERT INTO Barrel(id, name, load, barrelCellar_id, box_id) VALUES (nextval('hibernate_sequence'), 'barrel 3', 105.7, 33, 30);
INSERT INTO Barrel(id, name, load, barrelCellar_id, box_id) VALUES (nextval('hibernate_sequence'), 'barrel 4', 103.4, 33, 31);