package br.com.bank.resolver;

import br.com.bank.input.PersonInput;
import br.com.bank.model.Person;
import br.com.bank.repository.PersonRepository;
import br.com.bank.subscription.Subscribers;
import br.com.bank.subscription.SubscribersRegister;
import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.coxautodev.graphql.tools.GraphQLSubscriptionResolver;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class PersonResolver implements GraphQLQueryResolver, GraphQLMutationResolver, GraphQLSubscriptionResolver {

    @Autowired
    private PersonRepository repository;

    private Subscribers subscribers;

    public PersonResolver() {
        subscribers = SubscribersRegister.register(Person.class);
    }

    public Collection<Person> findAllPeople(){
        return repository.findAll();
    }

    public Person findPersonById(Long id){
        return repository.findById(id).get();
    }

    public Person savePerson(PersonInput input){
        return repository.save(new Person(input.getName(), input.getAge(), input.getGender()));
    }

    public Person updateAge(Long personId, Integer age){
        Person person = repository.findById(personId).get();
        person.setAge(age);
        repository.save(person);

        SubscribersRegister.emit(personId, person);

        return person;
    }

    public Publisher<Person> onPersonUpdated(Long personId){
        return subscribers.subscription(personId);
    }

}
