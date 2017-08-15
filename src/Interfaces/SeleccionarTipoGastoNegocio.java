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
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author andreu
 */
public class SeleccionarTipoGastoNegocio extends javax.swing.JFrame {

    final JComboBox comboBox;
    JTable tablaProductos;
    String tipoEstado[];

    String evtTipo = "";
    int filaTipo = -1;

    Conexionn conTipo;
    String numFac;
    int anio;
    String cedula, tipo;

    public SeleccionarTipoGastoNegocio(Conexionn conn, Object[][] tipos, String factura, int anio,
            String cedula, String tipo, String nombrCli, String id_Cli, String RUC, String NomComercial,
            String Direccion, String Codigo, String fecha, String totalS, String IVA, String totalC) {
        initComponents();
        setIconImage(new ImageIcon(getClass().getResource("/Imagenes/ico_21-1.png")).getImage());
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

        totalSinIVA.setText(totalS);
        this.IVA.setText(IVA);
        totalConIVA.setText(totalC);

        tablaProductos = new JTable(tipos, nombreCabeceras) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 2;
            }
        };
        jScrollPane1.setViewportView(tablaProductos);

        comboBox = new JComboBox();
        comboBox.addItem("");

        String q2 = "SELECT ID_USUARIO,COUNT(ID_USUARIO) FROM TIPO_GASTO_NEG WHERE ID_USUARIO = '" + cedula + "' GROUP BY ID_USUARIO";
        
        ArrayList num =  conTipo.ddl(q2);
        System.out.println("num "+num);
        int longitud = Integer.parseInt(num.get(1).toString());
        
        for (int i = 1; i <longitud; i++) {

            //String query = "SELECT TIPO_GASTO FROM TIPO_GASTO_NEG WHERE ID_USUARIO = '" + cedula + "'";
            Object[] n = conn.cargarTipoGasNegocio(cedula).toArray();
            for (int j = 0; j<n.length; j++){
                System.out.println(">>>> " +n[j]);
                comboBox.addItem(n[j]);
            }
            //comboBox.addItem(n[]);

        }

        tablaProductos.getModel().addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent tme) {
                int row = tme.getFirstRow();
                int column = tme.getColumn();

                TableModel model = (TableModel) tme.getSource();
                Object data = model.getValueAt(row, column);

                if (!data.equals("") && column == 2) {

                    if(!tipoEstado[row].equals("")){
                        
                    }
                    
                    
                    
                    /*if (!tipoEstado[row].equals("")) {
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
                    }*/
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

        for (int i = 0; i < tipos.length; i++) {
            String q = "SELECT TIPO_GASTO FROM PROV_GASTO WHERE PROVEEDOR = '" + RUC + "' and tipo_fac='Negocio'";
            ArrayList lista = conTipo.ddl(q);
            tablaProductos.setValueAt(lista.get(0), i, 2);

            if (conTipo.verificar_usuario("SELECT DESCRIPCIONRELACION FROM RELACIONGASTO WHERE descripcionrelacion='" + tablaProductos.getValueAt(i, 0) + "'")) {
                String q1 = "SELECT TIPO_GASTO FROM RELACIONGASTO WHERE descripcionrelacion='" + tablaProductos.getValueAt(i, 0) + "'";
                ArrayList n1 = conTipo.ddl(q1);
                tablaProductos.setValueAt(n1.get(0), i, 2);
            }
        }
        /*for(int i=0;i<tipos.length;i++){              
            
        }*/

        setLocationRelativeTo(getParent());
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        
        ArrayList tipoGastosNeg = conTipo.cargarTipoGasNegocio(cedula);
        DefaultTableModel dm = (DefaultTableModel) jTableTiposGasto.getModel();
            int i = 0;
            for (int l=0; l<tipoGastosNeg.size(); l++) {
                System.out.println("estoy en el while");
                dm.addRow(new Object [] {"",""});
                //for (int j = 0; j < 2; j++) {                                      
                    jTableTiposGasto.setValueAt(tipoGastosNeg.get(l).toString(),l ,0 );
                    jTableTiposGasto.setValueAt("0.0",l ,1 );
                    //System.out.println(rs.getObject(j + 1));
                //}
            }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel5 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        btnAceptar = new javax.swing.JButton();
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
        btnCancelar = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableTiposGasto = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        totalSinIVA = new javax.swing.JTextField();
        totalConIVA = new javax.swing.JTextField();
        IVA = new javax.swing.JTextField();
        Calcular = new javax.swing.JButton();

        jLabel5.setText("jLabel5");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setText("FACTURA");

        jScrollPane1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jScrollPane1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        btnAceptar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnAceptar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/if_Artboard_1_1790657.png"))); // NOI18N
        btnAceptar.setText("Aceptar");
        btnAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarActionPerformed(evt);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Datos Proveedor", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        lbCodigo.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lbCodigo.setText("RUC");

        lbCodigo1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lbCodigo1.setText("Nombre");

        RUC_PRov.setEditable(false);
        RUC_PRov.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        nomb_Prov.setEditable(false);
        nomb_Prov.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        lbCodigo6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lbCodigo6.setText("Direccion");

        dir_Prov.setEditable(false);
        dir_Prov.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lbCodigo)
                    .addComponent(lbCodigo6))
                .addGap(36, 36, 36)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dir_Prov)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(RUC_PRov, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(47, 47, 47)
                        .addComponent(lbCodigo1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(nomb_Prov, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)))
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

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Datos Cliente", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        lbCodigo2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lbCodigo2.setText("RUC / CI");

        lbCodigo3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lbCodigo3.setText("Nombre");

        RUC_CI_Cli.setEditable(false);
        RUC_CI_Cli.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        nombre_Cli.setEditable(false);
        nombre_Cli.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

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

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Datos Factura", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        lbCodigo4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lbCodigo4.setText("Código");

        lbCodigo5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lbCodigo5.setText("Fecha");

        cod.setEditable(false);
        cod.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        fecha.setEditable(false);
        fecha.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(lbCodigo4)
                .addGap(18, 18, 18)
                .addComponent(cod, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbCodigo5)
                .addGap(18, 18, 18)
                .addComponent(fecha, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
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

        btnCancelar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/cancel.png"))); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        jPanel6.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jTableTiposGasto.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jTableTiposGasto.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTableTiposGasto.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tipo de Gasto", "Total"
            }
        ));
        jScrollPane2.setViewportView(jTableTiposGasto);

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setText("Total sin IVA");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setText("IVA");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setText("Total con IVA");

        totalSinIVA.setEditable(false);
        totalSinIVA.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        totalConIVA.setEditable(false);
        totalConIVA.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        IVA.setEditable(false);
        IVA.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        Calcular.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        Calcular.setText("Calcular");
        Calcular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CalcularActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(82, 82, 82)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(IVA, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(totalSinIVA, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(totalConIVA, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39))
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(108, 108, 108)
                .addComponent(Calcular)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(totalSinIVA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(IVA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(totalConIVA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Calcular)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1)
                    .addComponent(jScrollPane1)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnAceptar)
                .addGap(47, 47, 47)
                .addComponent(btnCancelar)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAceptar)
                    .addComponent(btnCancelar))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
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
            String query, query1 = "";

            double totales[] = {0.0, 0.0, 0.0, 0.0, 0.0, 0.0};

            /*if (!txtVivienda.getText().equals("0.0")) {
                totales[0] = ingresarTipo(txtVivienda, lblVivienda);
            } else {
                IngrTipo0(lblVivienda);
            }
            if (!txtSalud.getText().equals("0.0")) {
                totales[1] = ingresarTipo(txtSalud, lblSalud);
            } else {
                IngrTipo0(lblSalud);
            }
            if (!txtEducacion.getText().equals("0.0")) {
                totales[2] = ingresarTipo(txtEducacion, lblEducacion);
            } else {
                IngrTipo0(lblEducacion);
            }
            if (!txtAlimentacion.getText().equals("0.0")) {
                totales[3] = ingresarTipo(txtAlimentacion, lblAlimentacion);
            } else {
                IngrTipo0(lblAlimentacion);
            }
            if (!txtVestimenta.getText().equals("0.0")) {
                totales[4] = ingresarTipo(txtVestimenta, lblVestimenta);
            } else {
                IngrTipo0(lblVestimenta);
            }
            if (!txtOtro.getText().equals("0.0")) {
                totales[5] = ingresarTipo(txtOtro, lblOtro);
            } else {
                IngrTipo0(lblOtro);
            }*/

            if (conTipo.verificar_usuario("SELECT * FROM HISTORIAL_PAGOS_PERSONALES WHERE anio_historial_p=" + anio + " AND id_cliente='" + cedula + "'")) {
                query = "UPDATE HISTORIAL_PAGOS_PERSONALES SET total_alimentacion=total_alimentacion+" + totales[3] + ","
                        + "total_salud=total_salud+" + totales[1] + ","
                        + "total_vivienda=total_vivienda+" + totales[0] + ","
                        + "total_educacion=total_educacion+" + totales[2] + ","
                        + "total_vestimenta=total_vestimenta+" + totales[4] + ","
                        + "total_otros=total_otros+" + totales[5] + " WHERE anio_historial_p=" + anio + " AND id_cliente='" + cedula + "'";
                System.out.println("Estoy en el if");
            } else {
                query = "INSERT INTO HISTORIAL_PAGOS_PERSONALES VALUES (" + anio + ",'" + cedula + "'," + totales[3] + "," + totales[1] + "," + totales[0] + "," + totales[2] + "," + totales[4] + "," + totales[5] + ")";
                System.out.println("Estoy en el else");
                query1 = "update cliente set nombre_cliente = '" + nombre_Cli.getText() + "'";
            }

            for (int i = 0; i < tipoEstado.length; i++) {
                if (!conTipo.verificar_usuario("SELECT DESCRIPCIONRELACION FROM RELACIONGASTO WHERE descripcionrelacion='" + tablaProductos.getValueAt(i, 0) + "'")) {
                    String q;
                    q = "INSERT INTO RELACIONGASTO (descripcionrelacion,tipo_gasto)"
                            + "VALUES('" + tablaProductos.getValueAt(i, 0) + "','" + tablaProductos.getValueAt(i, 2) + "')";
                    conTipo.insertar(q);
                }
            }

            for (int i = 0; i < tablaProductos.getRowCount(); i++) {
                String q;
                q = "INSERT INTO DETALLE (ID_FACTURA,NOMBRE_PRODUCTO,TOTAL,TIPO)"
                        + "VALUES('" + cod.getText() + "','" + tablaProductos.getValueAt(i, 0) + "','" + tablaProductos.getValueAt(i, 1) + "','" + tablaProductos.getValueAt(i, 2) + "')";
                conTipo.insertar(q);
                System.out.println(q);
            }

            conTipo.insertar(query);
            conTipo.insertar(query1);

            JOptionPane.showMessageDialog(this, "Factura ingresada exitosamente");
            recargar(conTipo);
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(this, "No se ha seleccionado el tipo para cada producto");
        }
    }//GEN-LAST:event_btnAceptarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
        String query = "";
        //if (conTipo.verificar_usuario("SELECT * FROM HISTORIAL_PAGOS_PERSONALES WHERE anio_historial_p=" + anio + " AND id_cliente='" + cedula + "'")) {
        query = "delete from factura where id_factura = '" + numFac + "'";

        //System.out.println("Estoy en el if");
        //} 
        conTipo.insertar(query);

        System.out.println("LA factura no se ingresa");
        recargar(conTipo);
        this.dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void CalcularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CalcularActionPerformed
        // TODO add your handling code here:
        sumarTablaTotalGastos();
    }//GEN-LAST:event_CalcularActionPerformed

    private void IngrTipo0(JLabel lblTipo) {
        String q;
        q = "INSERT INTO TIPO_GASTO (id_factura,tipo,total)"
                + "VALUES('" + numFac + "','" + lblTipo.getText() + "'," + 0 + ")";
        conTipo.insertar(q);
    }

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
    
    
    public void sumarTablaTotalGastos() {
       
        //ArrayList tipoGatosNegoc = conTipo.cargarTipoGasNegocio(cedula);
         
        for (int i=0; i<jTableTiposGasto.getRowCount(); i++ ){
            for (int j=0; j<tablaProductos.getRowCount(); j++) {
                if (tablaProductos.getValueAt(j, 2).equals(jTableTiposGasto.getValueAt(i,0))) {        
                    jTableTiposGasto.setValueAt((Double.parseDouble(jTableTiposGasto.getValueAt(i,1).toString()) + Double.parseDouble(tablaProductos.getValueAt(j, 1).toString())), i, 1);
                }
            }
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
            java.util.logging.Logger.getLogger(SeleccionarTipoGastoNegocio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SeleccionarTipoGastoNegocio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SeleccionarTipoGastoNegocio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SeleccionarTipoGastoNegocio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
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
    private javax.swing.JButton Calcular;
    private javax.swing.JTextField IVA;
    private javax.swing.JTextField RUC_CI_Cli;
    private javax.swing.JTextField RUC_PRov;
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JTextField cod;
    private javax.swing.JTextField dir_Prov;
    private javax.swing.JTextField fecha;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTableTiposGasto;
    private javax.swing.JLabel lbCodigo;
    private javax.swing.JLabel lbCodigo1;
    private javax.swing.JLabel lbCodigo2;
    private javax.swing.JLabel lbCodigo3;
    private javax.swing.JLabel lbCodigo4;
    private javax.swing.JLabel lbCodigo5;
    private javax.swing.JLabel lbCodigo6;
    private javax.swing.JTextField nomb_Prov;
    private javax.swing.JTextField nombre_Cli;
    private javax.swing.JTextField totalConIVA;
    private javax.swing.JTextField totalSinIVA;
    // End of variables declaration//GEN-END:variables
}
