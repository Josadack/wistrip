package com.wistrip.controller;

import com.wistrip.dtos.DestinoDto;
import com.wistrip.model.DestinoModel;
import com.wistrip.services.DestinoService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*",allowedHeaders = "*")
@RequestMapping("/destino")
public class DestinoController {

    final DestinoService destinoService;
    public DestinoController(DestinoService destinoService){
        this.destinoService = destinoService;
    }

    //Cadastrar Destino
    @PostMapping
    public ResponseEntity<DestinoModel> postDestino(@Valid @RequestBody DestinoDto destinoDto){
        var destinoModel = new DestinoModel();
        BeanUtils.copyProperties(destinoDto, destinoModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(destinoService.save(destinoModel));
    }

    //Listar Todos os Destino
    @GetMapping
    public ResponseEntity<List<DestinoModel>> getAll(){
        return ResponseEntity.ok(destinoService.findAll());
    }

    //Listar Por Id
    @GetMapping("/{id}")
    public ResponseEntity<DestinoModel> getOne(@PathVariable Long id){
        return destinoService.findById(id)
                .map(resposta -> ResponseEntity.ok(resposta))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    //Busca por Descrição
    @GetMapping("/busca/{descricao}")
    public ResponseEntity<List<DestinoModel>> findByDescricao(@PathVariable String descricao){
        return ResponseEntity.ok(destinoService.findByDescricao(descricao));
    }

    //Busca por Interesses
    @GetMapping("/buscar/{interesses}")
    public ResponseEntity<List<DestinoModel>> findByInteresses(@PathVariable String interesses){
        return ResponseEntity.ok(destinoService.findByInteresses(interesses));
    }

    //Atualizar o Destino
    @PutMapping
    public ResponseEntity<DestinoModel> putDestino(@RequestBody @Valid DestinoDto destinoDto){
        var destinoModel = new DestinoModel();
        BeanUtils.copyProperties(destinoDto, destinoModel);
        return destinoService.findById(destinoModel.getId())
                .map(resposta -> ResponseEntity.status(HttpStatus.OK)
                        .body(destinoService.save(destinoModel)))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    //Deletar Destino
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteDestino(@PathVariable Long id){
        Optional<DestinoModel> destinoModel = destinoService.findById(id);
        if(!destinoModel.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Destino não Encontrado!");
        }
        destinoService.delete(destinoModel.get());
        return ResponseEntity.status(HttpStatus.OK).body("Destino deletado com Sucesso!");
    }
}
