/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.formularios;

import com.mycompany.daos.LicenciaDAO;
import com.mycompany.daos.PagoDAO;
import com.mycompany.daos.PlacaDAO;
import com.mycompany.daos.VehiculoDAO;
import com.mycompany.dominio.Automovil;
import com.mycompany.dominio.Estado;
import com.mycompany.dominio.Licencia;
import com.mycompany.dominio.Persona;
import com.mycompany.dominio.Placa;
import com.mycompany.dominio.Tramite;
import com.mycompany.dominio.Vehiculo;
import com.mycompany.utils.ValidacionDatos;
import java.text.SimpleDateFormat;
import javax.persistence.EntityManager;
import javax.swing.JOptionPane;

/**
 * Clase que contiene el Modulo de Placa a Auto Nuevo
 * @author edemb
 */
public class ModuloPlacaAutoNuevo extends javax.swing.JFrame {

    private Persona persona;
    private EntityManager entityManager;
    private VehiculoDAO vehiculoDAO;
    private PlacaDAO placaDAO;
    private LicenciaDAO licenciaDAO;
    PagoDAO pagoDAO;

/**
 * Contructor del Modulo Placa A Auto Nuevo
 * @param persona Persona a utilizar
 * @param entityManager EntityManager a utilizar
 */
    public ModuloPlacaAutoNuevo(Persona persona, EntityManager entityManager) {
        initComponents();
        this.persona = persona;
        this.entityManager = entityManager;
        setLabelPersona();
        placaDAO = new PlacaDAO(entityManager);
        vehiculoDAO = new VehiculoDAO(entityManager);
        licenciaDAO = new LicenciaDAO(entityManager);
        pagoDAO = new PagoDAO(entityManager);
    }

