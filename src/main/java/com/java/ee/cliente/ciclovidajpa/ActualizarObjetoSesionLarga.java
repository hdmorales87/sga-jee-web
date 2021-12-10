/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.ee.cliente.ciclovidajpa;

import com.java.ee.domain.Persona;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author Hector
 */
public class ActualizarObjetoSesionLarga {
    static Logger log = LogManager.getRootLogger();
    
    public static void main(String[] args){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("SgaPU");
        EntityManager em = emf.createEntityManager();        
        
        //Inicia la transaccion        
        //Paso 1. Iniciar Transaccion 
        EntityTransaction tx = em.getTransaction();
        tx.begin();       
        
        //Paso 2. Ejecuta SQL de tipo select
        //El id debe existir en la base de datos
        Persona persona1 = em.find(Persona.class, 1);        
        
        log.debug("Objeto encontrado:" + persona1);
        
        //Paso 3. setValue(nuevoValor)
        persona1.setEmail("jjuarez@mail.com");
        
        persona1.setEmail("j.juarez@mail.com");
        
        //Paso 4. Termina la transaccion 1
        tx.commit();
        
        //Objeto en estado detached
        log.debug("Objeto recuperado - estado detached:" + persona1);
        
        //cerramos el entity manager
        em.close();
    }
}