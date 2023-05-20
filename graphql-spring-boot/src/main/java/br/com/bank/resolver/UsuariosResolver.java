package br.com.bank.resolver;

import br.com.bank.model.Usuario;
import br.com.bank.service.UsuariosService;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
public class UsuariosResolver implements GraphQLQueryResolver {


    private final ExecutorService executorService = Executors.newFixedThreadPool(
      Runtime.getRuntime().availableProcessors()
    );

    @Autowired
    private UsuariosService usuariosService;

    public CompletableFuture<Collection<Usuario>> findAllUsers(){
        return CompletableFuture.supplyAsync( () -> {
            return usuariosService.getUsuarios();
        }, executorService);
    }
}
