package sg.nus.edu.iss.vttp5a_day17_workshop.components;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
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

    @Autowired
    JSONExtracter jsonExtracter;

    public List<String> getCurrencyList(){
        return convertJSONToList(urlCreator.getResponseEntityFromUrl(MyConstants.CURRENCYLISTURL).getBody());
    }

    private List<String> convertJSONToList(String responseBody){
        List<String> currencyList = new ArrayList<>();
        JsonObject jsonObject = Json.createReader(new StringReader(responseBody)).readObject();
        JsonObject jsonObjectAllCurrencies = jsonObject.getJsonObject("results");
        Set<String> keys = jsonObjectAllCurrencies.keySet();

        for(String key:keys){
            currencyList.add(jsonExtracter.extractCountryNamePlusSymbolFromJSON(key, jsonObjectAllCurrencies));
        }
        return currencyList;
    }

    
}
