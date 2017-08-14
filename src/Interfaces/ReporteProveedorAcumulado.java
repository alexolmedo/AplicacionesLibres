/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import ReportExc.Exporter;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import conexionBDD.Conexionn;
import groovy.sql.ResultSetMetaDataWrapper;
import java.awt.Component;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author vengatus
 */
public class ReporteProveedorAcumulado extends javax.swing.JInternalFrame {

    Conexionn conn;
    String cedula_usuario;
    int anio;
    //JTable auxP, auxN;

    public ReporteProveedorAcumulado(Conexionn conn, String cedula_usuario, int anio) {
        initComponents();
        //setIconImage(new ImageIcon(getClass().getResource("/Imagenes/ico_21-1.png")).getImage());
        //Aceptar.setVisible(false);
        this.conn = conn;
        this.cedula_usuario = cedula_usuario;
        this.anio = anio;
        this.tablaProv.setVisible(false);
        BasicInternalFrameUI ui = (BasicInternalFrameUI) this.getUI();
        Component north = ui.getNorthPane();
        MouseMotionListener[] actions
                = (MouseMotionListener[]) north.getListeners(MouseMotionListener.class);
        for (MouseMotionListener action : actions) {
            north.removeMouseMotionListener(action);
        }    
    }
    
    public void cargarDato(String sfd) {
    
        nomCli.setText(sfd);
        CI.setText(cedula_usuario);
    }
    
    public void cargarTabla(String nombreProveedor) {
        labelAnio.setText(nombreProveedor);
        tablaProv.setVisible(true);
        Statement st;
        try {
            st = conn.getConn().createStatement();
            String c = String.format("select id_establecimiento, nombre_establecimiento, count(id_establecimiento), sum(TV), sum(TE), sum(TOt), sum(TA), sum(TVes), sum(TS) from (select id_Factura, factura.id_establecimiento, nombre_establecimiento, total_sin_IVA, IVA, Total_con_iva \n" +
            "from factura join establecimiento on (factura.id_establecimiento = establecimiento.id_establecimiento) \n" +
                    "where (select substr(cast(fecha_emision as char),7) ='%s') and id_cliente = '%s' and establecimiento.nombre_establecimiento='%s') as Tab1\n" +
                    "join (select t1.id_factura, TV, TE, TOt, TA, TVes, TS \n" +
                    "from (select id_factura, total as 'TV' from tipo_gasto where tipo = 'Vivienda') as t1\n" +
                    "join (select id_factura, total as 'TE' from tipo_gasto where tipo = 'Educacion') as t2 \n" +
                    "on (t1.id_factura = t2.id_factura)\n" +
                    "join (select id_factura, total as 'TOt' from tipo_gasto where tipo = 'Otro') as t3 \n" +
                    "on (t3.id_factura = t2.id_factura)\n" +
                    "join (select id_factura, total as 'TA' from tipo_gasto where tipo = 'Alimentacion') as t4 \n" +
                    "on (t4.id_factura = t3.id_factura)\n" +
                    "join (select id_factura, total as 'TVes' from tipo_gasto where tipo = 'Vestimenta') as t5 \n" +
                    "on (t5.id_factura = t4.id_factura)\n" +
                    "join (select id_factura, total as 'TS' from tipo_gasto where tipo = 'Salud') as t6 \n" +
                    "on (t6.id_factura = t5.id_factura)) as Tab2 on (Tab1.id_factura = tab2.id_Factura) group by id_establecimiento",anio, cedula_usuario, nombreProveedor);            
            ResultSet rs = st.executeQuery(c);
            System.out.println(c);
            ResultSetMetaData rsMd = rs.getMetaData();
            int numeroColumnas = rsMd.getColumnCount();
            //System.out.println("estoy en dfdfg" + rs.getString(0));
            
            DefaultTableModel dm = (DefaultTableModel) tablaProv.getModel();
            int i = 0;
            while (rs.next()) {
                //System.out.println("estoy en el while");
                dm.addRow(new Object[]{"", "", ""});
                for (int j = 0; j < numeroColumnas; j++) {                    
                    tablaProv.setValueAt(rs.getObject(j + 1),i ,j );                    
                    //System.out.println(rs.getObject(j + 1));
                }
                i++;
            }

        } catch (SQLException ex) {
            Logger.getLogger(ReporteProveedor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbl_Reporte = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaProv = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        CI = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        nomCli = new javax.swing.JTextField();
        botonExcel = new javax.swing.JButton();
        labelAnio = new javax.swing.JLabel();
        botonPdf = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setEnabled(false);
        setPreferredSize(new java.awt.Dimension(1035, 410));
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                formComponentShown(evt);
            }
        });

        lbl_Reporte.setFont(new java.awt.Font("Open Sans", 1, 36)); // NOI18N
        lbl_Reporte.setText("Acumulado Facturas del proveedor ");

