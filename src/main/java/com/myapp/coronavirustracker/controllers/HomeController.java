package com.myapp.coronavirustracker.controllers;

import com.myapp.coronavirustracker.models.LocationStats;
import com.myapp.coronavirustracker.services.CoronaVirusDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    CoronaVirusDataService coronaVirusDataService;

    @GetMapping("/")
    public String home(Model model){
        List<LocationStats> allstats = coronaVirusDataService.getAllstats();
        int totalReportedCases =  allstats.stream().mapToInt(stat -> stat.getLatestTotalCases()).sum();
        int totalNewCases =  allstats.stream().mapToInt(stat -> stat.getDiffFromPreviousDay()).sum();
        model.addAttribute("locationStats",  allstats);
        model.addAttribute("totalReportedCases",  totalReportedCases);
        model.addAttribute("totalNewCases", totalNewCases);
        return "home";
    }
}
