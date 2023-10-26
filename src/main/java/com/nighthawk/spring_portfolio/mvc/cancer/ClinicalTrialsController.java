package src.main.java.com.nighthawk.spring_portfolio.mvc.cancer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/clinical-trials")
public class ClinicalTrialsController {

    private final ClinicalTrialsService clinicalTrialsService;

    @Autowired
    public ClinicalTrialsController(ClinicalTrialsService clinicalTrialsService) {
        this.clinicalTrialsService = clinicalTrialsService;
    }

    @GetMapping
    public String getClinicalTrials(@RequestParam String query) {
        return clinicalTrialsService.getClinicalTrials(query);
    }
}
