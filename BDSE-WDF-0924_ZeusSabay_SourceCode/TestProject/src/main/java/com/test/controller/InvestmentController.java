package com.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.NumberFormat;
import java.util.Locale;

@Controller
@RequestMapping("/investment")
public class InvestmentController {

    @PostMapping("/quote")
    public String getInvestmentQuote(@RequestParam("investmentType") String investmentType,
                                     @RequestParam("initialAmount") double initialAmount,
                                     @RequestParam("monthlyAmount") double monthlyAmount,
                                     Model model) {

        if (initialAmount < 0 || monthlyAmount < 50) {
            model.addAttribute("error", "Invalid input: Please enter valid amounts.");
            return "index";
        }

        double minReturnRate = 0.0;
        double maxReturnRate = 0.0;
        double groupFeeRate = 0.0;
        double taxRateLow = 0.1;
        double taxRateHigh = 0.2;

        switch (investmentType) {
            case "basicSavings":
                minReturnRate = 0.012;
                maxReturnRate = 0.024;
                groupFeeRate = 0.0025;
                break;
            case "savingsPlus":
                minReturnRate = 0.03;
                maxReturnRate = 0.055;
                groupFeeRate = 0.003;
                break;
            case "managedStockInvestments":
                minReturnRate = 0.04;
                maxReturnRate = 0.23;
                groupFeeRate = 0.013;
                break;
        }

        // Calculate results for 1, 5, and 10 years
        double[][] results = new double[3][4]; // [years][0=return, 1=profit, 2=fees, 3=tax]

        int[] years = {1, 5, 10};
        for (int i = 0; i < years.length; i++) {
            int year = years[i];

            double minReturn = (initialAmount * Math.pow(1 + minReturnRate, year)) + (monthlyAmount * 12 * year * minReturnRate);
            double maxReturn = (initialAmount * Math.pow(1 + maxReturnRate, year)) + (monthlyAmount * 12 * year * maxReturnRate);
            double totalFees = maxReturn * groupFeeRate;
            double totalProfit = maxReturn - totalFees;
            double totalTax = 0.0;

            if (totalProfit > 40000) {
                totalTax = ((totalProfit - 40000) * taxRateHigh) + ((28000) * taxRateLow);
            } else if (totalProfit > 12000) {
                totalTax = (totalProfit - 12000) * taxRateLow;
            }

            results[i][0] = maxReturn;
            results[i][1] = totalProfit;
            results[i][2] = totalFees;
            results[i][3] = totalTax;
        }

        // Format numbers as GBP currency
        NumberFormat gbpFormat = NumberFormat.getCurrencyInstance(Locale.UK);

        model.addAttribute("minReturn1", gbpFormat.format(results[0][0]));
        model.addAttribute("minReturn5", gbpFormat.format(results[1][0]));
        model.addAttribute("minReturn10", gbpFormat.format(results[2][0]));

        model.addAttribute("profit1", gbpFormat.format(results[0][1]));
        model.addAttribute("profit5", gbpFormat.format(results[1][1]));
        model.addAttribute("profit10", gbpFormat.format(results[2][1]));

        model.addAttribute("fees1", gbpFormat.format(results[0][2]));
        model.addAttribute("fees5", gbpFormat.format(results[1][2]));
        model.addAttribute("fees10", gbpFormat.format(results[2][2]));

        model.addAttribute("tax1", gbpFormat.format(results[0][3]));
        model.addAttribute("tax5", gbpFormat.format(results[1][3]));
        model.addAttribute("tax10", gbpFormat.format(results[2][3]));

        return "index"; // Return results to JSP page
    }

    @GetMapping("/quote")
    public String resetQuote(Model model) {
        model.addAttribute("error", "");
        return "index"; // Reset form
    }
}