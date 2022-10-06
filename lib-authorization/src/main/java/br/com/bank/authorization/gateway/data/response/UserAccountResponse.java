package br.com.bank.authorization.gateway.data.response;

import br.com.bank.authorization.entity.Metadata;
import br.com.bank.authorization.entity.UserAccount;

public class UserAccountResponse {
	private Metadata metadata;
    private UserAccount results;

    public Metadata getMetadata() {
        return metadata;
    }

    public void setMetadata(Metadata metadata) {
        this.metadata = metadata;
    }

    public UserAccount getResults() {
        return results;
    }

    public void setResults(UserAccount results) {
        this.results = results;
    }
}
