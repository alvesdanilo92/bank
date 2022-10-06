package br.com.bank.customer.gateway.data.request;

public class CreateUserGatewayRequest {
    private final int number;
    private final int digit;
    private final String agency;

    public CreateUserGatewayRequest(int number, int digit, String agency) {
        this.number = number;
        this.digit = digit;
        this.agency = agency;
    }

    public int getNumber() {
        return number;
    }

    public int getDigit() {
        return digit;
    }

    public String getAgency() {
        return agency;
    }
}
