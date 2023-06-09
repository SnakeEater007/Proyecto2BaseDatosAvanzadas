/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.formularios;

import com.mycompany.daos.ConexionDAO;
import com.mycompany.daos.PersonaDAO;
import com.mycompany.interfaces.IPersonaDAO;
import javax.persistence.EntityManager;
import javax.swing.JOptionPane;

/**
 *
 * @author edemb
 */
public class Menu extends javax.swing.JFrame {

    private ConexionDAO conexion;
    private EntityManager entity;

    /**
     * Creates new form NewJFrame
     */
    public Menu() {
        initComponents();
        conexion = new ConexionDAO();
        entity = conexion.crearConexion("Proyecto2BDA");
    }

    /**
     * Metodo para ir al modulo generar tramite
     */
    private void irModuloTramite() {
        ModuloGenerarTramite licencias = new ModuloGenerarTramite(this.entity);
        licencias.setVisible(true);
        this.dispose();
    }

    /**
     * Metodo para ir al modulo historiales
     */
    private void irHistorialPlacasLicencia() {
        ModuloHistoriales historiales = new ModuloHistoriales(entity);
        historiales.setVisible(true);
        this.dispose();
    }

    /**
     * Metodo para ir al modulo del reporte
     */
    private void irGenerarReporte() {
        ModuloReporte moduloReporte = new ModuloReporte(entity);
        moduloReporte.setVisible(true);
        this.dispose();
    }

    /**
     * Metodo para hacer una insercion masiva
     */
    private void personasAInsetar() {
        try {
            PersonaDAO insertar = new PersonaDAO(entity);
            insertar.registrarPersonas();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ocurrio un error al insertar las personas", "Error", JOptionPane.ERROR_MESSAGE);
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

        btnHistorial = new javax.swing.JButton();
        btnGenerarReporte = new javax.swing.JButton();
        btnGenerarLicencia = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        btnInsertarPersonas = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Menu");
        setResizable(false);

        btnHistorial.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        btnHistorial.setText("Historial de Placas y Licencias");
        btnHistorial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHistorialActionPerformed(evt);
            }
        });

        btnGenerarReporte.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        btnGenerarReporte.setText("Generar Reporte de Tramites Realizados");
        btnGenerarReporte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerarReporteActionPerformed(evt);
            }
        });

        btnGenerarLicencia.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        btnGenerarLicencia.setText("Generar Tramite");
        btnGenerarLicencia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerarLicenciaActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        jLabel1.setText("Agencia Fiscal De Sonora");

        btnInsertarPersonas.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        btnInsertarPersonas.setText("Insertar Personas");
        btnInsertarPersonas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInsertarPersonasActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(164, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addContainerGap(164, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnGenerarLicencia, javax.swing.GroupLayout.PREFERRED_SIZE, 382, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnHistorial, javax.swing.GroupLayout.PREFERRED_SIZE, 382, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGenerarReporte, javax.swing.GroupLayout.PREFERRED_SIZE, 382, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(190, 190, 190))
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(btnInsertarPersonas)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addComponent(btnGenerarLicencia, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46)
                .addComponent(btnHistorial, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(btnGenerarReporte, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 65, Short.MAX_VALUE)
                .addComponent(btnInsertarPersonas)
                .addGap(22, 22, 22))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnGenerarLicenciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerarLicenciaActionPerformed
        // TODO add your handling code here:
        this.irModuloTramite();
    }//GEN-LAST:event_btnGenerarLicenciaActionPerformed

    private void btnHistorialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHistorialActionPerformed
        // TODO add your handling code here:
        this.irHistorialPlacasLicencia();
    }//GEN-LAST:event_btnHistorialActionPerformed

    private void btnGenerarReporteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerarReporteActionPerformed
        // TODO add your handling code here:
        this.irGenerarReporte();
    }//GEN-LAST:event_btnGenerarReporteActionPerformed

    private void btnInsertarPersonasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInsertarPersonasActionPerformed
        // TODO add your handling code here:
        this.personasAInsetar();
    }//GEN-LAST:event_btnInsertarPersonasActionPerformed

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new Menu().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGenerarLicencia;
    private javax.swing.JButton btnGenerarReporte;
    private javax.swing.JButton btnHistorial;
    private javax.swing.JButton btnInsertarPersonas;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
