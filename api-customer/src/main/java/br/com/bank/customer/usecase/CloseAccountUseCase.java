package br.com.bank.customer.usecase;

public interface CloseAccountUseCase {

    void execute(String authorization, String document);
}
