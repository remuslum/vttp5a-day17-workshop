package sg.nus.edu.iss.vttp5a_day17_workshop.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.nus.edu.iss.vttp5a_day17_workshop.repo.CurrencyListRepo;

@Service
public class CurrencyListService {
    @Autowired
    CurrencyListRepo currencyListRepo;

    public List<String> getCurrencyList(){
        return currencyListRepo.getCurrencyList();
    }

    public Map<String, String> getCountryCodeAndName(){
        return currencyListRepo.getCountryCodeAndName();
    }
    
}
