package com.java.ee.cliente.ciclovidajpa;

import static com.java.ee.cliente.ciclovidajpa.ActualizarObjetoSesionLarga.log;
import com.java.ee.domain.Persona;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EliminarObjetoJPA {
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
        Persona persona1 = em.find(Persona.class, 2);   
        
        //Paso 3. Termina transaccion 1
        tx.commit();
        
        //Objeto en estado detached
        log.debug("Objeto encontrado:" + persona1);
        
        //Paso 4. Inicia transaccion 2
        EntityTransaction tx2 = em.getTransaction();
        tx2.begin(); 
        
        //Paso 5. Ejecuta SQL que es un delete
        em.remove(em.merge(persona1));       
        
        //Paso 6. Termina transaccion 2
        tx2.commit();
        
        //Objeto en estado detached ya eliminado       
        log.debug("Objeto eliminado:" + persona1);
        
        //cerramos el entity manager
        em.close();
    }
}
