/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.formularios;

import com.mycompany.daos.LicenciaDAO;
import com.mycompany.daos.PagoDAO;
import static com.mycompany.dominio.Discapacitado.SI;
import com.mycompany.dominio.Licencia;
import com.mycompany.dominio.Pago;
import com.mycompany.dominio.Persona;
import com.mycompany.dominio.Tramite;
import com.mycompany.dominio.Vigencia;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.persistence.EntityManager;
import javax.swing.JOptionPane;

/**
 * Clase que contiene el Modulo de generar Licencia
 *
 * @author edemb
 */
public class ModuloLicencia extends javax.swing.JFrame {

    private Persona persona;
    private LicenciaDAO licenciaDAO;
    private PagoDAO pagoDAO;
    private EntityManager entityManager;
/**
 * Constructor del Modulo Licencia
 * @param persona Persona a utilizar
 * @param entityManager EntityManager a utilizar
 */
    public ModuloLicencia(Persona persona, EntityManager entityManager) {
        initComponents();
        this.persona = persona;
        this.entityManager = entityManager;
        setLabelPersona();
        licenciaDAO = new LicenciaDAO(entityManager);
        System.out.println(entityManager);
        pagoDAO = new PagoDAO(entityManager);
    }

    /**
     * Muestra mensaje de Licencia generada con exito
     */
    private void mensajeLicenciaGeneradaExitosamente() {
        JOptionPane.showMessageDialog(this, "Licencia Generada Exitosamente para la persona :" + this.persona.getNombreCompleto(), "Licencia Generada Exitosamente", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Muestra un Mensaje de error por Personas
     */
    private void mensajeErrorPersona() {
        JOptionPane.showMessageDialog(this, "Error Persona no encontrada", "Formateo de fecha error", JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Asigna valores a los labels de precios
     */
    public void setLabelPrecioLicencia() {
        if (this.persona.getDiscapasitado().equals(SI)) {
            if (cmbVigencia.getSelectedItem().toString() == "1 Año") {
                lblPrecioTotal.setText("$200.00 pesos");
            } else if (cmbVigencia.getSelectedItem().toString() == "2 Años") {
                lblPrecioTotal.setText("$500.00 pesos");
            } else {
                lblPrecioTotal.setText("$700.00 Pesos");
            }
        } else {
            if (cmbVigencia.getSelectedItem().toString() == "1 Año") {
                lblPrecioTotal.setText("$600.00 pesos");
            } else if (cmbVigencia.getSelectedItem().toString() == "2 Años") {
                lblPrecioTotal.setText("$900.00 pesos");
            } else {
                lblPrecioTotal.setText("$1,100.00 Pesos");
            }
        }
    }

    /**
     * Genere la licencia
     *
     * @return Regresa true si se genero, false en caso contrario
     */
    private Boolean generarLicencia() {

        if (this.persona == null) {
            mensajeErrorPersona();
            return false;
        }
        licenciaDAO.generarLicencia(this.consutlarVigencia(), persona);
        Licencia licencia = licenciaDAO.consultarLicenciaActiva(persona);
        Pago pago = generarPago(licencia);
        if (pago == null) {
            JOptionPane.showMessageDialog(this, "No se pudo generar el Pago del Tramite", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    /**
     * Genera el pago
     *
     * @param tramite Tramite al que se le generara al Pago
     * @return el Pago generado
     */
    private Pago generarPago(Tramite tramite) {
        Pago pago = this.pagoDAO.generarPago(tramite);
        return pago;
    }

    /**
     * Consulta la Vigencia de la licencia
     *
     * @return La vigencia que tendra la licencia
     */
    private Vigencia consutlarVigencia() {
        int opcion = this.cmbVigencia.getSelectedIndex();
        if (opcion == 1) {
            return Vigencia.Ano_1;
        } else if (opcion == 2) {
            return Vigencia.Ano_2;
        } else {
            return Vigencia.Ano_3;
        }
    }

    /**
     * Regresa la Persona
     *
     * @return Persona
     */
    public Persona getPersona() {
        return persona;
    }

    /**
     * Le asigna un valor a la Persona
     *
     * @param persona Persona a asignar
     */
    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    /**
     * Cierra el Modulo Licencia
     */
    private void cerrarVentana() {
        this.dispose();
    }

    /**
     * Abre el Modulo Generar Tramite
     */
    private void irModuloGenerarTramite() {
        ModuloGenerarTramite tra = new ModuloGenerarTramite(this.entityManager);
        tra.setVisible(true);
    }

    /**
     * Abre el Modulo Menu
     */
    private void irMenu() {
        Menu menu = new Menu();
        menu.setVisible(true);
    }

    /**
     * Asigna los valores a los labels de los datos de la Persona
     */
    private void setLabelPersona() {
        try {
            SimpleDateFormat formateado = new SimpleDateFormat("dd/MM/yyyy");
            lblNombre.setText(persona.getNombreCompleto());
            lblFechaNcimiento.setText(formateado.format(persona.getFechaNacimiento().getTime()));
            lblRFC.setText(persona.getRfc());
            lblTelefono.setText(persona.getTelefono());
            lblSexo.setText(persona.getSexo().toString());
            lblDiscapacitado.setText(persona.getDiscapasitado().toString());
        } catch (Exception e) {
            System.out.println(e.getMessage());
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

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        lblNombre = new javax.swing.JLabel();
        lblFechaNcimiento = new javax.swing.JLabel();
        lblTelefono = new javax.swing.JLabel();
        lblSexo = new javax.swing.JLabel();
        lblRFC = new javax.swing.JLabel();
        btnRegresar = new javax.swing.JButton();
        cmbVigencia = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        lblDiscapacitado = new javax.swing.JLabel();
        btnGenerarLicencia = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        lblPrecioTotal = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Generar Licencia");
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel1.setText("Generar Licencia");

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel2.setText("Nombre:");

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel3.setText("Fecha de Nacimiento:");

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel4.setText("Telefono:");

        jLabel5.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel5.setText("Sexo:");

        jLabel6.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel6.setText("RFC:");

        lblNombre.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lblNombre.setText("...");

        lblFechaNcimiento.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lblFechaNcimiento.setText("...");

        lblTelefono.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lblTelefono.setText("...");

        lblSexo.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lblSexo.setText("...");

        lblRFC.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lblRFC.setText("...");

        btnRegresar.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        btnRegresar.setText("Regresar");
        btnRegresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegresarActionPerformed(evt);
            }
        });

        cmbVigencia.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1 Año", "2 Años", "3 Años" }));
        cmbVigencia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbVigenciaActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel7.setText("Es Discapacitado:");

        lblDiscapacitado.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lblDiscapacitado.setText("...");

        btnGenerarLicencia.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        btnGenerarLicencia.setText("Generar Licencia");
        btnGenerarLicencia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerarLicenciaActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel8.setText("Total:");

        lblPrecioTotal.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblPrecioTotal.setText("....");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(266, 266, 266)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnRegresar, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGap(66, 66, 66)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblDiscapacitado, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addContainerGap())
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4)
                                    .addComponent(lblNombre, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lblFechaNcimiento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lblTelefono, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lblSexo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lblRFC, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel5))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 145, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(cmbVigencia, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(btnGenerarLicencia, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(64, 64, 64))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(jLabel8)
                                        .addGap(18, 18, 18)
                                        .addComponent(lblPrecioTotal)
                                        .addGap(107, 107, 107))))))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(jLabel1)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblNombre)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(cmbVigencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblFechaNcimiento)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblTelefono))
                    .addComponent(btnGenerarLicencia, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel8)
                        .addComponent(lblPrecioTotal))
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblSexo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblRFC)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblDiscapacitado)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 60, Short.MAX_VALUE)
                .addComponent(btnRegresar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Te regresa al Modulo anterior
     *
     * @param evt ...
     */
    private void btnRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresarActionPerformed
        // TODO add your handling code here:
        irModuloGenerarTramite();
        cerrarVentana();
    }//GEN-LAST:event_btnRegresarActionPerformed

    /**
     * Genera la Licencia
     *
     * @param evt ...
     */
    private void btnGenerarLicenciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerarLicenciaActionPerformed
        // TODO add your handling code here:
        if (!this.generarLicencia()) {
            return;
        }
        mensajeLicenciaGeneradaExitosamente();
        this.irMenu();
        this.cerrarVentana();
    }//GEN-LAST:event_btnGenerarLicenciaActionPerformed

    /**
     * Asigna la Vigencia de la Licencia
     *
     * @param evt...
     */
    private void cmbVigenciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbVigenciaActionPerformed
        // TODO add your handling code here:
        this.setLabelPrecioLicencia();
    }//GEN-LAST:event_cmbVigenciaActionPerformed

//    /**
//     * @param args the command line arguments
//     */
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
//            java.util.logging.Logger.getLogger(ModuloLicencia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(ModuloLicencia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(ModuloLicencia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(ModuloLicencia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new ModuloLicencia().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGenerarLicencia;
    private javax.swing.JButton btnRegresar;
    private javax.swing.JComboBox<String> cmbVigencia;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel lblDiscapacitado;
    private javax.swing.JLabel lblFechaNcimiento;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblPrecioTotal;
    private javax.swing.JLabel lblRFC;
    private javax.swing.JLabel lblSexo;
    private javax.swing.JLabel lblTelefono;
    // End of variables declaration//GEN-END:variables
}
