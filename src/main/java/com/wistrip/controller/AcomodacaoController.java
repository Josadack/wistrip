package com.wistrip.controller;

import com.wistrip.dtos.AcomodacaoDto;
import com.wistrip.model.AcomodacaoModel;
import com.wistrip.services.AcomodacaoService;
import com.wistrip.services.DestinoService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RequestMapping("/acomodacao")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class AcomodacaoController {

    //Com o construtor
    final AcomodacaoService acomodacaoService;
    public AcomodacaoController(AcomodacaoService acomodacaoService){
        this.acomodacaoService = acomodacaoService;
    }

    //Com autowired
    @Autowired
    public DestinoService destinoService;

    //Listar Todas as Acomodações!
    @GetMapping
    public ResponseEntity<List<AcomodacaoModel>> getAll(){
        return ResponseEntity.ok(acomodacaoService.findAll());
    }

    //Buscar Por ID
    @GetMapping("/{id}")
    public ResponseEntity<AcomodacaoModel> getOne(@PathVariable Long id){
        return acomodacaoService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    //Buscar por Descrição
    @GetMapping("/buscar/{descricao}")
    public ResponseEntity<List<AcomodacaoModel>> findByDescricao(@PathVariable String descricao){
        return ResponseEntity.ok(acomodacaoService.findByDescricao(descricao));
    }

    //Criar Acomodações
    @PostMapping
    public ResponseEntity<AcomodacaoModel> postAcomodacao(@RequestBody @Valid AcomodacaoDto acomodacaoDto){
        var acomodacaoModel = new AcomodacaoModel();
        BeanUtils.copyProperties(acomodacaoDto, acomodacaoModel);
        if(destinoService.existsById(acomodacaoModel.getDestino().getId()))
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(acomodacaoService.save(acomodacaoModel));
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Destino não existe", null);
    }

    //Atualizar Acomodação
    @PutMapping("/{id}")
    public ResponseEntity<AcomodacaoModel> putAcomodacao(@RequestBody @Valid AcomodacaoDto acomodacaoDto){
        var acomodacaoModel = new AcomodacaoModel();
        BeanUtils.copyProperties(acomodacaoDto, acomodacaoModel);

        if(acomodacaoService.existsById(acomodacaoModel.getId())){

            if(destinoService.existsById(acomodacaoModel.getDestino().getId()))
                return ResponseEntity.status(HttpStatus.OK)
                        .body(acomodacaoService.save(acomodacaoModel));
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Destino na encontrado!", null);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    //Deletar Acomodação
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteAcomodacao(@PathVariable Long id){
        Optional<AcomodacaoModel> acomodacaoModel = acomodacaoService.findById(id);
        if(acomodacaoModel.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Acomodação não encontrada!");
        }
        acomodacaoService.delete(acomodacaoModel.get());
        return ResponseEntity.status(HttpStatus.OK).body("Acomodação deletada com sucesso!");
    }

}
