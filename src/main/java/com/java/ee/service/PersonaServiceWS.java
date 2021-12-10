package com.java.ee.service;

import com.java.ee.domain.Persona;
import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface PersonaServiceWS {
    
    @WebMethod
    public List<Persona> listarPersonas();
    
}
