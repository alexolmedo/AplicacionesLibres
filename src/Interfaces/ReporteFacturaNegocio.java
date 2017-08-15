package Interfaces;

import ReportExc.Exporter;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import conexionBDD.Conexionn;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

public class ReporteFacturaNegocio extends javax.swing.JFrame {
    static String codFactura;
    Conexionn conn;

    public ReporteFacturaNegocio(String codFactura) {
        
        conn=new Conexionn();
        this.codFactura=codFactura;
        initComponents();
        cod.setText(codFactura);
        setDatos(); 
        setLocationRelativeTo(null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        lbCodigo = new javax.swing.JLabel();
        lbCodigo1 = new javax.swing.JLabel();
        RUC_PRov = new javax.swing.JTextField();
        nomb_Prov = new javax.swing.JTextField();
        lbCodigo6 = new javax.swing.JLabel();
        dir_Prov = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        lbCodigo4 = new javax.swing.JLabel();
        lbCodigo5 = new javax.swing.JLabel();
        cod = new javax.swing.JTextField();
        fecha = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        lbCodigo2 = new javax.swing.JLabel();
        lbCodigo3 = new javax.swing.JLabel();
        RUC_CI_Cli = new javax.swing.JTextField();
        nombre_Cli = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaDetalle = new javax.swing.JTable();
        botonCancelar = new javax.swing.JButton();
        botonExcel = new javax.swing.JButton();
        botonPdf = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        lblSalud = new javax.swing.JLabel();
        lblEducacion = new javax.swing.JLabel();
        lblAlimentacion = new javax.swing.JLabel();
        txtVivienda = new javax.swing.JTextField();
        txtSalud = new javax.swing.JTextField();
        txtEducacion = new javax.swing.JTextField();
        txtAlimentacion = new javax.swing.JTextField();
        txtVestimenta = new javax.swing.JTextField();
        txtOtro = new javax.swing.JTextField();
        lblVestimenta = new javax.swing.JLabel();
        lblVivienda = new javax.swing.JLabel();
        lblOtro = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        totalSinIVA = new javax.swing.JTextField();
        totalConIVA = new javax.swing.JTextField();
        IVA = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos Proveedor"));

        lbCodigo.setText("RUC");

        lbCodigo1.setText("Nombre");

        RUC_PRov.setEditable(false);

        nomb_Prov.setEditable(false);

        lbCodigo6.setText("Direccion");

        dir_Prov.setEditable(false);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(lbCodigo)
                        .addGap(58, 58, 58)
                        .addComponent(RUC_PRov, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbCodigo1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nomb_Prov, javax.swing.GroupLayout.PREFERRED_SIZE, 388, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(lbCodigo6)
                        .addGap(36, 36, 36)
                        .addComponent(dir_Prov)))
                .addGap(43, 43, 43))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbCodigo1)
                    .addComponent(nomb_Prov, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(RUC_PRov, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbCodigo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(dir_Prov, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbCodigo6)))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Detalle Factura"));

        lbCodigo4.setText("Código");

        lbCodigo5.setText("Fecha");

        cod.setEditable(false);

        fecha.setEditable(false);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbCodigo4)
                .addGap(37, 37, 37)
                .addComponent(cod, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbCodigo5)
                .addGap(64, 64, 64)
                .addComponent(fecha, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbCodigo5)
                    .addComponent(fecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbCodigo4))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos Cliente"));

        lbCodigo2.setText("RUC / CI");

        lbCodigo3.setText("Nombre");

        RUC_CI_Cli.setEditable(false);

        nombre_Cli.setEditable(false);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbCodigo2)
                .addGap(37, 37, 37)
                .addComponent(RUC_CI_Cli, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48)
                .addComponent(lbCodigo3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 291, Short.MAX_VALUE)
                .addComponent(nombre_Cli, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbCodigo3)
                    .addComponent(nombre_Cli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(RUC_CI_Cli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbCodigo2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel1.setText("FACTURA NEGOCIO");

        tablaDetalle.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Producto", "Total", "Tipo"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tablaDetalle);

        botonCancelar.setText("Cancelar");
        botonCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonCancelarActionPerformed(evt);
            }
        });

        botonExcel.setText("Exportar Excel");
        botonExcel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonExcelActionPerformed(evt);
            }
        });

        botonPdf.setText("Exportar Pdf");
        botonPdf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonPdfActionPerformed(evt);
            }
        });

        lblSalud.setText("Salud");

        lblEducacion.setText("Educacion");

        lblAlimentacion.setText("Alimentacion");

        txtVivienda.setEditable(false);
        txtVivienda.setText("0.0");

        txtSalud.setEditable(false);
        txtSalud.setText("0.0");
        txtSalud.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSaludActionPerformed(evt);
            }
        });

        txtEducacion.setEditable(false);
        txtEducacion.setText("0.0");

        txtAlimentacion.setEditable(false);
        txtAlimentacion.setText("0.0");

        txtVestimenta.setEditable(false);
        txtVestimenta.setText("0.0");

        txtOtro.setEditable(false);
        txtOtro.setText("0.0");

        lblVestimenta.setText("Vestimenta");

        lblVivienda.setText("Vivienda");

        lblOtro.setText("Otro");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblVivienda)
                    .addComponent(lblSalud))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtVivienda, javax.swing.GroupLayout.DEFAULT_SIZE, 74, Short.MAX_VALUE)
                    .addComponent(txtSalud))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblAlimentacion)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(lblVestimenta)))
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtAlimentacion)
                    .addComponent(txtVestimenta, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(165, 165, 165)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblEducacion, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblOtro, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(36, 36, 36)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtEducacion, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtOtro, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblVivienda)
                            .addComponent(txtVivienda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(8, 8, 8)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblSalud)
                            .addComponent(txtSalud, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtOtro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblOtro))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtEducacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblEducacion)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtAlimentacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblAlimentacion))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblVestimenta)
                            .addComponent(txtVestimenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel2.setText("Total sin IVA");

        jLabel3.setText("IVA");

        jLabel4.setText("Total con IVA");

        totalSinIVA.setEditable(false);

        totalConIVA.setEditable(false);

        IVA.setEditable(false);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(242, 242, 242)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(IVA, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(totalSinIVA, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(totalConIVA, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(totalSinIVA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(IVA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(totalConIVA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(botonPdf)
                        .addGap(18, 18, 18)
                        .addComponent(botonExcel)
                        .addGap(18, 18, 18)
                        .addComponent(botonCancelar))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addComponent(jScrollPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botonCancelar)
                    .addComponent(botonExcel)
                    .addComponent(botonPdf))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void setDatos(){
        Statement st;
        
        try {
            st = conn.getConn().createStatement();
            String c = String.format("select factura.id_establecimiento, nombre_establecimiento, direccion_establecimiento, fecha_emision, total_sin_iva, iva, total_con_iva from factura, establecimiento where factura.id_establecimiento=establecimiento.id_establecimiento and id_factura='%s'",cod.getText());
            ResultSet rs = st.executeQuery(c);

            RUC_PRov.setText(rs.getObject(1).toString());
            nomb_Prov.setText(rs.getObject(2).toString());
            dir_Prov.setText(rs.getObject(3).toString());
            fecha.setText(rs.getObject(4).toString());
            totalSinIVA.setText(rs.getObject(5).toString());
            IVA.setText(rs.getObject(6).toString());
            totalConIVA.setText(rs.getObject(7).toString());
            
            c = String.format("select factura.id_cliente, cliente.nombre_cliente from factura, cliente where factura.id_cliente=cliente.id_cliente and id_factura='%s'",cod.getText());
            rs = st.executeQuery(c);
            RUC_CI_Cli.setText(rs.getObject(1).toString());
            nombre_Cli.setText(rs.getObject(2).toString());
            
            c = String.format("select total from tipo_gasto where tipo='Vivienda' and id_factura='%s'",cod.getText());
            rs = st.executeQuery(c);
            txtVivienda.setText(rs.getObject(1).toString());
            
            c = String.format("select total from tipo_gasto where tipo='Alimentacion' and id_factura='%s'",cod.getText());
            rs = st.executeQuery(c);
            txtAlimentacion.setText(rs.getObject(1).toString());
            
            c = String.format("select total from tipo_gasto where tipo='Otro' and id_factura='%s'",cod.getText());
            rs = st.executeQuery(c);
            txtOtro.setText(rs.getObject(1).toString());
            
            c = String.format("select total from tipo_gasto where tipo='Salud' and id_factura='%s'",cod.getText());
            rs = st.executeQuery(c);
            txtSalud.setText(rs.getObject(1).toString());
            
            c = String.format("select total from tipo_gasto where tipo='Vestimenta' and id_factura='%s'",cod.getText());
            rs = st.executeQuery(c);
            txtVestimenta.setText(rs.getObject(1).toString());
            
            c = String.format("select total from tipo_gasto where tipo='Educacion' and id_factura='%s'",cod.getText());
            rs = st.executeQuery(c);
            txtEducacion.setText(rs.getObject(1).toString());
            
            cargarTabla();
            
                        
        } catch (SQLException ex) {
            Logger.getLogger(ReporteFacturaNegocio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
        public void cargarTabla() {
        tablaDetalle.setVisible(true);
        Statement st;
        try {
            st = conn.getConn().createStatement();
            String c = String.format("select nombre_producto, total, tipo from detalle where id_factura='%s'",cod.getText());            
            ResultSet rs = st.executeQuery(c);
            //System.out.println(c);
            ResultSetMetaData rsMd = rs.getMetaData();
            int numeroColumnas = rsMd.getColumnCount();
            //System.out.println("estoy en dfdfg" + rs.getString(0));
            
            DefaultTableModel dm = (DefaultTableModel) tablaDetalle.getModel();
            int i = 0;
            while (rs.next()) {
                //System.out.println("estoy en el while");
                dm.addRow(new Object[]{"", "", ""});
                for (int j = 0; j < numeroColumnas; j++) {                    
                    tablaDetalle.setValueAt(rs.getObject(j + 1),i ,j );                    
                    //System.out.println(rs.getObject(j + 1));
                }
                i++;
            }

        } catch (SQLException ex) {
            Logger.getLogger(ReporteProveedor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void txtSaludActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSaludActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSaludActionPerformed

    private void botonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonCancelarActionPerformed
        this.dispose();
    }//GEN-LAST:event_botonCancelarActionPerformed

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
            PdfPTable pdfTable = new PdfPTable(tablaDetalle.getColumnCount());
            for (int i = 0; i < tablaDetalle.getColumnCount(); i++) {
                    pdfTable.addCell(tablaDetalle.getColumnName(i));
                }
                //extracting data from the JTable and inserting it to PdfPTable
                for (int rows = 0; rows < tablaDetalle.getRowCount(); rows++) {
                    for (int cols = 0; cols < tablaDetalle.getColumnCount(); cols++) {
                        pdfTable.addCell(tablaDetalle.getModel().getValueAt(rows, cols).toString());

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

    private void botonExcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonExcelActionPerformed
        if (tablaDetalle.getRowCount() > 0) {
            JFileChooser chooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos de excel", "xls");
            chooser.setFileFilter(filter);
            chooser.setDialogTitle("Guardar archivo");
            chooser.setAcceptAllFileFilterUsed(false);
            if (chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {                
                List<JTable> tb = new ArrayList<>();
                List<String> nom = new ArrayList<>();
                tb.add(tablaDetalle);
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
            java.util.logging.Logger.getLogger(ReporteFacturaNegocio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ReporteFacturaNegocio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ReporteFacturaNegocio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ReporteFacturaNegocio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ReporteFacturaNegocio(codFactura).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField IVA;
    private javax.swing.JTextField RUC_CI_Cli;
    private javax.swing.JTextField RUC_PRov;
    private javax.swing.JButton botonCancelar;
    private javax.swing.JButton botonExcel;
    private javax.swing.JButton botonPdf;
    private javax.swing.JTextField cod;
    private javax.swing.JTextField dir_Prov;
    private javax.swing.JTextField fecha;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbCodigo;
    private javax.swing.JLabel lbCodigo1;
    private javax.swing.JLabel lbCodigo2;
    private javax.swing.JLabel lbCodigo3;
    private javax.swing.JLabel lbCodigo4;
    private javax.swing.JLabel lbCodigo5;
    private javax.swing.JLabel lbCodigo6;
    private javax.swing.JLabel lblAlimentacion;
    private javax.swing.JLabel lblEducacion;
    private javax.swing.JLabel lblOtro;
    private javax.swing.JLabel lblSalud;
    private javax.swing.JLabel lblVestimenta;
    private javax.swing.JLabel lblVivienda;
    private javax.swing.JTextField nomb_Prov;
    private javax.swing.JTextField nombre_Cli;
    private javax.swing.JTable tablaDetalle;
    private javax.swing.JTextField totalConIVA;
    private javax.swing.JTextField totalSinIVA;
    private javax.swing.JTextField txtAlimentacion;
    private javax.swing.JTextField txtEducacion;
    private javax.swing.JTextField txtOtro;
    private javax.swing.JTextField txtSalud;
    private javax.swing.JTextField txtVestimenta;
    private javax.swing.JTextField txtVivienda;
    // End of variables declaration//GEN-END:variables
}
