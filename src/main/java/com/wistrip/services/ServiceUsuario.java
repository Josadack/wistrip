package com.wistrip.services;

import com.wistrip.model.ModelUsuario;
import com.wistrip.model.UsuarioLoginM;
import com.wistrip.repository.RepositoryUsuario;
import com.wistrip.security.JwtService;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ServiceUsuario {


    @Autowired
    private RepositoryUsuario repositoryUsuario;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    //cadastrar usuário
    public Optional<ModelUsuario> cadastrarUsuario(ModelUsuario modelUsuario){
        if(repositoryUsuario.findByEmail(modelUsuario.getEmail()).isPresent())
            return Optional.empty();

        modelUsuario.setSenha(criptografarSenha(modelUsuario.getSenha()));

        return Optional.of(repositoryUsuario.save(modelUsuario));
    }

    //Atualizar Usuario
    public Optional<ModelUsuario> atualizarUsuario(ModelUsuario  modelUsuario){

        if(repositoryUsuario.findById(modelUsuario.getId()).isPresent()){

            Optional<ModelUsuario> buscarUsuario = repositoryUsuario.findByEmail(modelUsuario.getEmail());

            if((buscarUsuario.isPresent()) && ( buscarUsuario.get().getId() != modelUsuario.getId()))
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuário já existe!", null);

            modelUsuario.setSenha(criptografarSenha(modelUsuario.getSenha()));

            return Optional.of(repositoryUsuario.save(modelUsuario));
        }

        return Optional.empty();
    }

    public Optional<UsuarioLoginM> autenticarUsuario(Optional<UsuarioLoginM> usuarioLogin) {

        //Gera o Objeto de autenticação
        var credenciais = new UsernamePasswordAuthenticationToken(usuarioLogin.get().getEmail(), usuarioLogin.get().getSenha());

        //Autentica o Usuário
        Authentication authentication = authenticationManager.authenticate(credenciais);

        //Se a autenticação foi efetuada com sucesso
        if (authentication.isAuthenticated()) {

            // Busca os dados do usuário
            Optional<ModelUsuario> usuario = repositoryUsuario.findByEmail(usuarioLogin.get().getEmail());

            // Se o usuário foi encontrado
            if (usuario.isPresent()) {

                // Preenche o Objeto usuarioLogin com os dados encontrados
                usuarioLogin.get().setId(usuario.get().getId());
                usuarioLogin.get().setNome(usuario.get().getNome());
                usuarioLogin.get().setData_nascimento(usuario.get().getData_nascimento());
                usuarioLogin.get().setTelefone(usuario.get().getTelefone());
                usuarioLogin.get().setFoto(usuario.get().getFoto());
                usuarioLogin.get().setToke(gerarToken(usuarioLogin.get().getEmail()));
                usuarioLogin.get().setSenha("");

                // Retorna o Objeto preenchido
                return usuarioLogin;

            }

        }

        return Optional.empty();

    }

    private String criptografarSenha(String senha) {

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        return encoder.encode(senha);

    }

    private String gerarToken(String usuario) {
        return "Bearer " + jwtService.generateToken(usuario);
    }


    public List<ModelUsuario> findAll() {
        return this.repositoryUsuario.findAll();
    }

    public List<ModelUsuario> findAllByNomeContainingIgnoreCase(String nome) {
        return this.repositoryUsuario.findAll();
    }




    public Optional<ModelUsuario> findById(UUID id) {
        return repositoryUsuario.findById(id);
    }

    public boolean existsByEmail(@NotBlank(message = "O email não pode ser vazio!") String email) {
        return repositoryUsuario.existsByEmail(email);
    }

    public boolean existsById(UUID id) {
        return repositoryUsuario.existsById(id);
    }
}
