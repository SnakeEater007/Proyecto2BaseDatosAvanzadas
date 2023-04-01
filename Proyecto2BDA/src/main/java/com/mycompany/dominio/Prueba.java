/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.mycompany.dominio;

import com.mycompany.daos.LicenciaDAO;
import com.mycompany.daos.PersonaDAO;
import com.mycompany.daos.PlacaDAO;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Random;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author edemb
 */
public class Prueba {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Proyecto2BDA");
        EntityManager em = emf.createEntityManager();
//        Persona p1 = new Persona("Juan", "García", "Pérez", "GAPJ920702", new GregorianCalendar(2017, Calendar.JANUARY, 22), "5512345678", Sexo.MASCULINO, Discapacitado.NO);
//        Persona p2 = new Persona("María", "López", "Hernández", "LOHM900529", new GregorianCalendar(1990, Calendar.MAY, 29), "5534567890", Sexo.FEMENINO, Discapacitado.NO);
//        Persona p3 = new Persona("Pedro", "Sánchez", "Ramírez", "SARP970825", new GregorianCalendar(1997, Calendar.AUGUST, 25), "5252611989", Sexo.MASCULINO, Discapacitado.NO);
//        Persona p4 = new Persona("Laura", "Martínez", "Gómez", "MAGL840704", new GregorianCalendar(1984, Calendar.JULY, 4), "5512345678", Sexo.FEMENINO, Discapacitado.SI);
//        Persona p5 = new Persona("Miguel", "Rodríguez", "Torres", "ROTM910627", new GregorianCalendar(1991, Calendar.JUNE, 27), "5534567890", Sexo.FEMENINO, Discapacitado.NO);
//        Persona p6 = new Persona("Ana", "González", "Vázquez", "GOVA810118", new GregorianCalendar(1981, Calendar.JANUARY, 18), "5523456789", Sexo.FEMENINO, Discapacitado.SI);
//        Persona p7 = new Persona("José", "Díaz", "Castro", "DICJ750712", new GregorianCalendar(1975, Calendar.JULY, 12), "5512345678", Sexo.MASCULINO, Discapacitado.NO);
//        Persona p8 = new Persona("Paula", "Reyes", "Flores", "REFP800522", new GregorianCalendar(1980, Calendar.MAY, 22), "5534567890", Sexo.MASCULINO, Discapacitado.SI);
//        Persona p9 = new Persona("Ricardo", "Hernández", "López", "HELJ860423", new GregorianCalendar(1986, Calendar.APRIL, 23), "5523456789", Sexo.FEMENINO, Discapacitado.NO);
//        Persona p10 = new Persona("Carmen", "Perez", "García", "PEGJ950303", new GregorianCalendar(1995, Calendar.MARCH, 3), "5512345678", Sexo.FEMENINO, Discapacitado.SI);
//        Persona p11 = new Persona("Fernando", "Romero", "Sánchez", "ROSF920611", new GregorianCalendar(1992, Calendar.JUNE, 11), "5534567890", Sexo.MASCULINO, Discapacitado.NO);
//        Persona p12 = new Persona("Juan", "Perez", "García", "PEGJ940201", new GregorianCalendar(1994, Calendar.FEBRUARY, 4), "5551234567", Sexo.MASCULINO, Discapacitado.NO);
//        Persona p13 = new Persona("Ana", "Torres", "Hernández ", "TOHA960612", new GregorianCalendar(1996, Calendar.JUNE, 12), "5552345678", Sexo.FEMENINO, Discapacitado.SI);
//        Persona p14 = new Persona("Luis", "García", "González", "GAGL891223", new GregorianCalendar(1989, Calendar.DECEMBER, 23), "5553456789", Sexo.MASCULINO, Discapacitado.SI);
//        Persona p15 = new Persona("Carmen", "Sánchez", "Martínez", "SAMC950709", new GregorianCalendar(1995, Calendar.JULY, 9), "5554567890", Sexo.FEMENINO, Discapacitado.NO);
//        Persona p16 = new Persona("Ricardo", "Martínez", "Torres", "MATR930523", new GregorianCalendar(1993, Calendar.MAY, 23), "5555678901", Sexo.MASCULINO, Discapacitado.NO);
//        Persona p17 = new Persona("Patricia", "Flores", "Cruz", "FOCR901209", new GregorianCalendar(1990, Calendar.DECEMBER, 9), " 5556789012", Sexo.FEMENINO, Discapacitado.SI);
//        Persona p18 = new Persona("Fernando", "Ruiz", "Hernández", "RUIF860422", new GregorianCalendar(1986, Calendar.APRIL, 22), "5557890123", Sexo.MASCULINO, Discapacitado.SI);
//        Persona p19 = new Persona("María", "Martínez", "López", "MALL980108", new GregorianCalendar(1998, Calendar.JANUARY, 8), "5558901234", Sexo.FEMENINO, Discapacitado.NO);
//        Persona p20 = new Persona("Jorge", "Hernández", "Sánchez", "HESJ920706", new GregorianCalendar(1992, Calendar.JULY, 6), "5559012345", Sexo.MASCULINO, Discapacitado.SI);
//        em.getTransaction().begin();
//        em.persist(p1);
//        em.persist(p2);
//        em.persist(p3);
//        em.persist(p4);
//        em.persist(p5);
//        em.persist(p6);
//        em.persist(p7);
//        em.persist(p8);
//        em.persist(p9);
//        em.persist(p10);
//        em.persist(p11);
//        em.persist(p12);
//        em.persist(p13);
//        em.persist(p14);
//        em.persist(p15);
//        em.persist(p16);
//        em.persist(p17);
//        em.persist(p18);
//        em.persist(p19);
//        em.persist(p20);
//               em.getTransaction().commit();