    /**
     * Asigna los valores de los labels de los datos de la Persona
     */
    private void setLabelPersona() {
        try {
            SimpleDateFormat formateado = new SimpleDateFormat("dd/MM/yyyy");
            lblNombre.setText(persona.getNombreCompleto());
            lblFechaNacimiento.setText(formateado.format(persona.getFechaNacimiento().getTime()));
            lblRFC.setText(persona.getRfc());
            lblTelefono.setText(persona.getTelefono());
            lblSexo.setText(persona.getSexo().toString());
            lblDiscapacitado.setText(persona.getDiscapasitado().toString());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Valida el formato de los datos ingresados
     * @return true si es valido, false en caso contrario
     */
    private Boolean formatoValido() {
        String errores = "Formato invalido en: \n";
        int i = 0;
        if (!ValidacionDatos.serieEsValida(this.txtSerie.getText())) {
            errores += " Serie: Debe contener 7 caracteres, solo acepta MAYUSCULAS y Numeros y no acepta espacios al final\n";
            i++;
        }
        if (ValidacionDatos.contieneCaracteresEspeciales(this.txtMarca.getText())) {
            errores += " Marca: Revise que no este vacio y no contenga caracteres especiales ni espacios al final\n";
            i++;
        }
        if (ValidacionDatos.contieneCaracteresEspeciales(this.txtLinea.getText())) {
            errores += " Linea: Revise que no este vacio y no contenga caracteres especiales ni espacios al final\n";
            i++;
        }
        if (ValidacionDatos.contieneCaracteresEspeciales(this.txtColor.getText())) {
            errores += " Color: Revise que no este vacio y no contenga caracteres especiales ni espacios al final\n";
            i++;
        }
        if (ValidacionDatos.contieneCaracteresEspeciales(this.txtModelo.getText())) {
            errores += " Modelo: Revise que no este vacio y no contenga caracteres especiales ni espacios al final";
            i++;
        }
        if (i != 0) {
            JOptionPane.showMessageDialog(this, errores, "Error en los Datos", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;

    }

    /**
     * Consulta el vehiculo nuevo
     * @return el Vehiculo nuevo, null en caso de algun error
     */
    private Vehiculo consultarVehiculoNuevo() {
        if (!this.formatoValido()) {
            return null;
        }
        Vehiculo vehiculo = new Automovil();
        vehiculo.setSerie(this.txtSerie.getText());
        vehiculo.setMarca(this.txtMarca.getText());
        vehiculo.setLinea(this.txtLinea.getText());
        vehiculo.setColor(this.txtColor.getText());
        vehiculo.setModelo(this.txtModelo.getText());
        return vehiculo;
    }

    /**
     * Muestra un mensaje de Placa generada con exito
     * @param placa Placa que se genero
     */
    private void placaGeneradaExitosamente(Placa placa) {
        JOptionPane.showMessageDialog(this, "Se genero exitosamente la Placa: " + placa.getNumero() + " al vehiculo con el numero de serie: " + this.txtSerie.getText(), "Placa Generada Exitosamente", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Valida si la Persona tiene licencia vigente
     * @return true si la tiene, false en caso contrario
     */
    private Boolean tieneLicenciaVigente() {
        Licencia licencia = licenciaDAO.consultarLicenciaActiva(this.persona);
        if (licencia == null || licencia.getEstado() == Estado.DESACTIVA) {
            JOptionPane.showMessageDialog(this, "La persona que quiere generar la placa no tienen vigente su Licencia", "Licencia Vencida", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    /**
     * Genera la Placa
     * @return La Placa generada
     */
    private Placa generarPlaca() {
        if (!tieneLicenciaVigente()) {
            return null;
        }
        Vehiculo v = consultarVehiculoNuevo();
        if (v == null) {
            return null;
        }
        Vehiculo vehiculo = this.vehiculoDAO.registraVehiculo(v);
        if (vehiculo == null) {
            JOptionPane.showMessageDialog(this, "Serie ya registrada, favor de ir a crear la placa en auto usado", "Alerta", JOptionPane.WARNING_MESSAGE);
            return null;
        }
        Placa placa = placaDAO.generarPlacaVehiculoNuevo(persona, vehiculo);

        if (placa == null) {
            JOptionPane.showMessageDialog(this, "No se pudo generar la Placa", "Error", JOptionPane.ERROR_MESSAGE);

            return null;
        }
        generarPago(placa);
        return placa;
    }

    /**
     * Genera el Pago al Tramite
     * @param tramite Tramite al que se le generara el Pago
     */
    private void generarPago(Tramite tramite) {
        pagoDAO.generarPago(tramite);
    }

    /**
     * Abre Modulo de Seleccion de Placa
     */
    private void regresarModuloPlaca() {
        ModuloPlaca placa = new ModuloPlaca(this.entityManager);
        placa.setPersona(this.persona);
        placa.setVisible(true);

    }

    /**
     * Abre modulo Menu
     */
    private void irMenu() {
        Menu menu = new Menu();
        menu.setVisible(true);
    }

    /**
     * Cierra el Modulo Placa Auto Nuevo
     */
    private void cerrarVentanaActual() {
        this.dispose();
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
        txtMarca = new javax.swing.JTextField();
        txtSerie = new javax.swing.JTextField();
        txtModelo = new javax.swing.JTextField();
        txtColor = new javax.swing.JTextField();
        txtLinea = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        generarPlaca = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        lblFechaNacimiento = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        lblTelefono = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        lblSexo = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        lblRFC = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        lblNombre = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        lblDiscapacitado = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Generar Placa a Vehiculo Nuevo");
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel1.setText("Serie:");

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel2.setText("Marca:");

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel3.setText("Color:");

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel4.setText("Linea:");

        jLabel5.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel5.setText("Modelo:");

        jLabel6.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel6.setText("Ingresa los datos del vehiculo");

        generarPlaca.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        generarPlaca.setText("Generar Placa");
        generarPlaca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generarPlacaActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jButton2.setText("Regresar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        lblFechaNacimiento.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lblFechaNacimiento.setText("...");

        jLabel8.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel8.setText("Telefono:");

        lblTelefono.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lblTelefono.setText("...");

        jLabel10.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel10.setText("Sexo:");

        lblSexo.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lblSexo.setText("...");

        jLabel12.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel12.setText("RFC:");

        lblRFC.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lblRFC.setText("...");

        jLabel14.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel14.setText("Nombre:");

        jLabel15.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel15.setText("Persona :");

        lblNombre.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lblNombre.setText("...");

        jLabel17.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel17.setText("Fecha de Nacimiento:");

        jLabel13.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel13.setText("Es Discapacitado:");

        lblDiscapacitado.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lblDiscapacitado.setText("...");

        jLabel7.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel7.setText("Total:");

        jLabel9.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel9.setText("$1,500 pesos");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(74, 74, 74)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15)
                    .addComponent(jLabel14)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(lblRFC, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblSexo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblTelefono, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblFechaNacimiento, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel17, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblDiscapacitado, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.LEADING))
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(lblNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 61, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel1))
                                .addGap(51, 51, 51)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtSerie)
                                    .addComponent(txtMarca)
                                    .addComponent(txtLinea)
                                    .addComponent(txtColor)
                                    .addComponent(txtModelo, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(56, 56, 56))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(generarPlaca, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(81, 81, 81))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel9)
                        .addGap(91, 91, 91))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(jLabel6)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtSerie, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtMarca, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtLinea, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addGap(19, 19, 19)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtColor, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGap(36, 36, 36)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(txtModelo, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(generarPlaca, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel14)
                        .addGap(11, 11, 11)
                        .addComponent(lblNombre)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblFechaNacimiento)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel8)
                        .addGap(16, 16, 16)
                        .addComponent(lblTelefono)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel10)
                        .addGap(18, 18, 18)
                        .addComponent(lblSexo)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblRFC)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel13)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel7)
                        .addComponent(jLabel9))
                    .addComponent(lblDiscapacitado))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 67, Short.MAX_VALUE)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Regresa al modulo anterior
     * @param evt ...
     */
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        regresarModuloPlaca();
        this.cerrarVentanaActual();
    }//GEN-LAST:event_jButton2ActionPerformed

    /**
     * Genera la Placa
     * @param evt ...
     */
    private void generarPlacaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_generarPlacaActionPerformed
        // TODO add your handling code here:
        Placa placa = this.generarPlaca();
        if (placa == null) {
            return;
        }
        this.placaGeneradaExitosamente(placa);
        this.irMenu();
        this.cerrarVentanaActual();

    }//GEN-LAST:event_generarPlacaActionPerformed

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
//            java.util.logging.Logger.getLogger(ModuloPlacaAutoNuevo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(ModuloPlacaAutoNuevo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(ModuloPlacaAutoNuevo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(ModuloPlacaAutoNuevo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new ModuloPlacaAutoNuevo().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton generarPlaca;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel lblDiscapacitado;
    private javax.swing.JLabel lblFechaNacimiento;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblRFC;
    private javax.swing.JLabel lblSexo;
    private javax.swing.JLabel lblTelefono;
    private javax.swing.JTextField txtColor;
    private javax.swing.JTextField txtLinea;
    private javax.swing.JTextField txtMarca;
    private javax.swing.JTextField txtModelo;
    private javax.swing.JTextField txtSerie;
    // End of variables declaration//GEN-END:variables
}
