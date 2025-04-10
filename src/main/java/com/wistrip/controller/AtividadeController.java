package com.wistrip.controller;

import com.wistrip.dtos.AtividadeDto;
import com.wistrip.model.AtividadeModel;
import com.wistrip.services.AtividadeService;
import com.wistrip.services.DestinoService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/atividades")
@CrossOrigin(origins = "*",allowedHeaders = "*")
public class AtividadeController {

    @Autowired
    private AtividadeService atividadeService;

    @Autowired
    private DestinoService destinoService;


    //Criar Atividades
    @PostMapping
    public ResponseEntity<Object> criarAtividade(@RequestBody @Valid AtividadeDto atividadeDto){
        var atividadeModel = new AtividadeModel();
        BeanUtils.copyProperties(atividadeDto, atividadeModel);
        //Verificar se o destino
        if(!destinoService.existsById(atividadeModel.getDestino().getId())){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Destino não encontrado!");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(atividadeService.save(atividadeModel));
    }

    //Listar Todas  as Atividades
    @GetMapping
    public ResponseEntity<List<AtividadeModel>> getAll(){
        return ResponseEntity.ok(atividadeService.findByAll());
    }

    //Buscar por Id
    @GetMapping("/{id}")
    public ResponseEntity<AtividadeModel> getOne(@PathVariable Long id){
        return atividadeService.findById(id)
                .map(resposta -> ResponseEntity.ok(resposta))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    //Buscar Por Preço
    @GetMapping("/preco/{preco}")
    public ResponseEntity<List<AtividadeModel>>  findByPreco(@PathVariable double preco){
        return ResponseEntity.ok(atividadeService.findByPreco(preco));
    }

    //Bucar Por nome
    @GetMapping("/nome/{nome}")
    public ResponseEntity<List<AtividadeModel>> findByNome(@PathVariable String nome){
        return ResponseEntity.ok(atividadeService.findByNome(nome));
    }

    //Atulizar Atividade
    @PutMapping
    public ResponseEntity<AtividadeModel> putAtividade(@RequestBody @Valid AtividadeDto atividadeDto){
        var atividadeModel = new AtividadeModel();
        BeanUtils.copyProperties(atividadeDto, atividadeModel);

        if(atividadeService.existsById(atividadeModel.getId())){

            if(destinoService.existsById(atividadeModel.getDestino().getId()))
                return ResponseEntity.status(HttpStatus.OK)
                        .body(atividadeService.save(atividadeModel));
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Destino não encontrado", null);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    //Delete Atividade
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletarAtividade(@PathVariable Long id){
        Optional<AtividadeModel> atividadeModel = atividadeService.findById(id);
        if(!atividadeModel.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Atividade não Encontrada!");
        }
        atividadeService.delete(atividadeModel.get());
        return ResponseEntity.status(HttpStatus.OK).body("Atividade deletada com Sucesso!");
    }

}
