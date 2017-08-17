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

}
