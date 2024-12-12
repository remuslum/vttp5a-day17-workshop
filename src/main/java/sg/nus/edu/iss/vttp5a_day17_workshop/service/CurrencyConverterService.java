package sg.nus.edu.iss.vttp5a_day17_workshop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.nus.edu.iss.vttp5a_day17_workshop.repo.CurrencyConverterRepo;

@Service
public class CurrencyConverterService {
    @Autowired
    CurrencyConverterRepo currencyConverterRepo;

    public Double calculateConversion(String countryNameFrom, String countryNameTo, String amount){
        return currencyConverterRepo.calculateConversion(countryNameFrom, countryNameTo, amount);
    }
    
}
