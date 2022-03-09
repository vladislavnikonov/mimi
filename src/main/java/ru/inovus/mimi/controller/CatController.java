package ru.inovus.mimi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.inovus.mimi.model.Cat;
import ru.inovus.mimi.model.Pair;
import ru.inovus.mimi.service.CatService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping()
public class CatController {
    private CatService catService;
    HttpSession session;
    Integer count;

    public CatController(CatService catService) {
        this.catService = catService;
    }

    @GetMapping()
    public String home() {
        return "redirect:/index";
    }

    @GetMapping("/index")
    public String index(Model model, HttpServletRequest request) {
        session = request.getSession();
        count = (Integer) session.getAttribute("count");
        Set<Pair> pairs = (Set<Pair>) session.getAttribute("list");

        if (pairs == null) {
            pairs = new HashSet<>();
            catService.getRandomSet(pairs);
            session.setAttribute("list", pairs);
        }

        if (count == null) {
            count = 0;
            session.setAttribute("count", count);
        } else if (count > 44)
            return "redirect:/top";

        Pair pair = pairs.stream().toList().get(count);
        List<Cat> catList = catService.getPair(pair);
        model.addAttribute("cats", catList);
        model.addAttribute("count", count);
        return "index";
    }

    @GetMapping("/top")
    public String top(Model model) {
        model.addAttribute("cats", catService.getTop());
        return "top";
    }

    @PostMapping("/vote/{id}")
    public String vote(@PathVariable Long id) {
        session.setAttribute("count", ++count);
        catService.updateVote(id);
        return "redirect:/index";
    }
}
