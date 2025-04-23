package com.wistrip.security;

import com.wistrip.model.ModelUsuario;
import com.wistrip.repository.RepositoryUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private RepositoryUsuario repositoryUsuario;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
            Optional<ModelUsuario> usuario = repositoryUsuario.findByEmail(email);

            if (usuario.isPresent())
                return new UserDetailsImpl(usuario.get());
            else
                throw new UsernameNotFoundException("Usuário não encontrado para o email: " + email);
    }
}
