package com.catspell.catspell_web.Controller;

import com.catspell.catspell_web.Service.SpellService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CatSpellController {

    @Autowired
    private SpellService spellService;

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @PostMapping("/cast")
    public String castSpell(@RequestParam("phrase") String phrase, Model model) {
        String translated = spellService.translatePhrase(phrase);
        String catFact = spellService.getCatFact();
        model.addAttribute("spell", translated);
        model.addAttribute("catFact", catFact);
        return "index";
    }
}
