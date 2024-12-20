package sg.nus.edu.iss.vttp5a_day17_workshop.repo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import sg.nus.edu.iss.vttp5a_day17_workshop.components.CurrencyConverter;

@Repository
public class CurrencyConverterRepo {
    @Autowired
    CurrencyConverter currencyConverter;

    public Double calculateConversion(String countryNameFrom, String countryNameTo, String amount){
        return currencyConverter.calculateConversion(countryNameFrom, countryNameTo, amount);
    }

}
