package br.com.adopet.api.controller;

import br.com.adopet.api.domain.exception.AdopetException;
import br.com.adopet.api.domain.model.Abrigo;
import br.com.adopet.api.domain.repository.AbrigoRepository;
import br.com.adopet.api.domain.service.AbrigoService;
import br.com.adopet.api.dto.abrigo.AbrigoDTO;
import br.com.adopet.api.dto.abrigo.DadosAtualizarAbrigo;
import br.com.adopet.api.dto.abrigo.DadosCadatroAbrigo;
import br.com.adopet.api.dto.abrigo.DadosListagemAbrigo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class AbrigoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JacksonTester<DadosCadatroAbrigo> dadosCadatroAbrigo;

    @Autowired
    private JacksonTester<DadosAtualizarAbrigo> dadosAtualizarAbrigo;

    @Autowired
    private JacksonTester<AbrigoDTO> abrigoDTO;

    @MockBean
    private AbrigoService service;

    @MockBean
    private AbrigoRepository repository;

    @Test
    public void criarAbrigoRetornandoStatus400() throws Exception {
        var response = mockMvc.perform(post("/abrigos"))
                .andReturn().getResponse();

        Assertions.assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    public void criarAbrigoRetornandoStatus200() throws Exception {

        var abrigoDTO = new AbrigoDTO(null, "nome", "localizacao", null);

        when(service.criar(any(), any()))
                .thenReturn(ResponseEntity.ok(abrigoDTO));

        var response = mockMvc.perform(post("/abrigos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(dadosCadatroAbrigo.write(new DadosCadatroAbrigo("nome", "localizacao"))
                                .getJson()))
                .andReturn().getResponse();

        Assertions.assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

        var jsonEsperado = new ObjectMapper().writeValueAsString(abrigoDTO);

        Assertions.assertThat(response.getContentAsString()).isEqualTo(jsonEsperado);
    }

    @Test
    @WithMockUser
    public void buscarPorIdDeveriaDevolverStatus404() throws Exception {
        Long idInexistente = 999L;

        when(service.buscarPorId(idInexistente)).thenThrow(new AdopetException("Não foi encontrado abrigo com id = " + idInexistente));
        var response = mockMvc.perform(get("/abrigos/" + idInexistente)).andReturn().getResponse();

        Assertions.assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
    }

    @Test
    @WithMockUser
    public void buscarPorIdDeveriaDevolverStatus200() throws Exception {
        AbrigoDTO abrigoDTO = new AbrigoDTO(1L, "nome", "localizacao", null);

        when(service.buscarPorId(any())).thenReturn(ResponseEntity.ok(abrigoDTO));

        var response = mockMvc.perform(get("/abrigos/" + 1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        String jsonEsperado = new ObjectMapper().writeValueAsString(abrigoDTO);

        Assertions.assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        Assertions.assertThat(response.getContentAsString()).isEqualTo(jsonEsperado);
    }

    @Test
    @WithMockUser
    public void buscarTodosDeveriaDevolverStatus404() throws Exception {

        when(service.buscarTodos()).thenThrow(new AdopetException(""));
        var response = mockMvc.perform(get("/abrigos/")).andReturn().getResponse();

        Assertions.assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
    }

    @Test
    @WithMockUser
    public void buscarTodosDeveriaDevolverStatus200() throws Exception {
        var abrigo1 = new DadosListagemAbrigo(1L, "nome1", "localizacao1");
        var abrigo2 = new DadosListagemAbrigo(2L, "nome1", "localizacao1");


        List<DadosListagemAbrigo> abrigos = Arrays.asList(abrigo1, abrigo2);

        when(service.buscarTodos()).thenReturn(ResponseEntity.ok().body(abrigos));

        var response = mockMvc.perform(get("/abrigos")
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        String jsonEsperado = new ObjectMapper().writeValueAsString(abrigos);

        Assertions.assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        Assertions.assertThat(response.getContentAsString()).isEqualTo(jsonEsperado);
    }

    @Test
    @WithMockUser(roles = {"ABRIGO"})
    public void atualizarAbrigoDeveriaDevolverStatus400() throws Exception {

        var response = mockMvc.perform(put("/abrigos/99"))
                .andReturn().getResponse();

        Assertions.assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());

    }

    @Test
    @WithMockUser(roles = {"ABRIGO"})
    public void atualizarAbrigoDeveriaDevolverStatus200() throws Exception {
        var abrigo = new Abrigo(1L, "nome", "localizacao", null);
        var dados = new DadosAtualizarAbrigo("nome atualizado", "nova localizacao");
        var abrigoDTO = new AbrigoDTO(null, "nome atualizado", "nova localizacao", null);

        when(service.atualizar(dados, 1L)).thenReturn(ResponseEntity.ok(abrigoDTO));

        var response = mockMvc.perform(
                        put("/abrigos/1")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(dadosAtualizarAbrigo.write(dados).getJson()))
                .andReturn().getResponse();

        Assertions.assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

        var jsonEsperado = new ObjectMapper().writeValueAsString(abrigoDTO);
        Assertions.assertThat(response.getContentAsString()).isEqualTo(jsonEsperado);

        verify(service, times(1)).atualizar(eq(dados), eq(1L));
    }

    @Test
    @WithMockUser(roles = {"ABRIGO"})
    public void excluirAbrigoDeveriaDevolverStatus204() throws Exception {

        Long id = 1L;

        when(service.excluir(id)).thenReturn(ResponseEntity.noContent().build());

        var response = mockMvc.perform(delete("/abrigos/{id}", id))
                .andReturn().getResponse().getStatus();

        Assertions.assertThat(response).isEqualTo(HttpStatus.NO_CONTENT.value());
    }


}





