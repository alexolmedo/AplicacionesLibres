/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import conexionBDD.Conexionn;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableModel;

/**
 *
 * @author andreu
 */
public class SeleccionarTipoGastoPersonal extends javax.swing.JFrame {

    final JComboBox comboBox;
    JTable tablaProductos;
    String tipoEstado[];

    String evtTipo = "";
    int filaTipo = -1;

    Conexionn conTipo;
    String numFac;
    int anio;
    String cedula, tipo;

    /**
     * Creates new form SeleccionarTipoGasto
     *
     * @param conn
     * @param tipos
     * @param factura
     * @param anio
     * @param cedula
     * @param tipo
     */
    public SeleccionarTipoGastoPersonal(Conexionn conn, Object[][] tipos, String factura, int anio, 
            String cedula, String tipo, String nombrCli, String id_Cli, String RUC,String NomComercial, String Direccion, String Codigo, String fecha) {
        initComponents();
        this.conTipo = conn;
        this.numFac = factura;
        this.anio = anio;
        this.cedula = cedula;
        this.tipo = tipo;

        String nombreCabeceras[] = {"Descripcion", "Precio Total", "Tipo de Gasto"};

        tipoEstado = new String[tipos.length];
        for (int i = 0; i < tipos.length; i++) {
            tipoEstado[i] = "";
        }
        
        //Datos Cliente
        RUC_CI_Cli.setText(id_Cli);
        nombre_Cli.setText(nombrCli);
        
        //Datos Proveedor
        RUC_PRov.setText(RUC);
        nomb_Prov.setText(NomComercial);
        dir_Prov.setText(Direccion);
        
        //Datos Factura
        cod.setText(Codigo);
        this.fecha.setText(fecha);
        
        tablaProductos = new JTable(tipos, nombreCabeceras){
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 2;
            }
        };
        jScrollPane1.setViewportView(tablaProductos);

        comboBox = new JComboBox();
        comboBox.addItem("");

        comboBox.addItem("Vivienda");
        comboBox.addItem("Salud");
        comboBox.addItem("Educacion");
        comboBox.addItem("Alimentacion");
        comboBox.addItem("Vestimenta");
        comboBox.addItem("Otro");

