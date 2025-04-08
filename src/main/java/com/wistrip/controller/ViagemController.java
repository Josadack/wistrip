package com.wistrip.controller;

import com.wistrip.dtos.ViagemDto;
import com.wistrip.model.ViagemModel;
import com.wistrip.services.ServiceUsuario;
import com.wistrip.services.ViagemService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*",allowedHeaders = "*")
@RequestMapping("/viagens")
public class ViagemController {

    @Autowired
    private ViagemService viagemService;

    @Autowired
    private ServiceUsuario serviceUsuario;

    //Criar viagem
    @PostMapping
    public ResponseEntity<ViagemModel> criarViagem(@RequestBody @Valid ViagemDto viagemDTO) {
        var viagemModel = new ViagemModel();
        BeanUtils.copyProperties(viagemDTO, viagemModel);
        if(serviceUsuario.existsById(viagemModel.getUsuario().getId()))
              return ResponseEntity.status(HttpStatus.CREATED)
                      .body(viagemService.save(viagemModel));
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuario não existe!", null);
    }


    //Listar todas a viagens
    @GetMapping
    public ResponseEntity <List<ViagemModel>> getViagem(){
        return ResponseEntity.ok(viagemService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ViagemModel> getOne(@PathVariable Long id){
        return viagemService.findById(id)
                .map(resposta -> ResponseEntity.ok(resposta))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping("/destino/{nome}")
    public ResponseEntity<List<ViagemModel>> findByNome(@PathVariable String nome){
        return ResponseEntity.ok(viagemService.findByNome(nome));
    }

    @GetMapping("/custo/{custo_total}")
    public ResponseEntity<List<ViagemModel>> findByCusto(@PathVariable double custo_total){
        return ResponseEntity.ok(viagemService.findByCustoTotal(custo_total));
    }

    //Atualizar Viagem
    @PutMapping
    public ResponseEntity<ViagemModel> putViagem(@RequestBody @Valid ViagemDto viagemDto){
        var viagemModel = new ViagemModel();
        BeanUtils.copyProperties(viagemDto, viagemModel);
        //Verificar se a Viagem existe
        if(viagemService.existsById(viagemModel.getId())){
            //Verificar se o usuario existe
            if(serviceUsuario.existsById(viagemModel.getUsuario().getId()))
                return ResponseEntity.status(HttpStatus.OK)
                        .body(viagemService.save(viagemModel));
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario não existe!", null);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    //Deletar Viagem
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteViagem(@PathVariable Long id){
        Optional<ViagemModel> viagemModel = viagemService.findById(id);
        if(viagemModel.isEmpty()){
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("Viagem não encontrada!");
        }
        viagemService.delete(viagemModel.get());
        return ResponseEntity.status(HttpStatus.OK).body("Viagem deletada com sucesso!");
    }
}
