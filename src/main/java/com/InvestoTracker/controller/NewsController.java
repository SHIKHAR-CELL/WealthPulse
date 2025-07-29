package com.InvestoTracker.controller;

import com.InvestoTracker.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NewsController {

    @Autowired
    private NewsService newsService;

    @GetMapping("/news")
    public String home(Model model) {
        model.addAttribute("newsList", newsService.fetchIndianMarketNews());
        return "news";
    }
}
