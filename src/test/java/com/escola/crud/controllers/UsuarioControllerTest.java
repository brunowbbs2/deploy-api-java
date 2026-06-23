package com.escola.crud.controllers;

import com.escola.crud.dtos.UsuarioDTO;
import com.escola.crud.services.UsuarioService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.TestConstructor;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@WebMvcTest(UsuarioController.class)
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
public class UsuarioControllerTest {

    private final MockMvc mockMvc;
    private final ObjectMapper objectMapper;

    @MockitoBean
    private UsuarioService service;

    public UsuarioControllerTest(MockMvc mockMvc, ObjectMapper objectMapper) {
        this.mockMvc = mockMvc;
        this.objectMapper = objectMapper;
    }


    @Test
    void deveCriarUmUsuarioComSucesso() throws Exception{

        //ARRANGE
        UsuarioDTO usuarioDTO = new UsuarioDTO(null, "Wesley", "wesley@gmail.com");
        UsuarioDTO usuarioDTOCriado = new UsuarioDTO(1L, "Wesley", "wesley@gmail.com");

        when(this.service.criar(usuarioDTO)).thenReturn(usuarioDTOCriado);

        String jsonString = this.objectMapper.writeValueAsString(usuarioDTOCriado);

        // ACTION
       MockHttpServletResponse response =  this.mockMvc.perform(post("/api/usuarios").contentType(MediaType.APPLICATION_JSON)
                .content(jsonString)).andDo(print()).andReturn().getResponse();


        // ASSERT
        assertThat(response.getStatus()).isEqualTo(201);
        assertThat(response.getContentAsString()).contains("Wesley");

    }

}
