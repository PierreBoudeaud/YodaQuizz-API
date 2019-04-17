package fr.pboudeaud.obiwanapi.controller;

import fr.pboudeaud.obiwanapi.entity.Quizz;
import fr.pboudeaud.obiwanapi.service.QuizzService;
import fr.pboudeaud.obiwanapi.service.ThemeService;
import fr.pboudeaud.obiwanapi.service.TypeService;
import fr.pboudeaud.obiwanapi.service.UtilisateurService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

@Controller(value = "QuizzViewController")
@RequestMapping(path = "/quizz")
public class QuizzController {
    @Resource(name = "QuizzService")
    private QuizzService quizzService;

    @Resource(name = "ThemeService")
    private ThemeService themeService;

    @Resource(name = "TypeService")
    private TypeService typeService;

    @Resource(name = "UtilisateurService")
    private UtilisateurService utilisateurService;

    @GetMapping
    public String getAll(Model model) {
        List<Quizz> quizz = this.quizzService.getAll();
        model.addAttribute("allQuizz", quizz);
        return "quizz/quizz-all";
    }

    @GetMapping(path = "/{id}")
    public String show(@PathVariable("id") Quizz quizz, Model model) {
        model.addAttribute("quizz", quizz);
        return "quizz/quizz-show";
    }

    @GetMapping(path = "/createedit/{id}")
    public String quizzForm(@PathVariable("id") Quizz quizz, Model model) {
        if (quizz == null) {
            quizz = new Quizz();
        }
        model.addAttribute("quizz", quizz);

        model.addAttribute("allTypes", this.typeService.getAll());
        model.addAttribute("allThemes", this.themeService.getAll());
        model.addAttribute("allUtilisateurs", this.utilisateurService.getAll());
        return "quizz/quizz-createedit";
    }

    @PostMapping(path = "/createedit")
    public RedirectView quizzCreateEdit(@ModelAttribute Quizz quizz) {
        if (quizz.getId() > 0) {
            this.quizzService.edit(quizz);
        } else {
            this.quizzService.create(quizz);
        }
        return new RedirectView("/quizz/" + quizz.getId());
    }

    @GetMapping(path = "/{id}/delete")
    public String deleteForm(@PathVariable("id") Quizz quizz, Model model) {
        model.addAttribute("quizz", quizz);
        return "quizz/quizz-delete";
    }

    @PostMapping(path = "/{id}/delete")
    public RedirectView delete(@PathVariable("id") Quizz quizz) {
        this.quizzService.delete(quizz.getId());
        return new RedirectView("/quizz");
    }
}
