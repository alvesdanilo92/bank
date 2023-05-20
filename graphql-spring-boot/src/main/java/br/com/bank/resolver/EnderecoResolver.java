package br.com.bank.resolver;

import br.com.bank.authorization.annotation.AuthenticationRequered;
import br.com.bank.model.Endereco;
import br.com.bank.service.EnderecoService;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
@AuthenticationRequered
public class EnderecoResolver implements GraphQLQueryResolver {

    private final ExecutorService executorService = Executors.newFixedThreadPool(
            Runtime.getRuntime().availableProcessors()
    );

    @Autowired
    private EnderecoService enderecoService;

    public CompletableFuture<Endereco> findAllEndereco(){
        return CompletableFuture.supplyAsync( () -> {
            return enderecoService.getEndereco();
        }, executorService);
    }
}
