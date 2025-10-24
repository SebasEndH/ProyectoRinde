package com.example.ProyectoRinde.Service;

import com.example.ProyectoRinde.DTO.UsuarioDTO;
import com.example.ProyectoRinde.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ProyectoRinde.Model.Usuario;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public boolean existeUsuarioConEmail(String email) {
        return usuarioRepository.existsByEmail(email);
    }

    public Usuario autenticar(String email, String password) {
        return usuarioRepository.findByEmailAndPassword(email, password);
    }

    public void registrar(Usuario usuario) {
        if (existeUsuarioConEmail(usuario.getEmail())) {
            throw new RuntimeException("El correo electrónico ya está registrado");
        } else {
            usuarioRepository.save(usuario);
        }
    }

    /*public UsuarioDTO convertirAUsuarioDTO(Usuario usuario) {
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setNombre(usuario.getNombre());
        usuarioDTO.setEmail(usuario.getEmail());
        return usuarioDTO;
    }

    public Usuario convertirDTOaUsuario(UsuarioDTO usuarioDTO) {
        Usuario usuario = new Usuario();
        usuario.setNombre(usuarioDTO.getNombre());
        usuario.setEmail(usuarioDTO.getEmail());
        return usuario;
    }*/

    public Usuario obtenerPorId(Long id) {
        return usuarioRepository.findById(id).orElse(null);
    }


}
