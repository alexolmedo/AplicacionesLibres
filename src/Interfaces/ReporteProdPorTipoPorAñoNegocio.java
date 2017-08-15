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
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author vengatus
 */
public class ReporteProdPorTipoPorAñoNegocio extends javax.swing.JInternalFrame {

    Conexionn conn;
    String cedula_usuario;
    int anio;
    //JTable auxP, auxN;

    public ReporteProdPorTipoPorAñoNegocio(Conexionn conn, String cedula_usuario, int anio) {
        initComponents();
        //setIconImage(new ImageIcon(getClass().getResource("/Imagenes/ico_21-1.png")).getImage());
        //Aceptar.setVisible(false);
        this.conn = conn;
        this.cedula_usuario = cedula_usuario;
        this.anio = anio;
        this.tablaProv.setVisible(false);
        //this.comboProv1.setEnabled(false);
        //cargar_Tipo();
        BasicInternalFrameUI ui = (BasicInternalFrameUI) this.getUI();
        Component north = ui.getNorthPane();
        MouseMotionListener[] actions
                = (MouseMotionListener[]) north.getListeners(MouseMotionListener.class);
        for (MouseMotionListener action : actions) {
            north.removeMouseMotionListener(action);
        }
        
        String q2 = "SELECT ID_USUARIO,COUNT(ID_USUARIO) FROM TIPO_GASTO_NEG WHERE ID_USUARIO = '" + cedula_usuario + "' GROUP BY ID_USUARIO";
        ArrayList num =  conn.ddl(q2);
        System.out.println("num "+num);
        int longitud = Integer.parseInt(num.get(1).toString());
        
        for (int i = 1; i <longitud; i++) {

            //String query = "SELECT TIPO_GASTO FROM TIPO_GASTO_NEG WHERE ID_USUARIO = '" + cedula + "'";
            Object[] n = conn.cargarTipoGasNegocio(cedula_usuario).toArray();
            for (int j = 0; j<n.length; j++){
                System.out.println(">>>> " +n[j]);
                comboProv.addItem(n[j].toString());
            }
            //comboBox.addItem(n[]);
        }
    }

    public void cargar_Tipo() {
        comboProv.removeAllItems();
        comboProv.addItem("---Todos los proveedores---");
        ArrayList proov = conn.cargarEstablecimiento();
        for (Object obj : proov) {
            comboProv.addItem(obj.toString());
            //System.out.println(obj.toString());
        }
    }
    
    public void cargarDato(String sfd) {
    
        nom_cli.setText(sfd);
        CI.setText(cedula_usuario);
    }
    
