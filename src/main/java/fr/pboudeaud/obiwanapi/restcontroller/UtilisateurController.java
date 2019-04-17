package fr.pboudeaud.obiwanapi.restcontroller;

import fr.pboudeaud.obiwanapi.entity.Utilisateur;
import fr.pboudeaud.obiwanapi.service.UtilisateurService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping(path = "/api/utilisateurs")
public class UtilisateurController {

    @Resource(name = "UtilisateurService")
    private UtilisateurService utilisateurService;

    @RequestMapping
    public List<Utilisateur> getAll() {
        return this.utilisateurService.getAll();
    }

    @RequestMapping(method = RequestMethod.PUT)
    public Utilisateur create(@RequestBody Utilisateur utilisateur) {
        this.utilisateurService.create(utilisateur);
        return utilisateur;
    }
}
