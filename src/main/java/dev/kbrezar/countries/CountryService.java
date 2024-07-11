package dev.kbrezar.countries;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Service
public class CountryService {

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    public CountryService(RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }
    public List<Country> find(String query) {
        final ParameterizedTypeReference<List<Country>> typeRef = new ParameterizedTypeReference<List<Country>>() {
        };

        try {
            String url = "https://restcountries.com/v3.1/name/" + query;

            final String response = restTemplate.getForObject(url, String.class);

            return parseToCountries(response);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    private List<Country> parseToCountries(String json) throws JsonProcessingException {
        List<Country> parsedData = new LinkedList<Country>();
        int i = 0;
        JsonNode countries = objectMapper.readTree(json);
        while (i < 250) {
            if(countries.get(i) == null) {
                break;
            } else {
                JsonNode country = countries.get(i);
                JsonNode name = country.get("name").get("common");
                JsonNode capital = country.get("capital").get(0);
                JsonNode population = country.get("population");
                JsonNode region = country.get("region");
                JsonNode code= country.get("ccn3");
                parsedData.add(new Country(name.asText(), capital.asText(), population.asInt(), region.asText(), code.asInt()));
            }
            i++;
        }
        return parsedData;
    }
}
