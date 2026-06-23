package com.escola.crud.repositories;

import com.escola.crud.entities.Usuario;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class UsuarioRepositoryTest {

    private final UsuarioRepository repository;

    @Autowired
    public UsuarioRepositoryTest(UsuarioRepository repository) {
        this.repository = repository;
    }


    //TESTAR SE É POSSIVEL SALVAR UM USUARIO NO BANCO DE DADOS
    @Test
    void deveSalvarUsuarioNoBancoComSucesso(){

        //ARRANGE (ORGANIZAR O CENARIO)
        Usuario usuario = new Usuario(null, "Wesley", "wesley@gmail.com");

        // ACT

        Usuario usuarioSalvo =  this.repository.save(usuario);

        // ASSERT
        assertNotNull(usuarioSalvo.getId());
    }

    //TESTAR SE É POSSIVEL BUSCAR UM USUARIO POR ID
    @Test
    void deveBuscarUmUsuarioPorId(){

        //ARRANGE (ORGANIZAR O CENARIO)
        Usuario usuario = new Usuario(null, "Wesley", "wesley@gmail.com");
        Usuario usuarioSalvo =  this.repository.save(usuario);

        //ACT
        Optional<Usuario> usuaioRetornado = this.repository.findById(usuarioSalvo.getId());

        //ASSERT
        assertTrue(usuaioRetornado.isPresent());
        assertEquals("Wesley", usuaioRetornado.get().getNome());

    }

    //TESTAR SE É POSSIVEL DELETAR UM USUARIO
    @Test
    void deveDeletarUmUsuario(){
        //ARRANGE
        Usuario usuario = new Usuario(null, "Wesley", "wesley@gmail.com");
        Usuario usuarioSalvo =  this.repository.save(usuario);

        //ACT
        this.repository.deleteById(usuarioSalvo.getId());

        //ASSERT
        Optional<Usuario>  usuarioEncontrado = this.repository.findById(usuarioSalvo.getId());
        assertFalse(usuarioEncontrado.isPresent());



    }


}
