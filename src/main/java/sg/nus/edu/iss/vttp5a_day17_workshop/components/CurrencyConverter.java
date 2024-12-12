package sg.nus.edu.iss.vttp5a_day17_workshop.components;

import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import sg.nus.edu.iss.vttp5a_day17_workshop.util.MyConstants;

@Component
public class CurrencyConverter {
    @Autowired
    UrlCreator urlCreator;

    @Autowired
    JSONExtracter jsonExtracter;

    public String getConversionRate(String currFrom, String currTo){
        return urlCreator.getConversionRateFromUrl(MyConstants.CONVERTURL, currFrom, currTo).getBody();
    }

    public Map<String, String> getCountryCodeAndName(){
        Map<String, String> countryCodeAndName = new HashMap<>();
        JsonObject jsonObject = Json.createReader(new StringReader(urlCreator.getResponseEntityFromUrl(MyConstants.CURRENCYLISTURL).getBody())).readObject();
        JsonObject jsonObjectAllCurrencies = jsonObject.getJsonObject("results");
        Set<String> keys = jsonObjectAllCurrencies.keySet();

        for(String key:keys){
            String currencyId = jsonObjectAllCurrencies.getJsonObject(key).getString("id");
            countryCodeAndName.put(jsonExtracter.extractCountryNamePlusSymbolFromJSON(key, jsonObjectAllCurrencies), currencyId);
        }
        return countryCodeAndName;
    }
}
