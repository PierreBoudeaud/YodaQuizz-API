package fr.pboudeaud.obiwanapi.service;

import fr.pboudeaud.obiwanapi.entity.Utilisateur;
import fr.pboudeaud.obiwanapi.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service(value = "UtilisateurService")
public class UtilisateurService {
    @Autowired
    private UtilisateurRepository utilisateurRepository;

    public List<Utilisateur> getAll() {
        return this.utilisateurRepository.findAllByOrderByNomAsc();
    }

    public Utilisateur create(Utilisateur utilisateur) {
        this.utilisateurRepository.save(utilisateur);
        this.utilisateurRepository.flush();
        return utilisateur;
    }

    public void edit(Utilisateur utilisateur) {
        Optional<Utilisateur> resultDb = this.utilisateurRepository.findById(utilisateur.getId());
        if (resultDb.isPresent()) {
            Utilisateur oldUtilisateur = resultDb.get();
            oldUtilisateur.setNom(utilisateur.getNom());
            this.utilisateurRepository.save(oldUtilisateur);
            this.utilisateurRepository.flush();
        }
    }
}