    public void cargarTabla() {
        tablaProv.setVisible(true);
        Statement st;
        try {
            st = conn.getConn().createStatement();
            String c = String.format("select nombre_producto, nombre_establecimiento, sum(total)  from (select substr(cast(fecha_emision as char),7), id_factura, id_establecimiento\n" +
                    "from factura where id_cliente = '%s' and (select substr(cast(fecha_emision as char),7)) = '%s') as T1 "
                    + "join detalle on (T1.id_factura = detalle.id_factura) join\n" +
                    "establecimiento on (T1.id_establecimiento = establecimiento.id_establecimiento) where tipo = '%s' group by nombre_producto", cedula_usuario, anio, comboProv.getSelectedItem().toString());            
            ResultSet rs = st.executeQuery(c);
            System.out.println(c);
            ResultSetMetaData rsMd = rs.getMetaData();
            DefaultTableModel dm = (DefaultTableModel) tablaProv.getModel();
            
            int numeroColumnas = rsMd.getColumnCount();
            //System.out.println("estoy en dfdfg" + rs.getString(0));

            int i = 0;
            while (rs.next()) {
                dm.addRow(new Object[]{"", "", ""});
                System.out.println("estoy en el while");
                for (int j = 0; j < numeroColumnas; j++) {                    
                    tablaProv.setValueAt(rs.getObject(j + 1),i ,j );                    
                    System.out.println(rs.getObject(j + 1));
                }
                i++;
            }

        } catch (SQLException ex) {
            Logger.getLogger(ReporteProdPorTipoPorAñoNegocio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void limpiarTabla () {
            
        DefaultTableModel dm = (DefaultTableModel) tablaProv.getModel();
        int rowCount = dm.getRowCount();
        //Remove rows one by one from the end of the table
        for (int i = rowCount - 1; i >= 0; i--) {
            dm.removeRow(i);
        }
   
    }
    
    public void cargarTablaP() {
        
        tablaProv.setVisible(true);
        ArrayList idEstab = conn.ddl(String.format("select id_establecimiento from establecimiento where nombre_establecimiento='%s'", comboProv.getSelectedItem().toString()));
        
        Statement st;
        try {
            st = conn.getConn().createStatement();
            String c = String.format("select nombre_producto, nombre_establecimiento, sum(total)  from (select substr(cast(fecha_emision as char),7), id_factura, id_establecimiento\n" +
                    "from factura where id_cliente = '%s' and (select substr(cast(fecha_emision as char),7)) = '%s') as T1 "
                    + "join detalle on (T1.id_factura = detalle.id_factura) join\n" +
                    "establecimiento on (T1.id_establecimiento = establecimiento.id_establecimiento) group by nombre_producto", cedula_usuario, anio);            
            ResultSet rs = st.executeQuery(c);
            System.out.println(c);
            ResultSetMetaData rsMd = rs.getMetaData();
            DefaultTableModel dm = (DefaultTableModel) tablaProv.getModel();
            int numeroColumnas = rsMd.getColumnCount();
            //System.out.println("estoy en dfdfg" + rs.getString(0));

            int i = 0;
            while (rs.next()) {
                dm.addRow(new Object[]{"", "", ""});
                System.out.println("estoy en el while");
                for (int j = 0; j < numeroColumnas; j++) {                        
                    
                    tablaProv.setValueAt(rs.getObject(j + 1),i ,j );                    
                    System.out.println(rs.getObject(j + 1));
                }
                i++;
            }

        } catch (SQLException ex) {
            Logger.getLogger(ReporteProdPorTipoPorAñoNegocio.class.getName()).log(Level.SEVERE, null, ex);
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
        nom_cli = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        CI = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        comboProv = new javax.swing.JComboBox<>();
        botonExcel = new javax.swing.JButton();
        botonPdf = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setEnabled(false);
        setPreferredSize(new java.awt.Dimension(575, 370));
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                formComponentShown(evt);
            }
        });

        lbl_Reporte.setFont(new java.awt.Font("Open Sans", 1, 36)); // NOI18N
        lbl_Reporte.setText("Reporte Productos por Tipo de Gasto ");

        tablaProv.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tablaProv.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nomb Producto", "Nomb Proveedor", "Total"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tablaProv.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaProvMouseClicked(evt);
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

        nom_cli.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        nom_cli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nom_cliActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setText("CI");

        CI.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        CI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CIActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel5.setText("Tipo de Gasto");

        comboProv.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        comboProv.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todos" }));
        comboProv.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                comboProvPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        botonExcel.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        botonExcel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/if_application-xml_28904.png"))); // NOI18N
        botonExcel.setText("Exportar Excel");
        botonExcel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonExcelActionPerformed(evt);
            }
        });

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
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(botonPdf)
                        .addGap(18, 18, 18)
                        .addComponent(botonExcel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1))
                    .addComponent(jScrollPane2)
                    .addComponent(lbl_Reporte)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel3))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(comboProv, 0, 240, Short.MAX_VALUE)
                            .addComponent(nom_cli))
                        .addGap(73, 73, 73)
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addComponent(CI)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbl_Reporte)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(nom_cli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(CI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(comboProv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(46, 46, 46)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(botonExcel)
                        .addComponent(botonPdf)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

    private void nom_cliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nom_cliActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nom_cliActionPerformed

    private void CIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CIActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CIActionPerformed

    private void comboProvPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_comboProvPopupMenuWillBecomeInvisible
        // TODO add your handling code here:
        //System.out.println("Seleccionado:" +comboProv.getSelectedIndex());
        limpiarTabla();
        if (comboProv.getSelectedItem().toString().equals("Todos")){
            cargarTablaP();
        }else{
            cargarTabla();
        }
        
    }//GEN-LAST:event_comboProvPopupMenuWillBecomeInvisible

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

    private void tablaProvMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaProvMouseClicked
        System.out.println("año seleccionado "+tablaProv.getValueAt(tablaProv.getSelectedRow(), 0).toString());
        VentanaPrincipal vent = (VentanaPrincipal) SwingUtilities.getWindowAncestor(this);
        vent.reporteDetalleProveedor(tablaProv.getValueAt(tablaProv.getSelectedRow(), 1).toString());
    }//GEN-LAST:event_tablaProvMouseClicked

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
            java.util.logging.Logger.getLogger(ReporteProdPorTipoPorAñoNegocio.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ReporteProdPorTipoPorAñoNegocio.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ReporteProdPorTipoPorAñoNegocio.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ReporteProdPorTipoPorAñoNegocio.class
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
    private javax.swing.JComboBox<String> comboProv;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lbl_Reporte;
    private javax.swing.JTextField nom_cli;
    private javax.swing.JTable tablaProv;
    // End of variables declaration//GEN-END:variables
}
