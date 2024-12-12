package sg.nus.edu.iss.vttp5a_day17_workshop.controller;

import java.io.StringReader;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import sg.nus.edu.iss.vttp5a_day17_workshop.service.CurrencyConverterService;
import sg.nus.edu.iss.vttp5a_day17_workshop.service.CurrencyListService;


@Controller
@RequestMapping("/currency")
public class CurrencyListController {
    @Autowired
    CurrencyListService currencyListService;

    @Autowired
    CurrencyConverterService currencyConverterService;

    @GetMapping
    public String getCurrencyList(Model model){
        model.addAttribute("currencyList", currencyListService.getCurrencyList());
        return "currencylist";
    }

    @PostMapping
    public ModelAndView getAmount(@RequestBody MultiValueMap<String, String> params){
        ModelAndView mav = new ModelAndView();
        Double totalAmount = 0.0d;

        Map<String, String> countryCodeAndName = currencyListService.getCountryCodeAndName();
        String currFrom = countryCodeAndName.get(params.getFirst("currencyFrom"));
        String currTo = countryCodeAndName.get(params.getFirst("currencyTo"));
        String amount = params.getFirst("amount");

        String conversion = currencyConverterService.getConversionRate(currFrom, currTo);
        JsonObject jsonObject = Json.createReader(new StringReader(conversion)).readObject();
        Set<String> keys = jsonObject.keySet();
        for(String key:keys){
            totalAmount = Double.parseDouble(amount) * jsonObject.getJsonNumber(key).doubleValue();
        }

        mav.addObject("conversion", String.valueOf(totalAmount));
        mav.addObject("currFrom", params.getFirst("currencyFrom"));
        mav.addObject("currTo", params.getFirst("currencyTo"));
        mav.addObject("amount", params.getFirst("amount"));
        mav.setViewName("currencyconverter");
        return mav;
    }
}
