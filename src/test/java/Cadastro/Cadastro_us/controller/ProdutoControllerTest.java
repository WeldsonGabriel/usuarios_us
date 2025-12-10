package Cadastro.Cadastro_us.controller;

import Cadastro.Cadastro_us.dto.ProdutoDto;
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
public class ProdutoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void fluxoCrudCompleto() throws Exception {

        // -----------------------------
        // CREATE
        // NÃO enviar dataCriacao, dataAtualizacao, isActive
        // -----------------------------
        ProdutoDto dtoAtualizado = new ProdutoDto(
                "9876543210987",
                "NF54321",
                "Eletrodomesticos",
                "Geladeira ABC",
                5L,
                899.99,
                "2026-01-01",
                "Fornecedor XYZ",
                "Geladeira com tecnologia avançada",
                "Unidade",
                null
        );


        String response = mockMvc.perform(post("/produtos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                // criado automaticamente
                .andExpect(jsonPath("$.dataCriacao").exists())
                .andExpect(jsonPath("$.isActive").value(true))
                .andReturn()
                .getResponse()
                .getContentAsString();

        Long id = objectMapper.readTree(response).get("id").asLong();


        // -----------------------------
        // READ BY ID
        // -----------------------------
        mockMvc.perform(get("/produtos/" + id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Smartphone XYZ"))
                .andExpect(jsonPath("$.isActive").value(true));


        // -----------------------------
        // UPDATE
        // -----------------------------
        ProdutoDto dtoAtualizado = new ProdutoDto(
                "9876543210987",
                "NF54321",
                "Eletrodomesticos",
                "Geladeira ABC",
                5L,
                899.99,
                "2026-01-01",
                "Fornecedor XYZ",
                "Geladeira com tecnologia avançada",
                "Unidade",
                true
        );

        mockMvc.perform(put("/produtos/" + id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dtoAtualizado)))
                .andExpect(status().isOk())
                // atualizado automaticamente
                .andExpect(jsonPath("$.dataAtualizacao").exists())
                .andExpect(jsonPath("$.nome").value("Geladeira ABC"));


        // -----------------------------
        // CONFIRMAR UPDATE
        // -----------------------------
        mockMvc.perform(get("/produtos/" + id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Geladeira ABC"));


        // -----------------------------
        // DELETE (LÓGICO)
        // -----------------------------
        mockMvc.perform(delete("/produtos/" + id))
                .andExpect(status().isOk()); // ou NoContent, depende do seu endpoint


        // -----------------------------
        // VERIFICAR QUE O isActive AGORA É FALSE
        // -----------------------------
        mockMvc.perform(get("/produtos/" + id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.isActive").value(false));
    }
}
