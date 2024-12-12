package sg.nus.edu.iss.vttp5a_day17_workshop.components;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import sg.nus.edu.iss.vttp5a_day17_workshop.util.MyConstants;

@Component
public class CurrencyConverter {
    @Autowired
    UrlCreator urlCreator;

    public String getConversionRate(String currFrom, String currTo){
        return urlCreator.getConversionRateFromUrl(MyConstants.CONVERTURL, currFrom, currTo).getBody();
    }
}
