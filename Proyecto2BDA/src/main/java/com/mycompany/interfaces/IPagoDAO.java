/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.interfaces;

import com.mycompany.dominio.Pago;
import com.mycompany.dominio.Tramite;

/**
 *
 * @author Usuario
 */
public interface IPagoDAO {
    Pago generarPago(Tramite tramite);
}