        tablaProductos.getModel().addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent tme) {
                int row = tme.getFirstRow();
                int column = tme.getColumn();

                TableModel model = (TableModel) tme.getSource();
                Object data = model.getValueAt(row, column);

                if (!data.equals("") && column == 2) {
                    //int opc = comboBox.getSelectedIndex();
                    //System.out.println(row);

                    if (!tipoEstado[row].equals("")) {
                        if (tipoEstado[row].equals("Vivienda")) {
                            restarAgregado(txtVivienda, row);
                        }
                        if (tipoEstado[row].equals("Salud")) {
                            restarAgregado(txtSalud, row);
                        }
                        if (tipoEstado[row].equals("Educacion")) {
                            restarAgregado(txtEducacion, row);
                        }
                        if (tipoEstado[row].equals("Alimentacion")) {
                            restarAgregado(txtAlimentacion, row);
                        }
                        if (tipoEstado[row].equals("Vestimenta")) {
                            restarAgregado(txtVestimenta, row);
                        }
                        if (tipoEstado[row].equals("Otro")) {
                            restarAgregado(txtOtro, row);
                        }
                    }

                    if (data.equals("Vivienda")) {
                        sumarAgregado(txtVivienda, row, "Vivienda");
                    }
                    if (data.equals("Salud")) {
                        sumarAgregado(txtSalud, row, "Salud");
                    }
                    if (data.equals("Educacion")) {
                        sumarAgregado(txtEducacion, row, "Educacion");
                    }
                    if (data.equals("Alimentacion")) {
                        sumarAgregado(txtAlimentacion, row, "Alimentacion");
                    }
                    if (data.equals("Vestimenta")) {
                        sumarAgregado(txtVestimenta, row, "Vestimenta");
                    }
                    if (data.equals("Otro")) {
                        sumarAgregado(txtOtro, row, "Otro");
                    }
                }

            }
        });

        DefaultTableCellRenderer alinearDerecha = new DefaultTableCellRenderer();
        alinearDerecha.setHorizontalAlignment(DefaultTableCellRenderer.RIGHT);
        tablaProductos.getColumnModel().getColumn(1).setCellRenderer(alinearDerecha);

        tablaProductos.getColumnModel().getColumn(2).setCellEditor(new DefaultCellEditor(comboBox));

        tablaProductos.getColumnModel().getColumn(1).setMinWidth(100);
        tablaProductos.getColumnModel().getColumn(1).setMaxWidth(100);
        tablaProductos.getColumnModel().getColumn(2).setMinWidth(150);
        tablaProductos.getColumnModel().getColumn(2).setMaxWidth(150);

        setLocationRelativeTo(getParent());
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jButton1 = new javax.swing.JButton();
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
        jPanel2 = new javax.swing.JPanel();
        lbCodigo = new javax.swing.JLabel();
        lbCodigo1 = new javax.swing.JLabel();
        RUC_PRov = new javax.swing.JTextField();
        nomb_Prov = new javax.swing.JTextField();
        lbCodigo6 = new javax.swing.JLabel();
        dir_Prov = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        lbCodigo2 = new javax.swing.JLabel();
        lbCodigo3 = new javax.swing.JLabel();
        RUC_CI_Cli = new javax.swing.JTextField();
        nombre_Cli = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        lbCodigo4 = new javax.swing.JLabel();
        lbCodigo5 = new javax.swing.JLabel();
        cod = new javax.swing.JTextField();
        fecha = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("SELECCIONAR TIPO DE GASTO");

        jButton1.setText("Aceptar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        lblSalud.setText("Salud");

        lblEducacion.setText("Educacion");

        lblAlimentacion.setText("Alimentacion");

        txtVivienda.setEditable(false);
        txtVivienda.setText("0.0");

        txtSalud.setEditable(false);
        txtSalud.setText("0.0");

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
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lblVivienda)
                        .addGap(95, 95, 95)
                        .addComponent(txtVivienda, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(65, 65, 65)
                        .addComponent(lblAlimentacion))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblEducacion)
                            .addComponent(lblSalud))
                        .addGap(85, 85, 85)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtSalud, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtEducacion, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(69, 69, 69)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblOtro)
                            .addComponent(lblVestimenta))))
                .addGap(56, 56, 56)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtVestimenta, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtOtro, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtAlimentacion, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(27, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtVivienda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtAlimentacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblVivienda)
                            .addComponent(lblAlimentacion))))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblSalud)
                            .addComponent(lblVestimenta)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(txtVestimenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(txtSalud, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtEducacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblOtro)
                        .addComponent(txtOtro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(lblEducacion)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

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
                        .addComponent(RUC_PRov, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(47, 47, 47)
                        .addComponent(lbCodigo1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(nomb_Prov, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(lbCodigo6)
                        .addGap(36, 36, 36)
                        .addComponent(dir_Prov)))
                .addGap(43, 43, 43))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbCodigo1)
                    .addComponent(nomb_Prov, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(RUC_PRov, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbCodigo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(dir_Prov, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbCodigo6))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                .addComponent(cod, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jButton1))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 573, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        int filasTotales = tablaProductos.getRowCount();
        boolean validado = true;

        for (int i = 0; i < filasTotales; i++) {
            if (tablaProductos.getValueAt(i, 2).equals("")) {
                validado = false;
                break;
            }
        }

        if (validado == true) {
            String query;

            double totales[] = {0.0, 0.0, 0.0, 0.0, 0.0, 0.0};

            if (!txtVivienda.getText().equals("0.0")) {
                totales[0] = ingresarTipo(txtVivienda, lblVivienda);
            }
            if (!txtSalud.getText().equals("0.0")) {
                totales[1] = ingresarTipo(txtSalud, lblSalud);
            }
            if (!txtEducacion.getText().equals("0.0")) {
                totales[2] = ingresarTipo(txtEducacion, lblEducacion);
            }
            if (!txtAlimentacion.getText().equals("0.0")) {
                totales[3] = ingresarTipo(txtAlimentacion, lblAlimentacion);
            }
            if (!txtVestimenta.getText().equals("0.0")) {
                totales[4] = ingresarTipo(txtVestimenta, lblVestimenta);
            }
            if (!txtOtro.getText().equals("0.0")) {
                totales[5] = ingresarTipo(txtOtro, lblOtro);
            }

            if (conTipo.verificar_usuario("SELECT * FROM HISTORIAL_PAGOS_PERSONALES WHERE anio_historial_p=" + anio + " AND id_cliente='" + cedula + "'")) {
                query = "UPDATE HISTORIAL_PAGOS_PERSONALES SET total_alimentacion=total_alimentacion+" + totales[3] + "::money,"
                        + "total_salud=total_salud+" + totales[1] + "::money,"
                        + "total_vivienda=total_vivienda+" + totales[0] + "::money,"
                        + "total_educacion=total_educacion+" + totales[2] + "::money,"
                        + "total_vestimenta=total_vestimenta+" + totales[4] + "::money,"
                        + "total_otros=total_otros+" + totales[5] + "::money WHERE anio_historial_p=" + anio + " AND id_cliente='" + cedula + "'";
            } else {
                query = "INSERT INTO HISTORIAL_PAGOS_PERSONALES VALUES (" + anio + ",'" + cedula + "'," + totales[3] + "," + totales[1] + "," + totales[0] + "," + totales[2] + "," + totales[4] + "," + totales[5] + ")";
            }

            conTipo.insertar(query);

            JOptionPane.showMessageDialog(this, "Factura ingresada exitosamente");
            recargar(conTipo);
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(this, "No se ha seleccionado el tipo para cada producto");
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void recargar(Conexionn conn) {
         ArrayList auxRec = new ArrayList();
        Interfaces.FacturaManualPersonal.combo_Establecimientos.removeAllItems();
        Interfaces.FacturaManualNegocio.combo_Establecimientos.removeAllItems();
        Interfaces.FacturaManualPersonal.combo_Establecimientos.addItem("");
        Interfaces.FacturaManualNegocio.combo_Establecimientos.addItem("");
        auxRec = conn.cargarEstablecimiento();
        for (Object est : auxRec) {            
            Interfaces.FacturaManualPersonal.combo_Establecimientos.addItem(est.toString());
            Interfaces.FacturaManualNegocio.combo_Establecimientos.addItem(est.toString());
        }
    }
    
    public void restarAgregado(JTextField txtField, int row) {
        double total;
        total = Double.parseDouble(txtField.getText());
        total -= (Double) tablaProductos.getValueAt(row, 1);
        total = BigDecimal.valueOf(total).setScale(3, RoundingMode.HALF_UP).doubleValue();
        txtField.setText(String.valueOf(total));
    }

    public void sumarAgregado(JTextField txtField, int row, String tipo) {
        double total;
        total = Double.parseDouble(txtField.getText());
        total += (Double) tablaProductos.getValueAt(row, 1);
        total = BigDecimal.valueOf(total).setScale(3, RoundingMode.HALF_UP).doubleValue();
        txtField.setText(String.valueOf(total));
        tipoEstado[row] = tipo;
    }

    public double ingresarTipo(JTextField txtField, JLabel lblLabel) {
        double total;
        String query;

        total = Double.parseDouble(txtField.getText());
        total = BigDecimal.valueOf(total).setScale(3, RoundingMode.HALF_UP).doubleValue();

        query = "INSERT INTO TIPO_GASTO (id_factura,tipo,total)"
                + "VALUES('" + numFac + "','" + lblLabel.getText() + "'," + total + ")";

        conTipo.insertar(query);

        return total;
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
            java.util.logging.Logger.getLogger(SeleccionarTipoGastoPersonal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SeleccionarTipoGastoPersonal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SeleccionarTipoGastoPersonal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SeleccionarTipoGastoPersonal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                // new SeleccionarTipoGasto().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField RUC_CI_Cli;
    private javax.swing.JTextField RUC_PRov;
    private javax.swing.JTextField cod;
    private javax.swing.JTextField dir_Prov;
    private javax.swing.JTextField fecha;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
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
    private javax.swing.JTextField txtAlimentacion;
    private javax.swing.JTextField txtEducacion;
    private javax.swing.JTextField txtOtro;
    private javax.swing.JTextField txtSalud;
    private javax.swing.JTextField txtVestimenta;
    private javax.swing.JTextField txtVivienda;
    // End of variables declaration//GEN-END:variables
}