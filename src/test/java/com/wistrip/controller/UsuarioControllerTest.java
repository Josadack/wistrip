package com.wistrip.controller;

import com.wistrip.model.ModelUsuario;
import com.wistrip.repository.RepositoryUsuario;
import com.wistrip.services.ServiceUsuario;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.internal.matchers.Equals;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UsuarioControllerTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private RepositoryUsuario repositoryUsuario;

    @Autowired
    private ServiceUsuario serviceUsuario;

    @BeforeAll
    void start(){

        repositoryUsuario.deleteAll();

        serviceUsuario.cadastrarUsuario(new ModelUsuario( null,
               "root" ,"root@root.com" , "rootroot", LocalDate.now(), "-", "-"));
    }

    @Test
    @DisplayName("Cadastrar um Usuários")
    public void deveMostarCriarUsuarios(){
        HttpEntity<ModelUsuario> corpoRequisicao = new HttpEntity<ModelUsuario>(new ModelUsuario( null,
                "Paulo", "paulo@email.com", "123456789", LocalDate.now(), "", ""));

        ResponseEntity<ModelUsuario> corpoResposta = testRestTemplate
                .exchange("/usuarios/cadastrar", HttpMethod.POST, corpoRequisicao, ModelUsuario.class);

        assertEquals(HttpStatus.CREATED, corpoResposta.getStatusCode());
    }

    @Test
    @DisplayName("Não deve permitir duplicação do Usuário")
    public void naoDeveDuplicarUsuario() {
        serviceUsuario.cadastrarUsuario(new ModelUsuario(null,
                "Maria da Silva", "maria_silva@email.com.br", "13465278", LocalDate.now(),"-", "-"));
        HttpEntity<ModelUsuario> corpoRequisicao = new HttpEntity<ModelUsuario>(new ModelUsuario(null,
                "Maria da Silva", "maria_silva@email.com.br", "13465278", LocalDate.now(), "-", "-"));
        ResponseEntity<ModelUsuario> corpoResposta = testRestTemplate
                .exchange("/usuarios/cadastrar", HttpMethod.POST, corpoRequisicao, ModelUsuario.class);
        assertEquals(HttpStatus.BAD_REQUEST, corpoResposta.getStatusCode());
    }

    @Test
    @DisplayName("Atualizar um Usuário")
    public void deveAtualizarUmUsuario() {
        Optional<ModelUsuario> usuarioCadastrado = serviceUsuario.cadastrarUsuario(new ModelUsuario(null,
                "Juliana Andrews", "juliana_andrews@email.com.br", "juliana123", LocalDate.now(),"-", ""));
        ModelUsuario usuarioUpdate = new ModelUsuario(usuarioCadastrado.get().getId(),
                "Juliana Andrews Ramos", "juliana_ramos@email.com.br", "juliana123", LocalDate.now() ,"-", "-");
        HttpEntity<ModelUsuario> corpoRequisicao = new HttpEntity<ModelUsuario>(usuarioUpdate);
        ResponseEntity<ModelUsuario> corpoResposta = testRestTemplate
                .withBasicAuth("root@root.com", "rootroot")
                .exchange("/usuarios/atualizar", HttpMethod.PUT, corpoRequisicao, ModelUsuario.class);
        assertEquals(HttpStatus.OK, corpoResposta.getStatusCode());
    }

    @Test
    @DisplayName("Listar Todos os Usuários")
    public void deveMostarTodosUsuarios(){

        serviceUsuario.cadastrarUsuario(new ModelUsuario(null,
                "test2", "test2@email.com", "testeteste", LocalDate.now(), "-", "-"));

        serviceUsuario.cadastrarUsuario(new ModelUsuario(null,
                "test3", "test3@email.com", "testeteste", LocalDate.now(), "-", "-"));

        ResponseEntity<String> resposta = testRestTemplate
                .withBasicAuth("root@root.com", "rootroot")
                .exchange("/usuarios/all", HttpMethod.GET, null, String.class);
        assertEquals(HttpStatus.OK, resposta.getStatusCode());
    }
}
