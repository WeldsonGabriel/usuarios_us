package Cadastro.Cadastro_us.controller;

import Cadastro.Cadastro_us.dto.UsuarioDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class UsuarioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void fluxoCrudCompleto() throws Exception {

        // CREATE
        UsuarioDto dto = new UsuarioDto("crud@test.com", "Usuario Teste", true);

        String response = mockMvc.perform(post("/usuarios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andReturn()
                .getResponse()
                .getContentAsString();

        Integer id = objectMapper.readTree(response).get("id").asInt();

        // READ
        mockMvc.perform(get("/usuarios/" + id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value("crud@test.com"));

        // UPDATE
        UsuarioDto dtoAtualizado = new UsuarioDto("novo@email.com", "Novo Nome", true);

        mockMvc.perform(put("/usuarios/" + id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dtoAtualizado)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value("novo@email.com"))
                .andExpect(jsonPath("$.nome").value("Novo Nome"));


        // DELETE LOGICO
        mockMvc.perform(get("/usuarios/isActive/false"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[?(@.id==" + id + ")]").exists());

        // BUSCAR POR FALSE ISACTIVE
        mockMvc.perform(get("/usuarios/isActive/false"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[?(@.id==" + id + ")]").exists());

        // DELETE
        mockMvc.perform(delete("/usuarios/" + id))
                .andExpect(status().isNoContent());

        // BUSCAR APÓS DELETE → deve dar erro
        mockMvc.perform(get("/usuarios/" + id))
                .andExpect(status().is5xxServerError());
    }
}
