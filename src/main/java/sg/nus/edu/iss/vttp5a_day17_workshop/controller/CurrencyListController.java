package sg.nus.edu.iss.vttp5a_day17_workshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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

}
