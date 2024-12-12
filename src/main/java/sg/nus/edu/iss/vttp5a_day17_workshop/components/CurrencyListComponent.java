package sg.nus.edu.iss.vttp5a_day17_workshop.components;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import sg.nus.edu.iss.vttp5a_day17_workshop.util.MyConstants;

@Component
public class CurrencyListComponent {
    @Autowired
    UrlCreator urlCreator;

    public List<String> getCurrencyList(){
        return convertJSONToList(urlCreator.getResponseEntityFromUrl(MyConstants.CURRENCYLISTURL).getBody());
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

    public Map<String, String> getCountryCodeAndName(){
        Map<String, String> countryCodeAndName = new HashMap<>();
        JsonObject jsonObject = Json.createReader(new StringReader(urlCreator.getResponseEntityFromUrl(MyConstants.CURRENCYLISTURL).getBody())).readObject();
        JsonObject jsonObjectAllCurrencies = jsonObject.getJsonObject("results");
        Set<String> keys = jsonObjectAllCurrencies.keySet();

        for(String key:keys){
            StringBuilder sb = new StringBuilder();
            JsonObject currencyInfo = jsonObjectAllCurrencies.getJsonObject(key);
            String currencyName = currencyInfo.getString("currencyName");
            String currencyId = currencyInfo.getString("id");

            Optional<String> currencySymbol = Optional.ofNullable(currencyInfo.getJsonString("currencySymbol"))
            .map(jakarta.json.JsonString::getString);
            currencySymbol.ifPresentOrElse((value) -> sb.append(currencyName).append(" ").append(value),
            () -> sb.append(currencyName));

            countryCodeAndName.put(sb.toString(), currencyId);
        }
        System.out.println(countryCodeAndName);
        return countryCodeAndName;
    }
}
