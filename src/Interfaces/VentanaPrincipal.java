/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import conexionBDD.Conexionn;
import java.awt.Dimension;
import java.util.ArrayList;
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

    String cedula_usuario;
    int anio;
    Conexionn conn;

    ArrayList historial_p, historial_n;

    public VentanaPrincipal(String cedula_usuario, int anio) {
        initComponents();
        dim=super.getToolkit().getScreenSize();
        setSize(dim);
        //setUndecorated(true);
        conn = new Conexionn();
        fmp = new FacturaManualPersonal(conn, cedula_usuario, anio);
        fmn = new FacturaManualNegocio(conn, cedula_usuario, anio);
        fe = new FacturaElectronicaNew(cedula_usuario, anio);
        hg = new HistorialGastos(conn, cedula_usuario, anio);
        rp = new Reportes(conn, cedula_usuario, anio);
        rPV = new ReporteProveedor(conn, cedula_usuario, anio);
        this.anio = anio;
        this.cedula_usuario = cedula_usuario;
        setResizable(false);
        setLocationRelativeTo(null);
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
        jDesktopPane = new javax.swing.JDesktopPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        m_FactElect = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenuItem9 = new javax.swing.JMenuItem();
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
            .addComponent(jDesktopPane, javax.swing.GroupLayout.DEFAULT_SIZE, 705, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDesktopPane, javax.swing.GroupLayout.DEFAULT_SIZE, 499, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
        jDesktopPane.removeAll();
        fmp.setVisible(false);
        fmn.setVisible(false);
        hg.setVisible(false);
        rp.setVisible(false);
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
            this.dispose();
            new Login().setVisible(true);
        }
    }
    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        salir();
        
    }//GEN-LAST:event_formWindowClosing

    private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed
        // TODO add your handling code here:
        jDesktopPane.removeAll();
            fmp.setVisible(false);
            fmn.setVisible(false);
            fe.setVisible(false);
            rp.setVisible(false);
            hg.setVisible(false);
            rPV.cargar_Provee();
            rPV.setVisible(true);
            jDesktopPane.add(rPV);
    }//GEN-LAST:event_jMenuItem8ActionPerformed
    
    private void jMenuItem9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem9ActionPerformed
        // TODO add your handling code here:
        
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
    private javax.swing.JDesktopPane jDesktopPane;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JMenu m_FactElect;
    private javax.swing.JMenu m_FactFisic;
    private javax.swing.JMenu m_Usuario;
    // End of variables declaration//GEN-END:variables
}
