/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexionBDD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class InitDatabase {

    Connection conexion;

    public InitDatabase() {
        Statement stmt = null;
        try {
            //Class.forName("org.sqlite.JDBC");
            conexion = DriverManager.getConnection("jdbc:sqlite:facturacion.db");
            System.out.println("Opened database successfully");
            
            stmt = conexion.createStatement();
            String sql = "create table CLIENTE (\n" +
                    "   ID_CLIENTE           CHAR(10)             not null,\n" +
                    "   CONTRASENA           VARCHAR(30)          not null,\n" +
                    "   NOMBRE_CLIENTE       VARCHAR(50)          not null,\n" +
                    "   EMAIL_CLIENTE        VARCHAR(30)          null,\n" +
                    "   constraint PK_CLIENTE primary key (ID_CLIENTE)\n" +
                    ");" +
                    "create unique index CLIENTE_PK on CLIENTE (\n" +
                    "ID_CLIENTE\n" +
                    ");" +
                    "create table ESTABLECIMIENTO (\n" +
                    "   ID_ESTABLECIMIENTO   CHAR(13)             not null,\n" +
                    "   NOMBRE_ESTABLECIMIENTO VARCHAR(300)         not null,\n" +
                    "   DIRECCION_ESTABLECIMIENTO VARCHAR(300)         null,\n" +
                    "   TELEFONO_ESTABLECIMIENTO VARCHAR(10)          null,\n" +
                    "   constraint PK_ESTABLECIMIENTO primary key (ID_ESTABLECIMIENTO)\n" +
                    ");" +
                    "create unique index ESTABLECIMIENTO_PK on ESTABLECIMIENTO (\n" +
                    "ID_ESTABLECIMIENTO\n" +
                    ");\n" +
                    "create table FACTURA (\n" +
                    "   ID_FACTURA           VARCHAR(20)          not null,\n" +
                    "   ID_CLIENTE           CHAR(10)             not null,\n" +
                    "   ID_ESTABLECIMIENTO   CHAR(13)             not null,\n" +
                    "   TIPO_FACTURA         VARCHAR(10)          not null,\n" +
                    "   FECHA_EMISION        DATE                 not null,\n" +
                    "   ESTADO_FACTURA       VARCHAR(15)          null,\n" +
                    "   AMBIENTE_FACTURA     VARCHAR(15)          null,\n" +
                    "   TOTAL_SIN_IVA        MONEY                not null,\n" +
                    "   IVA                  MONEY                not null,\n" +
                    "   TOTAL_CON_IVA        MONEY                not null,\n" +
                    "   constraint PK_FACTURA primary key (ID_FACTURA)\n" +
                    ");" +
                    "create unique index FACTURA_PK on FACTURA (\n" +
                    "ID_FACTURA\n" +
                    ");" +
                    
                    "create table DETALLE (\n" +
                    "   ID_FACTURA           VARCHAR(20)          not null,\n" +
                    "   NOMBRE_PRODUCTO      CHAR(50)             not null,\n" +
                    "   TOTAL                MONEY             not null,\n" +
                    "   TIPO                 VARCHAR(15)          not null,\n" +
                    "   PRIMARY KEY (ID_FACTURA, NOMBRE_PRODUCTO)\n" +
                    ");" +
                    
                    
                    "create  index RELATIONSHIP_3_FK on FACTURA (\n" +
                    "ID_ESTABLECIMIENTO\n" +
                    ");" +
                    "create  index RELATIONSHIP_4_FK on FACTURA (\n" +
                    "ID_CLIENTE\n" +
                    ");" +
                    "create table GASTOSANUALESPERSONALES (\n" +
                    "   ANIO_GASTOS          INTEGER                 not null,\n" +
                    "   TOTAL_ALIMENTACION   MONEY                not null,\n" +
                    "   TOTAL_SALUD          MONEY                not null,\n" +
                    "   TOTAL_VIVIENDA       MONEY                not null,\n" +
                    "   TOTAL_EDUCACION      MONEY                not null,\n" +
                    "   TOTAL_VESTIMENTA     MONEY                not null,\n" +
                    "   constraint PK_GASTOSANUALESPERSONALES primary key (ANIO_GASTOS)\n" +
                    ");" +
                    "create unique index GASTOSANUALESPERSONALES_PK on GASTOSANUALESPERSONALES (\n" +
                    "ANIO_GASTOS\n" +
                    ");" +
                    "create table HISTORIAL_PAGOS_NEGOCIOS (\n" +
                    "   ANIO_HISTORIAL_N     INTEGER                 not null,\n" +
                    "   ID_CLIENTE           CHAR(10)             null,\n" +
                    "   TOTAL_MERCADERIA     MONEY                null,\n" +
                    "   TOTAL_ARRIENDO       MONEY                null,\n" +
                    "   TOTAL_SERVICIOS      MONEY                null,\n" +
                    "   TOTAL_SUELDOS        MONEY                null,\n" +
                    "   TOTAL_MOVILIZACION   MONEY                null,\n" +
                    "   TOTAL_VIATICOS       MONEY                null,\n" +
                    "   TOTAL_CAPACITACION   MONEY                null,\n" +
                    "   TOTAL_SUMINISTROS    MONEY                null,\n" +
                    "   TOTAL_HERRAMIENTAS   MONEY                null,\n" +
                    "   constraint PK_HISTORIAL_PAGOS_NEGOCIOS primary key (ANIO_HISTORIAL_N)\n" +
                    ");" +
                    "create unique index HISTORIAL_PAGOS_NEGOCIOS_PK on HISTORIAL_PAGOS_NEGOCIOS (\n" +
                    "ANIO_HISTORIAL_N\n" +
                    ");" +
                    "create  index RELATIONSHIP_6_FK on HISTORIAL_PAGOS_NEGOCIOS (\n" +
                    "ID_CLIENTE\n" +
                    ");" +
                    "create table HISTORIAL_PAGOS_PERSONALES (\n" +
                    "   ANIO_HISTORIAL_P     INTEGER                 not null,\n" +
                    "   ID_CLIENTE           CHAR(10)             null,\n" +
                    "   TOTAL_ALIMENTACION   MONEY                null,\n" +
                    "   TOTAL_SALUD          MONEY                null,\n" +
                    "   TOTAL_VIVIENDA       MONEY                null,\n" +
                    "   TOTAL_EDUCACION      MONEY                null,\n" +
                    "   TOTAL_VESTIMENTA     MONEY                null,\n" +
                    "   TOTAL_OTROS          MONEY                null,\n" +
                    "   constraint PK_HISTORIAL_PAGOS_PERSONALES primary key (ANIO_HISTORIAL_P)\n" +
                    ");" +
                    "create unique index HISTORIAL_PAGOS_PERSONALES_PK on HISTORIAL_PAGOS_PERSONALES (\n" +
                    "ANIO_HISTORIAL_P\n" +
                    ");" +
                    "create  index RELATIONSHIP_5_FK on HISTORIAL_PAGOS_PERSONALES (\n" +
                    "ID_CLIENTE\n" +
                    ");" +
                    "create table TIPO_GASTO (\n" +
                    "   ID_FACTURA           VARCHAR(20)          not null,\n" +
                    "   TIPO                 VARCHAR(20)          not null,\n" +
                    "   TOTAL                MONEY                not null\n" +
                    ");" +
                    "create  index RELATIONSHIP_1_FK on TIPO_GASTO (\n" +
                    "ID_FACTURA\n" +
                    ");"+
                    "create table RELACIONGASTO (\n" +
                    "   DESCRIPCIONRELACION           VARCHAR(30)          not null,\n" +
                    "   TIPO_GASTO       VARCHAR(50)          not null\n" +
                    ");" +
                    "create table PROV_GASTO (\n" +
                    "   PROVEEDOR           VARCHAR(100)          not null,\n" +
                    "   TIPO_GASTO       VARCHAR(50)          not null\n" +
                    ");" +
                    "create table TIPO_GASTO_NEG (\n" +                      
                    "   Id_usuario           VARCHAR(15)          not null,\n" +
                    "   TIPO_GASTO       VARCHAR(50)          not null\n" +
                    ");";
                    
            stmt.executeUpdate(sql);
            String datos = " INSERT INTO GASTOSANUALESPERSONALES VALUES (2013, 3630.25, 14521.00, 3630.25, 3630.25, 3630.25);"
                          + "INSERT INTO GASTOSANUALESPERSONALES VALUES (2014, 3630.25, 14521.00, 3630.25, 3630.25, 3630.25);"
                          + "INSERT INTO GASTOSANUALESPERSONALES VALUES (2015, 3630.25, 14521.00, 3630.25, 3630.25, 3630.25);"
                          + "INSERT INTO GASTOSANUALESPERSONALES VALUES (2016, 3630.25, 14521.00, 3630.25, 3630.25, 3630.25);"
                          + "INSERT INTO GASTOSANUALESPERSONALES VALUES (2017, 3630.25, 14521.00, 3630.25, 3630.25, 3630.25);";
            stmt.executeUpdate(datos);
            stmt.close();
            conexion.close();
        } catch (SQLException e) {
            System.out.println("Connection Failed! Check output console");
            e.printStackTrace();
        }

    }
}