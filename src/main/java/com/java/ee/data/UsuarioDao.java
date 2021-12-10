package com.java.ee.data;

import com.java.ee.domain.Usuario;
import java.util.List;

public interface UsuarioDao {
    
    public List<Usuario> findAllUsuarios();
    
    public Usuario findUsuarioById(Usuario usuario);   
    
    public void insertUsuario(Usuario usuario);
    
    public void updateUsuario(Usuario usuario);
    
    public void deleteUsuario(Usuario usuario);
    
}
