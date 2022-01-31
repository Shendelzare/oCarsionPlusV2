DROP TABLE IF EXISTS EXTRA;
DROP TABLE IF EXISTS PRECIO;
DROP TABLE IF EXISTS COCHE;
DROP TABLE IF EXISTS MARCA;
DROP TABLE IF EXISTS usuario;

DROP SEQUENCE IF EXISTS ocarsionplus_marca_id_seq;
DROP SEQUENCE IF EXISTS ocarsionplus_coche_id_seq;
DROP SEQUENCE IF EXISTS ocarsionplus_precio_id_seq;
DROP SEQUENCE IF EXISTS ocarsionplus_extra_id_seq;
DROP SEQUENCE IF EXISTS ocarsionplus_usuario_id_seq;



CREATE SEQUENCE ocarsionplus_marca_id_seq
    AS INT
    INCREMENT BY 1;

CREATE SEQUENCE ocarsionplus_coche_id_seq
    AS INT
    INCREMENT BY 1;

CREATE SEQUENCE ocarsionplus_precio_id_seq
    AS INT
    INCREMENT BY 1;

CREATE SEQUENCE ocarsionplus_extra_id_seq
    AS INT
    INCREMENT BY 1;

CREATE SEQUENCE ocarsionplus_usuario_id_seq
    AS INT
    INCREMENT BY 1;

    
CREATE TABLE IF NOT EXISTS marca
(
   id integer NOT NULL PRIMARY KEY,
   nombre character varying (15) COLLATE pg_catalog."default" NOT NULL,
   fecha_creacion timestamp without time zone DEFAULT NOW (),
   fecha_modificacion timestamp without time zone
)
TABLESPACE pg_default;

CREATE TABLE IF NOT EXISTS coche
(
   id integer NOT NULL PRIMARY KEY,
   id_marca integer NOT NULL,
   color integer NOT NULL,
   cilindrada integer NOT NULL,
   potencia integer NOT NULL,
   nombre_modelo character varying (20) COLLATE pg_catalog."default" NOT NULL,
   fecha_creacion timestamp without time zone DEFAULT NOW (),
   fecha_modificacion timestamp without time zone,
   CONSTRAINT ocarsionplus_coche_marca_fk FOREIGN KEY(id_marca) REFERENCES marca(id) ON UPDATE NO ACTION ON DELETE NO ACTION
)
TABLESPACE pg_default;

CREATE TABLE IF NOT EXISTS precio
(
   id integer NOT NULL PRIMARY KEY,
   id_coche integer NOT NULL,
   valor character varying (20) COLLATE pg_catalog."default" NOT NULL,
   ini_fecha_vigor timestamp without time zone,
   fin_fecha_vigor timestamp without time zone,
   fecha_creacion timestamp without time zone DEFAULT NOW (),
   fecha_modificacion timestamp without time zone,
   CONSTRAINT ocarsionplus_precio_coche_fk FOREIGN KEY (id_coche) REFERENCES coche(id) ON UPDATE NO ACTION ON DELETE NO ACTION
)
TABLESPACE pg_default;

CREATE TABLE IF NOT EXISTS extra
(
   id integer NOT NULL PRIMARY KEY,
   id_coche integer NOT NULL,
   nombre character varying (100) COLLATE pg_catalog."default" NOT NULL,
   fecha_creacion timestamp without time zone DEFAULT NOW (),
   fecha_modificacion timestamp without time zone,
   CONSTRAINT ocarsionplus_extra_coche_fk FOREIGN KEY (id_coche) REFERENCES coche (id) ON UPDATE NO ACTION ON DELETE NO ACTION
)
TABLESPACE pg_default;

CREATE TABLE IF NOT EXISTS usuario
(
   id integer NOT NULL PRIMARY KEY,
   name character varying (15) COLLATE pg_catalog."default" NOT NULL,
   email character varying (80) COLLATE pg_catalog."default" NOT NULL,
   password character varying (80) COLLATE pg_catalog."default" NOT NULL,
   fecha_creacion timestamp without time zone DEFAULT NOW (),
   fecha_modificacion timestamp without time zone
)