package sg.nus.edu.iss.vttp5a_day17_workshop.repo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import sg.nus.edu.iss.vttp5a_day17_workshop.components.CurrencyListComponent;

@Repository
public class CurrencyListRepo {
    @Autowired
    CurrencyListComponent currencyListComponent;

    public List<String> getCurrencyList(){
        return currencyListComponent.getCurrencyList();
    }

}
