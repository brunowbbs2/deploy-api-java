package com.escola.crud.services;

import com.escola.crud.dtos.UsuarioDTO;
import com.escola.crud.entities.Usuario;
import com.escola.crud.repositories.UsuarioRepository;
import org.hibernate.mapping.Any;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UsuarioServiceTest {

    @Mock
    private UsuarioRepository repository;

    @InjectMocks
    private UsuarioService service;

    @Test
    void deveCriarUmUsuarioComSucesso(){

        //ARRANGE
        UsuarioDTO usuarioDTO = new UsuarioDTO(null, "Wesley", "wesley@gmail.com");
        Usuario usuarioDB = new Usuario(1L, "Wesley", "wesley@gmail.com");

        //ACT
        when(this.repository.save(any(Usuario.class))).thenReturn(usuarioDB);
        UsuarioDTO usuarioSalvo =  this.service.criar(usuarioDTO);


        //ASSERT
        assertNotNull(usuarioSalvo.getId());
        assertEquals("Wesley", usuarioSalvo.getNome());

    }


    @Test
    void deveListarTodosOsUsuario(){
        // ARRANGE
        Usuario u1 = new Usuario(1L, "Wesley", "wesley@gmail.com");
        Usuario u2 = new Usuario(2L, "Ana", "ana@gmail.com");


        //ACT
        when(this.repository.findAll()).thenReturn(List.of(u1, u2));
        List<UsuarioDTO> usuarios = this.service.listarTodos();

        //ASSERT
        assertEquals(2, usuarios.size());
        assertEquals("Wesley", usuarios.get(0).getNome());
        assertEquals("wesley@gmail.com", usuarios.get(0).getEmail());

        assertEquals("Ana", usuarios.get(1).getNome());
        assertEquals("ana@gmail.com", usuarios.get(1).getEmail());
    }


}
