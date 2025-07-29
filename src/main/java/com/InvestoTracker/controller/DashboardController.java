package com.InvestoTracker.controller;

import com.InvestoTracker.model.*;
import com.InvestoTracker.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Controller

public class DashboardController {

    @Autowired private UserRepository userRepo;
    @Autowired private InvestmentRepository investmentRepo;
    @Autowired private PasswordEncoder encoder;
    
    @GetMapping("/")
    public String homePage() {
        return "home";
    }
    
    @GetMapping("/register")
    public String registerPage() {
        return "register";
    }

    @PostMapping("/register")
    public String register(@RequestParam String username, @RequestParam String password) {
        if (userRepo.findByUsername(username) == null) {
            User user = new User(null, username, encoder.encode(password));
            userRepo.save(user);
        }
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model, Principal principal) {
        String username = principal.getName();
        List<Investment> investments = investmentRepo.findByUserUsername(username);

        double totalInvestment = investments.stream().mapToDouble(Investment::getAmountInvested).sum();
        double totalProfit = investments.stream().mapToDouble(inv -> inv.getProfit() != null ? inv.getProfit() : 0).sum();
        double totalTax = investments.stream().mapToDouble(inv -> inv.getTax() != null ? inv.getTax() : 0).sum();

        model.addAttribute("username", username);
        model.addAttribute("investments", investments);
        model.addAttribute("totalInvestment", totalInvestment);
        model.addAttribute("totalProfit", totalProfit);
        model.addAttribute("totalTax", totalTax);

        return "dashboard";
    }


    @PostMapping("/investments/add")
    public String addInvestment(@ModelAttribute Investment inv, Principal principal) {
        inv.setUser(userRepo.findByUsername(principal.getName()));
        investmentRepo.save(inv);
        return "redirect:/dashboard";
    }

    @PostMapping("/investments/update")
    public String updateInvestment(@RequestParam Long id, @RequestParam String withdrawalDate,
                                   @RequestParam Double resultantAmount) {
        Investment inv = investmentRepo.findById(id).orElseThrow();
        inv.setWithdrawalDate(withdrawalDate);
        inv.setResultantAmount(resultantAmount);

        double profit = resultantAmount - inv.getAmountInvested();
        inv.setProfit(profit);

        long days = ChronoUnit.DAYS.between(LocalDate.parse(inv.getInvestmentDate()), LocalDate.parse(withdrawalDate));
        double tax = profit > 0 ? (days >= 365 ? profit * 0.125 : profit * 0.2) : 0;
        inv.setTax(tax);

        investmentRepo.save(inv);
        return "redirect:/dashboard";
    }

    @GetMapping("/investments/delete/{id}")
    public String delete(@PathVariable Long id) {
        investmentRepo.deleteById(id);
        return "redirect:/dashboard";
    }
}
