package org.example.controllers;

import org.example.models.Columns;
import org.example.models.Values;
import org.example.services.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
public class HomeController {
    @Autowired
    private HomeService homeService;

    @GetMapping("/home")
    public String homeGet() {
        return "home";
    }

    @PostMapping("/home")
    @ResponseBody
    public Values getValues(@RequestBody Columns columns) {
        Values values = new Values();
        values.setUniqueValues(homeService.getUniqueValues(columns.getSelectedColumn()));
        values.setDuplicatedValues(homeService.getDuplicatedValues(columns.getSelectedColumn()));
        return values;
    }

}
