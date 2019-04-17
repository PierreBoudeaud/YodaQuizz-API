package fr.pboudeaud.obiwanapi.controller;

import fr.pboudeaud.obiwanapi.entity.Utilisateur;
import fr.pboudeaud.obiwanapi.service.UtilisateurService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.annotation.Resource;
import java.util.List;

@Controller(value = "UtilisateurViewController")
@RequestMapping("/utilisateurs")
public class UtilisateurController {
    @Resource(name = "UtilisateurService")
    private UtilisateurService utilisateurService;

    @GetMapping
    public String getAll(Model model) {
        List<Utilisateur> utilisateurs = this.utilisateurService.getAll();
        model.addAttribute("allUtilisateurs", utilisateurs);
        return "utilisateur/utilisateur-all";
    }

    @GetMapping(path = "/createedit/{id}")
    public String utilisateurForm(@PathVariable("id") Utilisateur utilisateur, Model model) {
        if (utilisateur == null) {
            utilisateur = new Utilisateur();
        }
        model.addAttribute("utilisateur", utilisateur);
        return "utilisateur/utilisateur-createedit";
    }

    @PostMapping(path = "/createedit")
    public RedirectView utilisateurCreateEdit(@ModelAttribute Utilisateur utilisateur) {
        if (utilisateur.getId() > 0) {
            this.utilisateurService.edit(utilisateur);
        } else {
            this.utilisateurService.create(utilisateur);
        }
        return new RedirectView("/utilisateurs");
    }
}
