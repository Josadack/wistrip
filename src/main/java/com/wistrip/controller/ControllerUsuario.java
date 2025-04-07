package com.wistrip.controller;

import com.wistrip.dtos.DtoUsuario;
import com.wistrip.model.ModelUsuario;
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
@RequestMapping("/usuario")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ControllerUsuario {

    @Autowired
    private ServiceUsuario serviceUsuario;

    //Listar Todos os Usuarios
    @GetMapping
    public ResponseEntity<List<ModelUsuario>> getAll() {
        List<ModelUsuario> usuarioList = serviceUsuario.findAll();
        if (!usuarioList.isEmpty()) {
            for (ModelUsuario usuario : usuarioList) {
                UUID id = usuario.getId();
                usuario.add(linkTo(methodOn(ControllerUsuario.class).getOne(id)).withSelfRel());
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body(usuarioList);
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

    //Criar usuario
    @PostMapping
    public ResponseEntity<Object> postUser(@Valid @RequestBody DtoUsuario dtoUsuario){
        if(serviceUsuario.existsByEmail(dtoUsuario.email())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Usuário já estar cadastrado");
        }
        var modelUsers = new ModelUsuario();
        BeanUtils.copyProperties(dtoUsuario, modelUsers);
        return ResponseEntity.status(HttpStatus.CREATED).body(serviceUsuario.save(modelUsers));
    }

    //Atualizar usuario
    @PutMapping
    public ResponseEntity<ModelUsuario> putUsers(@Valid @RequestBody DtoUsuario dtoUsuario){
        var modelUsers = new ModelUsuario();
        BeanUtils.copyProperties(dtoUsuario, modelUsers);
        return serviceUsuario.findById(modelUsers.getId())
                .map(resposta -> ResponseEntity.status(HttpStatus.OK)
                        .body(serviceUsuario.save(modelUsers)))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }



}
