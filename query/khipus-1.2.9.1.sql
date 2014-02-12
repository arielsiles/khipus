
--SELECT * FROM FUNCIONALIDAD
--Khipus MRP PROD-83: Implementar el re procesado de productos
--Diego H. Loza Fernandez

CREATE TABLE PRODUCTOREPROCESADO(
   IDPRODUCTOREPROCESADO NUMBER(24,0) NOT NULL
  ,IDPRODUCTOBASE NUMBER(24,0) NOT NULL
  ,IDMETAPRODUCTOPRODUCCION NUMBER(24,0) NOT NULL  
  ,CONSTRAINT PK_PRODUCTOREPROCESADO PRIMARY KEY(IDPRODUCTOREPROCESADO)
);
--DROP TABLE PRODUCTOREPROCESADO
ALTER TABLE PRODUCTOREPROCESADO ADD CONSTRAINT FK_PRODUCBASE_PRODREPRO FOREIGN KEY (IDPRODUCTOBASE)
REFERENCES PRODUCTOBASE(IDPRODUCTOBASE);
ALTER TABLE PRODUCTOREPROCESADO ADD CONSTRAINT FK_METAPROD_PRODREPRO FOREIGN KEY (IDMETAPRODUCTOPRODUCCION)
REFERENCES METAPRODUCTOPRODUCCION(IDMETAPRODUCTOPRODUCCION);
--COMMIT
--rollback

CREATE TABLE PRODUCTOBASE(	
  IDPRODUCTOBASE NUMBER(24,0) NOT NULL, 
	UNIDADES NUMBER(6,0) NULL, 
	VOLUMEN NUMBER(8,2) NULL, 
  CODIGO VARCHAR(50) NOT NULL,
  COSTOTOTALINSUMOS	NUMBER(16,6) NULL,
	IDPLANIFICACIONPRODUCCION NUMBER(24,0) NOT NULL, 
	IDMETAPRODUCTOPRODUCCION NUMBER(24,0) NOT NULL, 
	IDCOMPANIA NUMBER(24,0) NOT NULL,
  CONSTRAINT PK_PRODUCTOBASE PRIMARY KEY(IDPRODUCTOBASE)
);

/*
ALTER TABLE PRODUCTOBASE ADD CONSTRAINT FK_METAPROD_REPROC FOREIGN KEY (IDMETAPRODUCTOPRODUCCION)
	  REFERENCES METAPRODUCTOPRODUCCION (IDMETAPRODUCTOPRODUCCION) ENABLE;
    */
-- alter table PRODUCTOBASE drop constraint FK_METAPROD_REPROC
--commit
ALTER TABLE PRODUCTOBASE ADD CONSTRAINT FK_PLANIF_REPROC FOREIGN KEY (IDPLANIFICACIONPRODUCCION)
	  REFERENCES PLANIFICACIONPRODUCCION (IDPLANIFICACIONPRODUCCION) ENABLE;
--DROP TABLE PRODUCTOBASE    

CREATE TABLE PRODUCTOSIMPLE(
  IDPRODUCTOSIMPLE  NUMBER(24) NOT NULL
  ,CANTIDAD NUMBER(6) NULL
  ,COSTOTOTALINSUMOS	NUMBER(16,6) NULL
  ,COSTOTOTALMATERIALES	NUMBER(16,6) NULL  
  ,ESTADO VARCHAR(20) NOT NULL
  ,IDPRODUCTOBASE NUMBER(24) NULL  
  ,IDCOMPANIA NUMBER(24) NOT  NULL
  ,CONSTRAINT PK_PRODUCTOSIMPLE PRIMARY KEY(IDPRODUCTOSIMPLE)
);
--drop TABLE PRODUCTOSIMPLE
--ALTER TABLE PRODUCTOSIMPLE DROP COLUMN IDMETAPRODUCTOPRODUCCION;
ALTER TABLE PRODUCTOSIMPLE 
ADD CONSTRAINT FK_PRODSIMP_PRODUCTRE 
FOREIGN KEY(IDPRODUCTOBASE) REFERENCES PRODUCTOBASE(IDPRODUCTOBASE);

/*ALTER TABLE PRODUCTOSIMPLE
ADD CONSTRAINT FK_METAPROD_PRODREPROC 
FOREIGN KEY(IDMETAPRODUCTOPRODUCCION) REFERENCES METAPRODUCTOPRODUCCION(IDMETAPRODUCTOPRODUCCION);
*/
--commit
/*
ALTER TABLE METAPRODUCTOPRODUCCION ADD IDPRODUCTOBASE NUMBER(24,0) NULL; 
ALTER TABLE METAPRODUCTOPRODUCCION 
ADD CONSTRAINT FK_PRODUCBASE_METAPROD FOREIGN KEY(IDPRODUCTOBASE) REFERENCES PRODUCTOBASE(IDPRODUCTOBASE);

ALTER TABLE METAPRODUCTOPRODUCCION DROP COLUMN IDPRODUCTOBASE;
*/
ALTER TABLE ORDENINSUMO
MODIFY (IDORDENPRODUCCION NUMBER(24,0) NULL); 

ALTER TABLE ORDENMATERIAL
ADD (IDPRODUCTOSIMPLE  NUMBER(24) NULL);

ALTER TABLE ORDENMATERIAL
ADD CONSTRAINT FK_ORDENMAT_PRODSIMPL FOREIGN KEY (IDPRODUCTOSIMPLE)
REFERENCES PRODUCTOSIMPLE(IDPRODUCTOSIMPLE) ENABLE;
--alter table ORDENMATERIAL drop constraint FK_ORDENMAT_PRODSIMPL
ALTER TABLE ORDENINSUMO
ADD (IDPRODUCTOBASE  NUMBER(24) NULL);
--commit
ALTER TABLE ORDENINSUMO
ADD CONSTRAINT FK_ORDENINSU_PRODBASE FOREIGN KEY (IDPRODUCTOBASE)
REFERENCES PRODUCTOBASE(IDPRODUCTOBASE) ENABLE;
--alter table ORDENINSUMO drop constraint FK_ORDENINSU_PRODBASE