        tablaProv.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "RUC Prov", "Nomb Prov", "Num Fac", "T. Vivienda", "T. Educacion", "T. Otro", "T. Alimentacion", "T. Vestimenta", "T. Salud"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tablaProv);

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/cancel.png"))); // NOI18N
        jButton1.setText("Cancelar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setText("Cliente:");

        CI.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel5.setText("CI");

        nomCli.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        botonExcel.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        botonExcel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/if_application-xml_28904.png"))); // NOI18N
        botonExcel.setText("Exportar Excel");
        botonExcel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonExcelActionPerformed(evt);
            }
        });

        labelAnio.setFont(new java.awt.Font("Open Sans", 0, 36)); // NOI18N
        labelAnio.setText("xyz");

        botonPdf.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        botonPdf.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/if_19_1737236.png"))); // NOI18N
        botonPdf.setText("Exportar Pdf");
        botonPdf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonPdfActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(lbl_Reporte)
                                .addGap(18, 18, 18)
                                .addComponent(labelAnio)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(16, 16, 16)
                                .addComponent(jLabel3)
                                .addGap(18, 18, 18)
                                .addComponent(nomCli)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel5)))
                        .addGap(18, 18, 18)
                        .addComponent(CI, javax.swing.GroupLayout.PREFERRED_SIZE, 354, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(botonPdf)
                        .addGap(18, 18, 18)
                        .addComponent(botonExcel)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton1)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_Reporte)
                    .addComponent(labelAnio))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(nomCli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(CI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botonExcel)
                    .addComponent(botonPdf))
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
        String[] datoi;
        

    }//GEN-LAST:event_formComponentShown

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void botonExcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonExcelActionPerformed
        // TODO add your handling code here:
        if (tablaProv.getRowCount() > 0) {
            JFileChooser chooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos de excel", "xls");
            chooser.setFileFilter(filter);
            chooser.setDialogTitle("Guardar archivo");
            chooser.setAcceptAllFileFilterUsed(false);
            if (chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {                
                List<JTable> tb = new ArrayList<>();
                List<String> nom = new ArrayList<>();
                tb.add(tablaProv);
                nom.add("Compras por factura");
                String file = chooser.getSelectedFile().toString().concat(".xls");
                try {
                    Exporter e = new Exporter(new File(file), tb, nom);
                    if (e.export()) {
                        JOptionPane.showMessageDialog(null, "Los datos fueron exportados a excel en el directorio seleccionado", "Mensaje de Informacion", JOptionPane.INFORMATION_MESSAGE);
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Hubo un error " + e.getMessage(), " Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }else{
            JOptionPane.showMessageDialog(this, "No hay datos para exportar","Mensaje de error",JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_botonExcelActionPerformed

    private void botonPdfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonPdfActionPerformed
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos pdf", "pdf");
        chooser.setFileFilter(filter);
        chooser.setDialogTitle("Guardar archivo");
        chooser.setAcceptAllFileFilterUsed(false);
        
        if (chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {  
        
            String file = chooser.getSelectedFile().toString().concat(".pdf");
            
            try{
            Document doc = new Document(PageSize.A4.rotate());
            PdfWriter.getInstance(doc, new FileOutputStream(new File(file)));
            doc.open();
            PdfPTable pdfTable = new PdfPTable(tablaProv.getColumnCount());
            for (int i = 0; i < tablaProv.getColumnCount(); i++) {
                    pdfTable.addCell(tablaProv.getColumnName(i));
                }
                //extracting data from the JTable and inserting it to PdfPTable
                for (int rows = 0; rows < tablaProv.getRowCount(); rows++) {
                    for (int cols = 0; cols < tablaProv.getColumnCount(); cols++) {
                        pdfTable.addCell(tablaProv.getModel().getValueAt(rows, cols).toString());

                    }
                }
                doc.add(pdfTable);
                doc.close();
                JOptionPane.showMessageDialog(null, "Los datos fueron exportados a pdf en el directorio seleccionado", "Mensaje de Informacion", JOptionPane.INFORMATION_MESSAGE);

            } catch (DocumentException ex) {

            } catch (FileNotFoundException ex) {

            }
        }
    }//GEN-LAST:event_botonPdfActionPerformed

    public void toExcel(JTable table, File file) {
        try {
            TableModel model = table.getModel();
            FileWriter excel = new FileWriter(file);

            for (int i = 0; i < model.getColumnCount(); i++) {
                excel.write(model.getColumnName(i) + "\t");
            }

            excel.write("\n");

            for (int i = 0; i < model.getRowCount(); i++) {
                for (int j = 0; j < model.getColumnCount(); j++) {
                    excel.write(model.getValueAt(i, j).toString() + "\t");
                }
                excel.write("\n");
            }

            excel.close();

        } catch (IOException e) {
            System.out.println(e);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;

                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ReporteProveedorAcumulado.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ReporteProveedorAcumulado.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ReporteProveedorAcumulado.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ReporteProveedorAcumulado.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField CI;
    private javax.swing.JButton botonExcel;
    private javax.swing.JButton botonPdf;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel labelAnio;
    private javax.swing.JLabel lbl_Reporte;
    private javax.swing.JTextField nomCli;
    private javax.swing.JTable tablaProv;
    // End of variables declaration//GEN-END:variables
}
