package br.com.bank.service;

import br.com.bank.model.Usuario;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class UsuariosService {

    public List<Usuario> getUsuarios(){
        return Arrays.asList(new RestTemplateBuilder().build().getForObject("http://localhost:5010/usuarios", Usuario[].class));
    }

}
