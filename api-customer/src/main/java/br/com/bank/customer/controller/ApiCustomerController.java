package br.com.bank.customer.controller;

import br.com.bank.authorization.annotation.AuthenticationRequered;
import br.com.bank.authorization.entity.UserAccount;
import br.com.bank.customer.controller.data.request.OpenAccountRequest;
import br.com.bank.customer.controller.data.request.ValidateAccountRequest;
import br.com.bank.customer.controller.data.response.AccountResponse;
import br.com.bank.customer.controller.data.response.AccountsResponse;
import br.com.bank.customer.usecase.AccountsUseCase;
import br.com.bank.customer.usecase.CloseAccountUseCase;
import br.com.bank.customer.usecase.OpenAccountUseCase;
import br.com.bank.customer.usecase.ValidateAccountUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Validated
@RestController
@AuthenticationRequered
@RequestMapping(value="customer", 
	produces=MediaType.APPLICATION_JSON_VALUE, 
	consumes=MediaType.APPLICATION_JSON_VALUE)
public class ApiCustomerController {

	private final AccountsUseCase accountsUseCase;
	private final OpenAccountUseCase openAccountUseCase;
	private final ValidateAccountUseCase validateAccountUseCase;
	private final CloseAccountUseCase closeAccountUseCase;

	public ApiCustomerController(AccountsUseCase accountsUseCase, OpenAccountUseCase openAccountUseCase, ValidateAccountUseCase validateAccountUseCase, CloseAccountUseCase closeAccountUseCase) {
		this.accountsUseCase = accountsUseCase;
		this.openAccountUseCase = openAccountUseCase;
		this.validateAccountUseCase = validateAccountUseCase;
		this.closeAccountUseCase = closeAccountUseCase;
	}

	@GetMapping("accounts")
	public AccountsResponse getAccounts(
			@RequestAttribute("UserAccount") UserAccount userAccount,
			@RequestHeader("Authorization") String authorization)  {
		return accountsUseCase.execute(userAccount.getDocument());
	}

	@PostMapping("account/validate")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@AuthenticationRequered(false)
	public void validateAccount(@Valid @RequestBody ValidateAccountRequest validateAccountRequest)  {
		validateAccountUseCase.execute(validateAccountRequest);
	}

	@PostMapping("account/open")
	@AuthenticationRequered(false)
	@ResponseStatus(HttpStatus.CREATED)
	public AccountResponse openAccount(@Valid @RequestBody OpenAccountRequest openAccountRequest)  {
		return openAccountUseCase.execute(openAccountRequest);
	}

	@PutMapping("account/close")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void closeAccount(
			@RequestHeader String authorization,
			@RequestAttribute("UserAccount") UserAccount userAccount)  {
		closeAccountUseCase.execute(authorization, userAccount.getDocument());
	}
}

