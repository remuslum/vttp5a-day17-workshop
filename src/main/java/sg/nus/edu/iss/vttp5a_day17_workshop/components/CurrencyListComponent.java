package sg.nus.edu.iss.vttp5a_day17_workshop.components;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import sg.nus.edu.iss.vttp5a_day17_workshop.util.MyConstants;

@Component
public class CurrencyListComponent {
    @Value("${api.key}")
    private String APIKEY;

    public List<String> getCurrencyList(){
        return convertJSONToList(getResponseEntityFromUrl(MyConstants.CURRENCYLISTURL).getBody());
    }

    private List<String> convertJSONToList(String responseBody){
        List<String> currencyList = new ArrayList<>();
        JsonObject jsonObject = Json.createReader(new StringReader(responseBody)).readObject();
        JsonObject jsonObjectAllCurrencies = jsonObject.getJsonObject("results");
        Set<String> keys = jsonObjectAllCurrencies.keySet();

        for(String key:keys){
            StringBuilder sb = new StringBuilder();
            JsonObject currencyInfo = jsonObjectAllCurrencies.getJsonObject(key);
            String currencyName = currencyInfo.getString("currencyName");

            Optional<String> currencySymbol = Optional.ofNullable(currencyInfo.getJsonString("currencySymbol"))
            .map(jakarta.json.JsonString::getString);
            currencySymbol.ifPresentOrElse((value) -> sb.append(currencyName).append(" ").append(value),
            () -> sb.append(currencyName));
            currencyList.add(sb.toString());
        }

        return currencyList;


    }

    private ResponseEntity<String> getResponseEntityFromUrl(String url){
        String urlWithParams = UriComponentsBuilder.fromUriString(url).queryParam("apiKey", APIKEY).toUriString();
        RequestEntity<Void> request = RequestEntity.get(urlWithParams).accept(MediaType.APPLICATION_JSON).build();
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.exchange(request, String.class);
    }
}
