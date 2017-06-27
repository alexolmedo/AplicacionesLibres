package Interfaces;

import conexionBDD.Conexionn;
import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

/**
 *
 * @author vengatus
 */
public class VentanaPrincipal extends javax.swing.JFrame {

    private Dimension dim;
    FacturaManualPersonal fmp;
    FacturaElectronicaNew fe;
    FacturaManualNegocio fmn;
    HistorialGastos hg;
    Reportes rp;
    ReporteProveedor rPV;
    ReporteNumFacPorAño rNFA;
    ReporteFacturasAño rFA;
    String cedula_usuario;
    int anio;
    Conexionn conn;
    ArrayList historial_p, historial_n;

    public VentanaPrincipal(String cedula_usuario) {
        initComponents();
        setIconImage(new ImageIcon(getClass().getResource("/Imagenes/ico_21-1.png")).getImage());
        setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);

        conn = new Conexionn();
        cargar_anios();
        setYear();        
        this.cedula_usuario = cedula_usuario;
        setLocationRelativeTo(null);
    }

    private void setYear(){
        jDesktopPane.removeAll();
        jDesktopPane.repaint();
        this.anio=Integer.parseInt(combo_anio.getSelectedItem().toString());
        fmp = new FacturaManualPersonal(conn, cedula_usuario, anio);
        fmn = new FacturaManualNegocio(conn, cedula_usuario, anio);
        fe = new FacturaElectronicaNew(cedula_usuario, anio);
        hg = new HistorialGastos(conn, cedula_usuario, anio);
        rp = new Reportes(conn, cedula_usuario, anio);
        rPV = new ReporteProveedor(conn, cedula_usuario, anio);
        rNFA = new ReporteNumFacPorAño(conn, cedula_usuario, anio);
        rFA = new ReporteFacturasAño(conn, cedula_usuario, anio);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuBar2 = new javax.swing.JMenuBar();
        jMenu2 = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();
        jPanel1 = new javax.swing.JPanel();
        combo_anio = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jDesktopPane = new javax.swing.JDesktopPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        m_FactElect = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenuItem9 = new javax.swing.JMenuItem();
        jMenuItem10 = new javax.swing.JMenuItem();
        jMenuItem11 = new javax.swing.JMenuItem();
        m_Usuario = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();
        m_FactFisic = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();

        jMenu2.setText("File");
        jMenuBar2.add(jMenu2);

        jMenu3.setText("Edit");
        jMenuBar2.add(jMenu3);

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Ordenador de Facturas");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel1.setMaximumSize(new java.awt.Dimension(32767, 30));
        jPanel1.setPreferredSize(new java.awt.Dimension(126, 30));

        combo_anio.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                combo_anioItemStateChanged(evt);
            }
        });
        combo_anio.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                combo_anioFocusLost(evt);
            }
        });
        combo_anio.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                combo_anioPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        jLabel1.setText("Año");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(combo_anio, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 10, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(combo_anio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)))
        );

        jDesktopPane.setPreferredSize(null);
        jDesktopPane.setRequestFocusEnabled(false);

        m_FactElect.setText("Facturas Electronicas");

        jMenuItem2.setText("Ingresar");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        m_FactElect.add(jMenuItem2);

        jMenuBar1.add(m_FactElect);

        jMenu4.setText("Reportes");

        jMenuItem8.setText("Reporte Facturas por Proveedor");
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem8ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem8);

        jMenuItem9.setText("Reporte Historial Gastos por Año");
        jMenuItem9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem9ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem9);

        jMenuItem10.setText("Reporte Nro Facturas Proveedor");
        jMenuItem10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem10ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem10);

        jMenuItem11.setText("Reporte Facturas por Año");
        jMenuItem11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem11ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem11);

        jMenuBar1.add(jMenu4);

        m_Usuario.setText("Usuario");

        jMenuItem3.setText("Darme de baja del sistema");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        m_Usuario.add(jMenuItem3);

        jMenuItem7.setText("Salir");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        m_Usuario.add(jMenuItem7);

        jMenuBar1.add(m_Usuario);

        m_FactFisic.setText("Facturas Fisicas");
        m_FactFisic.setEnabled(false);

        jMenuItem1.setText("Registar Factura Personal");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        m_FactFisic.add(jMenuItem1);

        jMenuItem5.setText("Registrar Factura de Negocio");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        m_FactFisic.add(jMenuItem5);

        jMenuBar1.add(m_FactFisic);

        jMenu1.setText("Historial de Gastos");
        jMenu1.setEnabled(false);

        jMenuItem4.setText("Ver Reporte de Gastos");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem4);

        jMenuItem6.setText("Reportes");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem6);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDesktopPane, javax.swing.GroupLayout.DEFAULT_SIZE, 535, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 535, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jDesktopPane, javax.swing.GroupLayout.DEFAULT_SIZE, 456, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void cargar_anios(){
        combo_anio.removeAllItems();
        ArrayList anios=conn.cargarAnios();
        //anios.sort(null);
        for (Object obj:anios){
            combo_anio.addItem(obj.toString());
        }
    }
    
    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        jDesktopPane.removeAll();
        fe.setVisible(false);
        hg.setVisible(false);
        fmn.setVisible(false);
        rp.setVisible(false);
        fmp.setVisible(true);
        jDesktopPane.add(fmp);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        anio=Integer.parseInt(combo_anio.getSelectedItem().toString());
        jDesktopPane.removeAll();
        fmp.setVisible(false);
        fmn.setVisible(false);
        hg.setVisible(false);
        rp.setVisible(false);
        rPV.setVisible(false);
        fe = new FacturaElectronicaNew(cedula_usuario, anio);
        fe.setVisible(true);        
        jDesktopPane.add(fe);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        if (JOptionPane.showConfirmDialog(null, "Esta seguro que desea darse de baja del sistema?", "Advertencia", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION) {
            JPasswordField pf = new JPasswordField();
            int okCxl = JOptionPane.showConfirmDialog(null, pf, "Por favor, ingrese su contraseña", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            if (okCxl == JOptionPane.OK_OPTION) {
                if (conn.verificar_usuario(String.format("select * from cliente where id_cliente='%s' and contrasena='%s'", cedula_usuario, String.valueOf(pf.getPassword())))) {
                    conn.insertar(String.format("select borrarCliente('%s')", cedula_usuario));
                    this.dispose();
                    new Login().setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Contraseña incorrecta", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        historial_p = conn.ddl(String.format("select * from historial_pagos_personales where anio_historial_p=%s and id_cliente='%s'", anio, cedula_usuario));
        historial_n = conn.ddl(String.format("select * from historial_pagos_negocios where anio_historial_n=%s and id_cliente='%s'", anio, cedula_usuario));

        if (historial_p.isEmpty() && historial_n.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No se tienen registros de este año");
        } else {
            jDesktopPane.removeAll();
            fmp.setVisible(false);
            fmn.setVisible(false);
            fe.setVisible(false);
            rp.setVisible(false);
            hg.setVisible(true);
            jDesktopPane.add(hg);
        }
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        jDesktopPane.removeAll();
        fe.setVisible(false);
        hg.setVisible(false);
        fmp.setVisible(false);
        rp.setVisible(false);
        fmn.setVisible(true);
        jDesktopPane.add(fmn);
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        jDesktopPane.removeAll();
        fe.setVisible(false);
        hg.setVisible(false);
        fmp.setVisible(false);
        fmn.setVisible(false);
        rp.setVisible(true);
        jDesktopPane.add(rp);        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        salir();
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void salir(){
        if (JOptionPane.showConfirmDialog(null, "Desea salir del sistema?", "Advertencia", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
            System.exit(0);
            new Login().setVisible(true);
        }
    }
    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        salir();
        
    }//GEN-LAST:event_formWindowClosing

    private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed

        jDesktopPane.removeAll();
            fmp.setVisible(false);
            fmn.setVisible(false);
            fe.setVisible(false);
            rp.setVisible(false);
            hg.setVisible(false);
            rPV.cargar_Provee();
            rPV.setVisible(true);
            ArrayList nombCLIente  = conn.ddl("select nombre_cliente from cliente where id_cliente ='" +this.cedula_usuario +"'");
            rPV.cargarDato(cedula_usuario);
            rPV.cargarTabla();
            jDesktopPane.add(rPV);
    }//GEN-LAST:event_jMenuItem8ActionPerformed
    
    private void jMenuItem9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem9ActionPerformed

        historial_p = conn.ddl(String.format("select * from historial_pagos_personales where anio_historial_p=%s and id_cliente='%s'", anio, cedula_usuario));
        historial_n = conn.ddl(String.format("select * from historial_pagos_negocios where anio_historial_n=%s and id_cliente='%s'", anio, cedula_usuario));

        if (historial_p.isEmpty() && historial_n.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No se tienen registros de este año");
        } else {
            jDesktopPane.removeAll();
            fmp.setVisible(false);
            rPV.setVisible(false);
            fmn.setVisible(false);
            fe.setVisible(false);
            rp.setVisible(false);
            hg.setVisible(true);
            jDesktopPane.add(hg);
        }               
    }//GEN-LAST:event_jMenuItem9ActionPerformed

    private void combo_anioItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_combo_anioItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_combo_anioItemStateChanged

    private void combo_anioFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_combo_anioFocusLost
       // TODO add your handling code here:
    }//GEN-LAST:event_combo_anioFocusLost

    private void combo_anioPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_combo_anioPopupMenuWillBecomeInvisible
        setYear();
    }//GEN-LAST:event_combo_anioPopupMenuWillBecomeInvisible

    private void jMenuItem10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem10ActionPerformed
        // TODO add your handling code here:
        jDesktopPane.removeAll();
        fmp.setVisible(false);
        fmn.setVisible(false);
        fe.setVisible(false);
        rp.setVisible(false);        
        hg.setVisible(false);                         
        rPV.setVisible(false);        
        rNFA.setVisible(true);
        ArrayList nombCLIente  = conn.ddl("select nombre_cliente from cliente where id_cliente ='" +this.cedula_usuario +"'");
        rNFA.cargarCliente((String) nombCLIente.get(0));
        rNFA.cargarTabla();
        jDesktopPane.add(rNFA);
    }//GEN-LAST:event_jMenuItem10ActionPerformed

    private void jMenuItem11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem11ActionPerformed
        // TODO add your handling code here:
        jDesktopPane.removeAll();
        fmp.setVisible(false);
        fmn.setVisible(false);
        fe.setVisible(false);
        rp.setVisible(false);        
        hg.setVisible(false);                         
        rPV.setVisible(false);        
        rNFA.setVisible(false);
        rFA.setVisible(true);
        ArrayList nombCLIente  = conn.ddl("select nombre_cliente from cliente where id_cliente ='" +this.cedula_usuario +"'");
        rFA.cargarDato((String) nombCLIente.get(0));
        rFA.cargarTabla();
        jDesktopPane.add(rFA);
    }//GEN-LAST:event_jMenuItem11ActionPerformed

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
            java.util.logging.Logger.getLogger(VentanaPrincipal.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaPrincipal.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaPrincipal.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaPrincipal.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                //new VentanaPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> combo_anio;
    private javax.swing.JDesktopPane jDesktopPane;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem11;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JMenu m_FactElect;
    private javax.swing.JMenu m_FactFisic;
    private javax.swing.JMenu m_Usuario;
    // End of variables declaration//GEN-END:variables
}