        Persona p = em.find(Persona.class, 2L);
        Vehiculo carro = em.find(Automovil.class, 1L);
//        
        PlacaDAO placaDao = new PlacaDAO(em);
       Placa placa = placaDao.generarPlacaVehiculoUsado(p, carro);
       
        System.out.println(placa.getNumero());
        
        Query query = em.createQuery("Select p from Placa p where p.persona = :per AND p.estado = :est ");

        query.setParameter("per", p);
        query.setParameter("est", Estado.ACTIVA);
        List<Placa> list = query.getResultList();

        for (Placa o : list) {
            System.out.println(o.getId());
        }
//        placaDao.generarPlacaVehiculoNuevo(p, carro);
//        for (int o = 0; o < 10; o++) {
//            char[] arr = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B',
//                'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P',
//                'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd',
//                'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r',
//                's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
//            String placa = "";
//            int i = 0;
//            while (i < 7) {
//                for (int j = 0; j < 3; j++) {
//                    int num = (int) Math.floor(Math.random() * arr.length);
//                    char caracter = arr[num];
//                    placa += caracter;
//                    i++;
//                }
//                if (i < 7) {
//                    placa += "-";
//                }
//                i++;
//            }
//            System.out.println(placa);
//        }

//    Vehiculo carro = new Automovil("Masda", "Homda", "Royp", "Recta", "400");
//    em.getTransaction().begin();
//    em.persist(carro);
//    em.getTransaction().commit();

    



//
//               
//               LicenciaDAO licenciaDAO = new LicenciaDAO(em);
//               licenciaDAO.generarLicencia(Vigencia.Ano_1,p);
//
//        Query query = em.createQuery("Select l from Tramite l where l.persona = :per").setParameter("per", p);
//
//        List<Tramite> list = query.getResultList();
//        
//        for(Tramite o : list){
//            System.out.println(o.getId());
//        }
//        Persona p = em.find(Persona.class, 15L);
//        Licencia l = new Licencia(new GregorianCalendar(2023, 03, 31), new GregorianCalendar(2026, 03, 31), 600F, "234234234", p);
//        Vehiculo v = new Automovil("swe", "wewerqw", "aeqwe", "weqw", "aweq");
//        v= em.find(Vehiculo.class, 2L);
//        
//        Placa placs = new Placa(new GregorianCalendar(2023, 03, 31), 8000F, p, "tsdtsdg", Estado.ACTIVA, v);
//        em.getTransaction().begin();
//        
//        //em.persist(v);
//        em.persist(placs);
//
//        em.getTransaction().commit();
//
//        CriteriaBuilder cb = em.getCriteriaBuilder();
//        CriteriaQuery<Licencia> cq = cb.createQuery(Licencia.class);
//        Root<Licencia> from = cq.from(Licencia.class);
//        CriteriaQuery select = cq.select(from);
//
//        TypedQuery<Licencia> type = em.createQuery(select);
//        List<Licencia> list = type.getResultList();
//        Query query = em.createQuery("Select l from Licencia l where p.persona = :per").setParameter("per", p);
//
//        List<Licencia> list = query.getResultList();
//
//        for (Licencia o : list) {
//          SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
//            System.out.println(o.getId()+","+formateador.format(o.getFechaEmision().getTime())+","+formateador.format(o.getFechaVigencia().getTime())+","+o.getCosto());
//        }
//
//        em.close();
//                  SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
//
//        GregorianCalendar fecha = new GregorianCalendar();
//                System.out.println(formateador.format(fecha.getTime()));
//
//        fecha.add(0, 3);
//        
//        System.out.println(formateador.format(fecha.getTime()));
//
//        GregorianCalendar fecha2 = new GregorianCalendar();
//        fecha2.add(1, 3);
//        
//        System.out.println(formateador.format(fecha2.getTime()));
//                GregorianCalendar fecha3 = new GregorianCalendar();
//        fecha3.add(2, 3);
//        
//        System.out.println(formateador.format(fecha3.getTime()));
//    
//                       GregorianCalendar fecha4 = new GregorianCalendar();
//        fecha4.add(3, 3);
//        
//        System.out.println(formateador.format(fecha4.getTime()));
    }

}