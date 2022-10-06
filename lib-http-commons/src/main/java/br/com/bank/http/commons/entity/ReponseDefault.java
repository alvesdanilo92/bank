package br.com.bank.http.commons.entity;

public class ReponseDefault<T>  {
	private Metadata metadata;
    private T results;

    public Metadata getMetadata() {
        return metadata;
    }

    public ReponseDefault<T> setMetadata(Metadata metadata) {
        this.metadata = metadata;
        return this;
    }

    public T getResults() {
        return results;
    }

    public ReponseDefault<T> setResults(T results) {
        this.results = results;
        return this;
    }
}
