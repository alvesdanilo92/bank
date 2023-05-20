package br.com.bank.service;

import br.com.bank.model.Endereco;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;

@Component
public class EnderecoService {

    public Endereco getEndereco(){
        return new RestTemplateBuilder().build().getForObject("http://localhost:5010/endereco/123", Endereco.class);
    }

}
