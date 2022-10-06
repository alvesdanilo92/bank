package br.com.bank.http.commons.config;


import br.com.bank.http.commons.config.properties.CustomLogbookProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.zalando.logbook.*;

import java.util.*;
import java.util.function.Predicate;

import static org.apache.commons.lang3.StringUtils.isNotBlank;
import static org.zalando.logbook.BodyFilters.accessToken;
import static org.zalando.logbook.BodyFilters.replaceJsonStringProperty;

@Configuration
public class CustomLogbookConfiguration {

    private final ObjectMapper objectMapper;
    private final CustomLogbookProperties customLogbookProperties;

    public CustomLogbookConfiguration(ObjectMapper objectMapper, CustomLogbookProperties customLogbookProperties) {
        this.objectMapper = objectMapper;
        this.customLogbookProperties = customLogbookProperties;
    }

    @Bean
    public Logbook logbook(
            final Predicate<RawHttpRequest> condition,
            final List<RawRequestFilter> rawRequestFilters,
            final List<RawResponseFilter> rawResponseFilters,
            final List<HeaderFilter> headerFilters,
            final List<QueryFilter> queryFilters,
            final List<BodyFilter> bodyFilters,
            final List<RequestFilter> requestFilters,
            final List<ResponseFilter> responseFilters,
            final HttpLogFormatter formatter,
            final HttpLogWriter writer) {

        return Logbook.builder()
                .condition(mergeWithExcludes(condition))
                .rawRequestFilters(rawRequestFilters)
                .rawResponseFilters(rawResponseFilters)
                .headerFilters(headerFilters)
                .queryFilters(queryFilters)
                .bodyFilters(bodyFilters)
                .bodyFilter(jsonBody(customLogbookProperties.getObfuscate().getParameters()))
                .bodyFilter(accessToken())
                .bodyFilter(formEncodedBody(customLogbookProperties.getObfuscate().getParameters()))
                .requestFilters(requestFilters)
                .responseFilters(responseFilters)
                .formatter(formatter)
                .writer(writer)
                .build();
    }

    private Predicate<RawHttpRequest> mergeWithExcludes(final Predicate<RawHttpRequest> predicate) {
        return customLogbookProperties.getExclude().stream()
                .map(Conditions::<RawHttpRequest>requestTo)
                .map(Predicate::negate)
                .reduce(predicate, Predicate::and);
    }

    private BodyFilter jsonBody(List<String> blackListParameters) {
        final Set<String> properties = new HashSet<>(blackListParameters);
        return replaceJsonStringProperty(properties, "***");
    }

    private BodyFilter formEncodedBody(List<String> blackListParameters) {
        return (contentType, body) -> {

            if (isForm(contentType)) {
                String[] keyValueString = getKeyValueString(body);
                Map<String, String> params = getParans(keyValueString, blackListParameters);
                try {
                    return objectMapper.writeValueAsString(params);
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                    return body;
                }
            } else {
                return body;
            }
        };
    }

    private Map<String, String> getParans(String[] keyValueString,List<String> blackListParameters){
        Map<String, String> params = new HashMap<>();
        for (String key : keyValueString) {
            String[] keyAndValue = key.split("=");
            if(keyAndValue.length > 1){
                String paramsKey = keyAndValue[0];
                String paramsValue = blackListParameters.contains(keyAndValue[0]) ? "***" : keyAndValue[1];
                params.put(paramsKey, paramsValue);
            }
        }
        return params;
    }

    private boolean isForm(String contentType){
        return isNotBlank(contentType) && contentType.contains("form");
    }

    private String[] getKeyValueString(String body){
        String[] keyValueString;

        if (body.contains("=") && body.contains("&")) {
            keyValueString = body.split("&");
        }else{
            keyValueString = new String[]{body};
        }
        return keyValueString;
    }

}
