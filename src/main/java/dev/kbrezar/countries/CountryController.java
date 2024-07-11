package dev.kbrezar.countries;

import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
public class CountryController {

    @Autowired
    private CountryService countryService;
    @GetMapping("/countries/{query}")
    public ResponseEntity<List<Country>> searchForCountries(@PathVariable String query) {
        List<Country> response = countryService.find(query);
        return (null != response) ? new ResponseEntity<List<Country>>(response, HttpStatus.OK) : new ResponseEntity<>(null, HttpStatus.OK);
    }
}
