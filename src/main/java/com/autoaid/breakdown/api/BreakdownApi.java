package com.autoaid.breakdown.api;

import com.autoaid.breakdown.model.Breakdown;
import com.autoaid.breakdown.service.BreakdownService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/breakdownapi")
public class BreakdownApi {

    private BreakdownService breakdownService;

    @Autowired
    public BreakdownApi(BreakdownService BreakdownService) {
        this.breakdownService = BreakdownService;
    }

    @GetMapping("/all")
    private List<Breakdown> getAllBreakdown() throws IOException {
        return breakdownService.getAllBreakdown();
    }

    @PostMapping("/create")
    private Breakdown createBreakdown(Breakdown Breakdown) {
        return breakdownService.createBreakdown(Breakdown);
    }

    @GetMapping("/get/{id}")
    private Breakdown getBreakdown(@PathVariable Long id) {
        return breakdownService.getBreakdown(id).get();
    }

    @PutMapping("/update")
    private Breakdown updateBreakdown(Breakdown Breakdown) {
        return breakdownService.updateBreakdown(Breakdown);
    }

    @DeleteMapping("/delete/{id}")
    private void deleteBreakdown(@PathVariable Long id) {
        breakdownService.deleteBreakdown(id);
    }

    @DeleteMapping("/delete/all")
    private void deleteAllBreakdown() {
        breakdownService.deleteAllBreakdown();
    }

}
