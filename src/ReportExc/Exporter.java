/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ReportExc;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import javax.swing.JTable;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

/**
 *
 * @author Mayra
 */
public class Exporter {

    private File file;
    private List<JTable> tabla;
    private List<JTable> tabla1;
    private List<String> nom_files;

    public Exporter(File file, List<JTable> tabla, List<String> nom_files) throws Exception {
        this.file = file;
        this.tabla = tabla;
        this.nom_files = nom_files;
        if (nom_files.size() != tabla.size()) {
            throw new Exception("Error");
        }
    }

    public Exporter(File file, List<JTable> tabla, List<JTable> tabla1, List<String> nom_files) throws Exception {
        this.file = file;
        this.tabla = tabla;
        this.tabla1 = tabla1;
        this.nom_files = nom_files;
        if (nom_files.size() != tabla.size()) {
            throw new Exception("Error");
        }
    }

    public boolean export() {
        try {
            DataOutputStream out = new DataOutputStream(new FileOutputStream(file));
            WritableWorkbook w = Workbook.createWorkbook(out);
            for (int index = 0; index < tabla.size(); index++) {
                JTable table = tabla.get(index);
                WritableSheet s = w.createSheet(nom_files.get(index), 0);
                for (int i = 0; i < table.getColumnCount(); i++) {
                    String NomCol = table.getColumnName(i);
                    System.out.println(NomCol);
                    s.addCell(new Label(i, 0, NomCol));
                    for (int j = 0; j < table.getRowCount(); j++) {
                        Object object = table.getValueAt(j, i);
                        s.addCell(new Label(i, j + 1, String.valueOf(object)));
                    }
                }
            }
            w.write();
            w.close();
            out.flush();
            out.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean exportDosTablas() {
        try {
            DataOutputStream out = new DataOutputStream(new FileOutputStream(file));
            WritableWorkbook w = Workbook.createWorkbook(out);
            for (int index = 0; index < tabla.size(); index++) {
                JTable table = tabla.get(index);
                WritableSheet s = w.createSheet(nom_files.get(index), 0);
                for (int i = 0; i < table.getColumnCount(); i++) {
                    String NomCol = table.getColumnName(i);
                    System.out.println(NomCol);
                    s.addCell(new Label(i, 0, NomCol));
                    for (int j = 0; j < table.getRowCount(); j++) {
                        Object object = table.getValueAt(j, i);
                        s.addCell(new Label(i, j + 1, String.valueOf(object)));
                    }
                }
            }
            
            for (int index = 0; index < tabla1.size(); index++) {
                JTable table = tabla1.get(index);
                WritableSheet s = w.createSheet(nom_files.get(index), 0);
                for (int i = 0; i < table.getColumnCount(); i++) {
                    String NomCol = table.getColumnName(i);
                    System.out.println(NomCol);
                    s.addCell(new Label(i, 0, NomCol));
                    for (int j = 0; j < table.getRowCount(); j++) {
                        Object object = table.getValueAt(j, i);
                        s.addCell(new Label(i, j + 1, String.valueOf(object)));
                    }
                }
            }
            
            w.write();
            w.close();
            out.flush();
            out.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    public boolean exportFactura(String CI_CLI, String Nombre_cli, String ruc_Prov, String nombre_prov, 
            String dir_prov, String codigo, String Fecha, String totalSinIVa, String IVA, String TotalconIva) {
        try {
            DataOutputStream out = new DataOutputStream(new FileOutputStream(file));
            WritableWorkbook w = Workbook.createWorkbook(out);
            for (int index = 0; index < tabla.size(); index++) {
                JTable table = tabla.get(index);
                WritableSheet s = w.createSheet(nom_files.get(index), 0);
                
                s.addCell(new Label(0, 0,"REPORTE FACTURA"));
                s.addCell(new Label(0, 1,""));
                s.addCell(new Label(0,2,"Datos Cliente"));                
                s.addCell(new Label(0,3,"Nombre: "));
                s.addCell(new Label(1,3,Nombre_cli));               
                s.addCell(new Label(2,3,"CI/RUC: "));                    
                s.addCell(new Label(3,3,CI_CLI));               
                s.addCell(new Label(0,4,""));
                s.addCell(new Label(0,5,"Datos Proveedor"));
                s.addCell(new Label(0,6,"Nombre: "));
                s.addCell(new Label(1,6,nombre_prov));                
                s.addCell(new Label(2,6,"RUC: "));                    
                s.addCell(new Label(3,6,ruc_Prov));               
                s.addCell(new Label(0,7,"Dirección: "));               
                s.addCell(new Label(1,7,dir_prov));
                s.addCell(new Label(0,8,""));                
                s.addCell(new Label(0,9,"Datos Factura"));
                s.addCell(new Label(0,10,"Codigo: "));
                s.addCell(new Label(1,10,codigo));                
                s.addCell(new Label(2,10,"Fecha: "));                    
                s.addCell(new Label(3,10,Fecha));                               
                
                for (int i = 0; i < table.getColumnCount(); i++) {
                    String NomCol = table.getColumnName(i);
                    System.out.println(NomCol);
                    s.addCell(new Label(i, 12, NomCol));
                    for (int j = 0; j < table.getRowCount(); j++) {
                        Object object = table.getValueAt(j, i);
                        s.addCell(new Label(i, j + 13, String.valueOf(object)));
                    }
                }
                
                s.addCell(new Label(0, table.getRowCount() + 14,"Total sin IVA: "));
                s.addCell(new Label(1, table.getRowCount() + 14, totalSinIVa));
                s.addCell(new Label(0, table.getRowCount() + 15,"IVA: "));
                s.addCell(new Label(1, table.getRowCount() + 15, IVA));
                s.addCell(new Label(0, table.getRowCount() + 16,"Total con IVA: "));
                s.addCell(new Label(1, table.getRowCount() + 16, TotalconIva));
            }
            w.write();
            w.close();
            out.flush();
            out.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
