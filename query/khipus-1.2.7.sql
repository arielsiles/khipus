--Khipus MRP PROD-79: Generar el asiento contable para productos terminados
--Diego H. Loza Fernandez
--Fecha de creacion: 16/12/2013
create table PERIODOCOSTOINDIRECTO(
   IDPERIODOCOSTOINDIRECTO number(24,0) not null
  ,MES number(2) 
  ,DIA number(2) 
  ,IDGESTION	NUMBER(24,0)  
  ,CONSTRAINT pk_PERIODOCOSTOINDIRECTO primary key(IDPERIODOCOSTOINDIRECTO)
);

ALTER TABLE PERIODOCOSTOINDIRECTO
ADD CONSTRAINT fk_peridocostoindirecto FOREIGN KEY (IDGESTION) REFERENCES gestion(IDGESTION);
/*ALTER TABLE PERIODOCOSTOINDIRECTO
ADD CONSTRAINT fk_perido_costoindirecto FOREIGN KEY (IDCOSTOSINDIRECTOS) REFERENCES COSTOSINDIRECTOS(IDCOSTOSINDIRECTOS);
*/
--DROP TABLE PERIODOCOSTOINDIRECTO
create table COSTOSINDIRECTOSCONF(
   IDCOSTOSINDIRECTOSCONF number(24,0) not null
  ,NO_CIA	VARCHAR2(2 BYTE) not null
  ,CUENTA	VARCHAR2(20 BYTE) not null
  ,COD_GRU	VARCHAR2(3 BYTE) NULL
  ,IDCOMPANIA NUMBER(24) NOT  NULL
  ,CONSTRAINT pk_costosindirectosconf primary key(IDCOSTOSINDIRECTOSCONF)
);
--drop table COSTOSINDIRECTOSCONF
ALTER TABLE costosindirectosconf
ADD CONSTRAINT fk_GRUPO_costosindirectosconf FOREIGN KEY (NO_CIA,COD_GRU) REFERENCES WISE.INV_GRUPOS(NO_CIA,COD_GRU);
ALTER TABLE costosindirectosconf
ADD CONSTRAINT fk_ARCGMS_costosindirectosconf FOREIGN KEY (NO_CIA,CUENTA) REFERENCES WISE.ARCGMS(NO_CIA,CUENTA);
--DROP TABLE COSTOSINDIRECTOS
CREATE TABLE COSTOSINDIRECTOS (
   IDCOSTOSINDIRECTOS NUMBER (24,0) NOT NULL
  ,NOMBRE VARCHAR(512) NOT NULL
  ,MONTOBS NUMBER(16,2) NOT NULL  
  ,IDPERIODOCOSTOINDIRECTO number(24,0) null
  ,IDCOSTOSINDIRECTOSCONF number(24,0) null
  ,IDORDENPRODUCCION	NUMBER(24,0) NULL
  ,VERSION   NUMBER(24) NOT NULL
  ,IDCOMPANIA NUMBER(24) NOT  NULL
  ,CONSTRAINT pk_COSTOSINDIRECTOS PRIMARY KEY (IDCOSTOSINDIRECTOS)
);
--DROP TABLE COSTOSINDIRECTOS
ALTER TABLE COSTOSINDIRECTOS
ADD CONSTRAINT fk_PERIODO_COSTOSINDIRECTOS FOREIGN KEY (IDPERIODOCOSTOINDIRECTO) REFERENCES PERIODOCOSTOINDIRECTO(IDPERIODOCOSTOINDIRECTO);

ALTER TABLE COSTOSINDIRECTOS
ADD CONSTRAINT fk_ORDEN_COSTOSINDIRECTOS FOREIGN KEY (IDORDENPRODUCCION) REFERENCES ORDENPRODUCCION(IDORDENPRODUCCION);

ALTER TABLE COSTOSINDIRECTOS
ADD CONSTRAINT fk_COSTCONF_COSTOSINDIRECTOS FOREIGN KEY (idcostosindirectosconf) REFERENCES costosindirectosconf(idcostosindirectosconf);
-------------
ALTER TABLE ORDENPRODUCCION
ADD CONSTRAINT fk_COSTINDIREC_ORDENPRODUCCION FOREIGN KEY (IDORDENPRODUCCION) REFERENCES ORDENPRODUCCION(IDORDENPRODUCCION);

/* Ariel 23122013: Add column for table CONFIGURATION */
ALTER TABLE WISE.CONFIGURACION ADD (CTATRANSALM2MN  VARCHAR2(20 BYTE) NULL);
UPDATE WISE.CONFIGURACION SET CTATRANSALM2MN = '10103004001001';

--COMMIT