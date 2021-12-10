/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.ee.cliente.criteria;

import java.util.List;
import javax.persistence.*;
import javax.persistence.criteria.*;
import org.apache.logging.log4j.*;

import com.java.ee.domain.Persona;
import java.util.ArrayList;

/**
 *
 * @author Hector
 */
public class PruebaApiCriteria {
    static Logger log = LogManager.getRootLogger();
    
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("SgaPU");
        EntityManager em = emf.createEntityManager();
        
        CriteriaBuilder cb = null;
        CriteriaQuery<Persona> criteriaQuery = null;
        Root<Persona> fromPersona = null;
        TypedQuery<Persona> query = null;
        Persona persona = null;
        List<Persona> personas = null;
        
        //Query utilizando el API de Criteria
        //1. Consulta de todas las personas
        
        //Paso1. El objeto EntityManager crea instancia CriteriaBuilder
        cb = em.getCriteriaBuilder();
        
        //Paso2. Se crea un objeto CriteriaQuery
        criteriaQuery = cb.createQuery(Persona.class);
        
        //Paso3. Creamos el objeto raiz de query
        fromPersona = criteriaQuery.from(Persona.class);
        
        //Paso4. seleccionamos lo necesario del from
        criteriaQuery.select(fromPersona);
        
        //Paso5. Creamos el qyery typeSafe
        query = em.createQuery(criteriaQuery);
        
        //Paso6. Ejecutamos la consulta
        personas = query.getResultList();
        
        //mostrarPersonas(personas);
        
        //2-a. Consulta de la Persona con id = 1
        //jpql = "select p from Persona p where p.idPersona = 1"
        log.debug("\n2-a. Consulta de la Persona con id = 1");
        cb = em.getCriteriaBuilder();
        criteriaQuery = cb.createQuery(Persona.class);
        fromPersona = criteriaQuery.from(Persona.class);
        criteriaQuery.select(fromPersona).where(cb.equal(fromPersona.get("id"), 3));
        persona = em.createQuery(criteriaQuery).getSingleResult();
        //log.debug(persona);
        
        //2-b. Consulta de la Persona con id = 1
        log.debug("\n2-b. Consulta de la Persona con id = 1");
        cb = em.getCriteriaBuilder();
        criteriaQuery = cb.createQuery(Persona.class);
        fromPersona = criteriaQuery.from(Persona.class);
        criteriaQuery.select(fromPersona);
        
        //La clase Predicate permite agregar varios criterios dinamicamente
        List<Predicate> criterios = new ArrayList<Predicate>();
        
        //Verificamos si tenemos criterios que agregar
        Integer idPersonaParam = 3;
        ParameterExpression<Integer> parameter = cb.parameter(Integer.class, "id");
        criterios.add( cb.equal(fromPersona.get("id"), parameter));
        
        //Se agregan los criterios
        if(criterios.isEmpty()){
            throw new RuntimeException("Sin criterios");
        }
        else if(criterios.size() == 1){
            criteriaQuery.where(criterios.get(0));
        }
        else{
            criteriaQuery.where(cb.and(criterios.toArray(new Predicate[0])));
        }
        
        query = em.createQuery(criteriaQuery);
        query.setParameter("id", idPersonaParam);
        
        //Se ejecuta el query
        persona = query.getSingleResult();
        log.debug(persona);

        
    }

    private static void mostrarPersonas(List<Persona> personas) {
        for(Persona p: personas){
            log.debug(p);
        }
    }   
    
}

