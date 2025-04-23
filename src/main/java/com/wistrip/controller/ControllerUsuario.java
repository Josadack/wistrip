package com.wistrip.controller;

import com.wistrip.dtos.DtoUsuario;
import com.wistrip.model.ModelUsuario;
import com.wistrip.model.UsuarioLoginM;
import com.wistrip.services.ServiceUsuario;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ControllerUsuario {

    @Autowired
    private ServiceUsuario serviceUsuario;

    //Listar Todos os Usuarios
    @GetMapping("/all")
    public ResponseEntity<List<ModelUsuario>> getAll() {
        return ResponseEntity.ok(serviceUsuario.findAll());
    }


    //Buscar Usuario por Id
    @GetMapping("/{id}")
    public ResponseEntity<Object> getOne(@PathVariable (value = "id") UUID id){
        Optional<ModelUsuario> modelUsers =  serviceUsuario.findById(id);
        if(modelUsers.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado");
        }
        modelUsers.get().add(linkTo(methodOn(ControllerUsuario.class)
                .getAll()).withRel("Lista de Usuarios"));
        return modelUsers.<ResponseEntity<Object>>
                map(modelUsuario -> ResponseEntity.status(HttpStatus.OK).body(modelUsuario))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("Id Não encontrado 🛑!"));
    }

    //Listar usuario por nome
    @GetMapping("nome/{nome}")
    public ResponseEntity<List<ModelUsuario>> getByName(@PathVariable String nome){
        return ResponseEntity.ok(serviceUsuario.findAllByNomeContainingIgnoreCase(nome));
    }

    @PostMapping("/logar")
    public ResponseEntity<UsuarioLoginM> autenticarUsuario(@RequestBody Optional<UsuarioLoginM> usuarioLogin){

        return serviceUsuario.autenticarUsuario(usuarioLogin)
                .map(resposta -> ResponseEntity.status(HttpStatus.OK).body(resposta))
                .orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
    }

    //Criar usuario
    @PostMapping("/cadastrar")
    public ResponseEntity<ModelUsuario> cadastrarUsuario(@RequestBody @Valid ModelUsuario usuario) {

        return serviceUsuario.cadastrarUsuario(usuario)
                .map(resposta -> ResponseEntity.status(HttpStatus.CREATED).body(resposta))
                .orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());

    }

    //Atualizar usuario
    @PutMapping("/atualizar")
    public ResponseEntity<ModelUsuario> atualizarUsuario(@Valid @RequestBody ModelUsuario usuario) {

        return serviceUsuario.atualizarUsuario(usuario)
                .map(resposta -> ResponseEntity.status(HttpStatus.OK).body(resposta))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());

    }




}
