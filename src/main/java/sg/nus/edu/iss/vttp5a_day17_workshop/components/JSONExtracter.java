package sg.nus.edu.iss.vttp5a_day17_workshop.components;

import java.util.Optional;

import org.springframework.stereotype.Component;

import jakarta.json.JsonObject;

@Component
public class JSONExtracter {
    public String extractCountryNamePlusSymbolFromJSON(String jsonKey, JsonObject jsonObject){
        StringBuilder sb = new StringBuilder();
        JsonObject currencyInfo = jsonObject.getJsonObject(jsonKey);
        String currencyName = currencyInfo.getString("currencyName");

        Optional<String> currencySymbol = Optional.ofNullable(currencyInfo.getJsonString("currencySymbol"))
        .map(jakarta.json.JsonString::getString);
        currencySymbol.ifPresentOrElse((value) -> sb.append(currencyName).append(" ").append(value),
        () -> sb.append(currencyName));

        return sb.toString();
    }
}
