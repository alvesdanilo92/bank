package br.com.bank.http.commons.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.zalando.logbook.spring.LogbookProperties;

import java.util.List;

@ConfigurationProperties("logbook")
public class CustomLogbookProperties {

    private List<String> exclude;
    private LogbookProperties.Obfuscate obfuscate;

    public List<String> getExclude() {
        return exclude;
    }

    public void setExclude(List<String> exclude) {
        this.exclude = exclude;
    }

    public LogbookProperties.Obfuscate getObfuscate() {
        return obfuscate;
    }

    public void setObfuscate(LogbookProperties.Obfuscate obfuscate) {
        this.obfuscate = obfuscate;
    }
}
