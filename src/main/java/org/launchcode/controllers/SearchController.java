package org.launchcode.controllers;

import org.launchcode.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")

public class SearchController {
/***Start of original code in here *
    @RequestMapping(value = "")
    public String search(Model model) {
        model.addAttribute("columns", SearchController.columnChoices);
        System.out.println("in here..");
        return "search";
    }

    **end of original code in here **/

/** new code **/
    // TODO #1 - Create handler to process search request and display results
    static HashMap<String, String> columnChoices = new HashMap<>();
    public SearchController () {

        columnChoices.put("core competency", "Skill");
        columnChoices.put("employer", "Employer");
        columnChoices.put("location", "Location");
        columnChoices.put("position type", "Position Type");
        columnChoices.put("name", "Name");
        columnChoices.put("all", "All");
    }

    @RequestMapping(value = "")
    public String search(Model model) {

        model.addAttribute("columns",SearchController.columnChoices);

        return "search";
    }
/**the below module "works" but in that it writes to html but it is garbage data just proves connection and passage of data
    @RequestMapping(value = "jobs")
    public String searchColumnValues(Model model , @RequestParam String searchType){
     ArrayList<String> items = JobData.findAll(searchType);
        model.addAttribute("title", "All " + columnChoices.get(searchType) + " Values");
        model.addAttribute("column", searchType);

        model.addAttribute("items", items);
        return "search-value";
    }
    **/
/** re-do of above  **/
    @RequestMapping(value = "results")
    public String searchJobsByColumnAndValue(Model model,
                                             @RequestParam String searchType, @RequestParam String searchTerm) {


        if (searchType.equals("all")) {
            ArrayList<HashMap<String, String>> jobs = JobData.findByValue(searchTerm);
            model.addAttribute("title", "Jobs with " + columnChoices.get(searchType) + ": " + searchTerm);
            model.addAttribute("jobs", jobs);

            model.addAttribute("columns", SearchController.columnChoices);

            return "search";
        } else {
            ArrayList<HashMap<String, String>> jobs = JobData.findByColumnAndValue(searchType, searchTerm);

            model.addAttribute("title", "Jobs with " + columnChoices.get(searchType) + ": " + searchTerm);
            model.addAttribute("jobs", jobs);

            model.addAttribute("columns", SearchController.columnChoices);

            return "search-value";
            //  return "search";
        }
    }
}
