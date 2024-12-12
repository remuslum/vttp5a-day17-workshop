package sg.nus.edu.iss.vttp5a_day17_workshop.components;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class UrlCreator {
    @Value("${api.key}")
    private String APIKEY;

    public ResponseEntity<String> getResponseEntityFromUrl(String url){
        String urlWithParams = UriComponentsBuilder.fromUriString(url).queryParam("apiKey", APIKEY).toUriString();
        RequestEntity<Void> request = RequestEntity.get(urlWithParams).accept(MediaType.APPLICATION_JSON).build();
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.exchange(request, String.class);
    }

    public ResponseEntity<String> getConversionRateFromUrl(String url, String currFrom, String currTo){
        String urlWithParams = UriComponentsBuilder.fromUriString(url).queryParam("apiKey", APIKEY)
        .queryParam("q", currFrom + "_" + currTo).queryParam("compact", "ultra").toUriString();
        RequestEntity<Void> request = RequestEntity.get(urlWithParams).accept(MediaType.APPLICATION_JSON).build();
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.exchange(request, String.class);
    }
}
