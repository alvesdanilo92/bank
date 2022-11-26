package br.com.bank.authenticator.controller;

import br.com.bank.authenticator.controller.converter.CreateAuthenticationConverter;
import br.com.bank.authenticator.controller.data.request.AccountRequest;
import br.com.bank.authenticator.controller.data.request.JwtRequest;
import br.com.bank.authenticator.controller.data.response.CreateAuthenticationResponse;
import br.com.bank.authenticator.controller.data.response.JwtResponse;
import br.com.bank.authenticator.entity.UserAccountEntity;
import br.com.bank.authenticator.usecase.AuthenticateUseCase;
import br.com.bank.authenticator.usecase.CreateUserUseCase;
import br.com.bank.authenticator.usecase.DeactivateAccountUseCase;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

@Validated
@RestController
@RequestMapping(value="/authenticator",
        produces= MediaType.APPLICATION_JSON_VALUE,
        consumes = MediaType.APPLICATION_JSON_VALUE)
public class ApiAuthenticatorController {

    private final AuthenticateUseCase authenticateUseCase;
    private final CreateUserUseCase createUserUseCase;
    private final CreateAuthenticationConverter createAuthenticationConverter;
    private final DeactivateAccountUseCase deactivateAccountUseCase;

    public ApiAuthenticatorController(AuthenticateUseCase authenticateUseCase, CreateUserUseCase createUserUseCase, CreateAuthenticationConverter createAuthenticationConverter, DeactivateAccountUseCase deactivateAccountUseCase) {
        this.authenticateUseCase = authenticateUseCase;
        this.createUserUseCase = createUserUseCase;
        this.createAuthenticationConverter = createAuthenticationConverter;
        this.deactivateAccountUseCase = deactivateAccountUseCase;
    }

    @GetMapping("/validate")
    public UserAccountEntity validateAuthenticationToken(
            @RequestAttribute("UserAccount") UserAccountEntity userAccount){
        return userAccount;
    }

    @PostMapping("/authenticate")
    public JwtResponse createAuthenticationToken(
            @Valid @RequestBody JwtRequest authenticationRequest){
        return new JwtResponse(authenticateUseCase.execute(authenticationRequest.getUsername(), authenticationRequest.getPassword()));
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public CreateAuthenticationResponse createAuthentication(@RequestHeader @NotEmpty @CPF String document,
                                                             @RequestHeader @NotEmpty @Length( min = 6, max = 6 ) String password,
                                                             @Valid @RequestBody AccountRequest accountRequest){
        var newUserEntity = createAuthenticationConverter.toNewUserEntity(accountRequest, document, password);
        return createUserUseCase.execute(newUserEntity);
    }

    @PutMapping("/deactivate")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deactivateAccount(@RequestAttribute("UserAccount") UserAccountEntity userAccount){
        deactivateAccountUseCase.execute(userAccount.getDocument());
    }
}
