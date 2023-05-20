package br.com.bank.resolver;

import br.com.bank.input.PetInput;
import br.com.bank.model.Person;
import br.com.bank.model.Pet;
import br.com.bank.repository.PersonRepository;
import br.com.bank.repository.PetRepository;
import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class PetResolver implements GraphQLQueryResolver, GraphQLMutationResolver {

    @Autowired
    private PetRepository repository;

    @Autowired
    private PersonRepository personRepository;

    public Collection<Pet> findAllPets(){
        return repository.findAll();
    }

    public Pet savePet(PetInput input){
        Person owner = personRepository.findById(input.getOwnerId()).get();
        return repository.save(new Pet(input.getName(), owner));
    }
}
