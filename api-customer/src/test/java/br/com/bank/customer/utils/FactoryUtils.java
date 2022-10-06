package br.com.bank.customer.utils;

import br.com.bank.authorization.entity.UserAccount;
import br.com.bank.customer.entity.enumerator.AccountStatus;
import br.com.bank.customer.gateway.repository.model.AccountModel;
import br.com.bank.customer.gateway.repository.model.PeopleModel;

import java.time.LocalDateTime;

public class FactoryUtils {
    private FactoryUtils(){}

    public static String AUTHORIZATION_TOKEN = "Bearer eyJhbGciOiJIUzUxMiJ9.eyJhY2NvdW50Ijp7Im51bWJlciI6OTAwMDAwMDIxLCJkaWdpdCI6NiwiYWdlbmN5IjoiMDAwMSJ9LCJzdWIiOiI5ODQ0OTAyMDAyNiIsImlhdCI6MTY2NDg5MzU2MSwiZXhwIjoxNjY0ODk0NzYxfQ.ivCO-F3R--2D6jer4oR4rOe51mHn3mDELBjLXpUh9kVTaMZaRec75ahSctjVFvbF7_YmHt-Euipy5EkJfLd3sg";
    public static int DIGIT = 2;
    public static int NUMBER = 900000001;
    public static String AGENCY = "0001";
    public static String DOCUMENT = "00000000191";
    public static String NAME = "Mock Test";

    public static PeopleModel createPeopleModel(){
        var peopleModel = new PeopleModel();
        peopleModel.setName(NAME);
        peopleModel.setDocument(DOCUMENT);
        peopleModel.setCreatedAt(LocalDateTime.now());
        peopleModel.setUpdatedAt(LocalDateTime.now());

        return peopleModel;
    }

    public static AccountModel createAccountModel(int cusPersonId){
        var accountModel = new AccountModel();
        accountModel.setDigit(DIGIT);
        accountModel.setNumber(NUMBER);
        accountModel.setAgency(AGENCY);
        accountModel.setOpenedAt(LocalDateTime.now());
        accountModel.setCusPersonId(cusPersonId);
        accountModel.setStatus(AccountStatus.ACTIVE.getStatus());

        return accountModel;
    }

    public static UserAccount createUserAccount(){
       return new UserAccount().setAgency(AGENCY).setNumber(NUMBER).setDigit(DIGIT).setDocument(DOCUMENT);
    }


}
