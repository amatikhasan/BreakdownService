package com.autoaid.breakdown.controller;

import com.autoaid.breakdown.model.AidProvider;
import com.autoaid.breakdown.model.AidRequest;
import com.autoaid.breakdown.model.Breakdown;
import com.autoaid.breakdown.model.UserInfo;
import com.autoaid.breakdown.service.BreakdownService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/breakdown")
public class BreakdownController {

    private BreakdownService breakdownService;

    @Autowired
    public BreakdownController(BreakdownService BreakdownService) {
        this.breakdownService = BreakdownService;
    }

    @GetMapping
    public String dashboard(Model model, HttpServletRequest request) throws IOException {
        // Retrieve session from session ID
        String userId = request.getParameter("userId");
        UserInfo user = breakdownService.getUserInfo(Long.valueOf(userId));

        HttpSession session = request.getSession();
        //UserInfo sessionUser = (UserInfo) session.getAttribute("user");
        session.setAttribute("user", user);
        if (user != null) {

            List<Breakdown> testData = breakdownService.getAllTestBreakdown();
            model.addAttribute("breakdowns", testData);

            for (Breakdown testCase : testData) {
                model.addAttribute("model", testCase.getModel());
                model.addAttribute("timestamp", testCase.getTimestamp().toString());
                model.addAttribute("location", (testCase.getLocation().getLatitude() + "(lat) " + testCase.getLocation().getLongitude() + "(lon)").toString());
                model.addAttribute("Hardware Fault Code", testCase.getErrorCode());
            }

            return "redirect:/breakdown/details";
        } else {
            return "redirect:http://localhost:8084/user/login";
        }
    }

    @GetMapping("/all")
    private String getAllTestBreakdown(Model model) throws IOException {
        List<Breakdown> testData = breakdownService.getAllTestBreakdown();
        model.addAttribute("breakdowns", testData);

        for (Breakdown testCase : testData) {
            model.addAttribute("model", testCase.getModel());
            model.addAttribute("timestamp", testCase.getTimestamp().toString());
            model.addAttribute("location", (testCase.getLocation().getLatitude() + "(lat) " + testCase.getLocation().getLongitude() + "(lon)").toString());
            model.addAttribute("Hardware Fault Code", testCase.getErrorCode());
        }

        return "breakdown-list";
    }

    @GetMapping("/details")
    private String getAllBreakdown(Model model) {
        List<Breakdown> breakdowns = breakdownService.getAllBreakdown();
        List<AidProvider> aidProviders = breakdownService.getAllAidProvider();
        model.addAttribute("breakdownList", breakdowns);
        model.addAttribute("aidProviderList", aidProviders);
        return "breakdown-details";
    }

    @GetMapping("/create")
    private String showCreateBreakdownPage() {
        return "breakdown";
    }

    @PostMapping("/create")
    private String createBreakdown() throws IOException {
        breakdownService.createRandomBreakdown();
        return "redirect:/breakdown/details";
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

    @PostMapping("/aid/request")
    private String requestAid(Breakdown _breakdown) {
        Breakdown breakdown = breakdownService.getBreakdown(_breakdown.getId()).get();
        AidRequest aidRequest = new AidRequest();
        aidRequest.setCarModel(breakdown.getModel());
        aidRequest.setErrorCode(breakdown.getErrorCode());
        aidRequest.setLocation(breakdown.getLocation());
        aidRequest.setTimeStamp(breakdown.getTimestamp());
        aidRequest.setCarDriver("Atik Hasan");
        aidRequest.setAidProvider("Wanna fix your car GmbH");
        aidRequest.setStatus("Pending");
        aidRequest.setDescription("");
        aidRequest.setPaymentStatus("Pending");
        AidRequest _aidRequest = breakdownService.requestAid(aidRequest);
        if (_aidRequest != null) return "redirect:http://localhost:8082/aid/all";
        else
            return "redirect:/breakdown/details";
    }

}
