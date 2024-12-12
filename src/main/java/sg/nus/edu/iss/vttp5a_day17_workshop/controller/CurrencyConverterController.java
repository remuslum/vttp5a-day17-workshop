package sg.nus.edu.iss.vttp5a_day17_workshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import sg.nus.edu.iss.vttp5a_day17_workshop.service.CurrencyConverterService;

@Controller
@RequestMapping("/currency")
public class CurrencyConverterController {
    @Autowired
    CurrencyConverterService currencyConverterService;

    @PostMapping
    public ModelAndView getAmount(@RequestBody MultiValueMap<String, String> params){
        ModelAndView mav = new ModelAndView();
        String countryNameFrom = params.getFirst("currencyFrom");
        String countryNameTo = params.getFirst("currencyTo");
        String amount = params.getFirst("amount");

        Double totalAmount = currencyConverterService.calculateConversion(countryNameFrom, countryNameTo, amount);

        mav.addObject("conversion", String.valueOf(totalAmount));
        mav.addObject("currFrom", params.getFirst("currencyFrom"));
        mav.addObject("currTo", params.getFirst("currencyTo"));
        mav.addObject("amount", params.getFirst("amount"));
        mav.setViewName("currencyconverter");
        return mav;
    }
}
