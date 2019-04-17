package fr.pboudeaud.obiwanapi.service;

import fr.pboudeaud.obiwanapi.entity.Theme;
import fr.pboudeaud.obiwanapi.repository.ThemeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service(value="ThemeService")
public class ThemeService {
    @Autowired
    private ThemeRepository themeRepository;

    public List<Theme> getAll() {
        return this.themeRepository.findAllByOrderByNomAsc();
    }

    public Theme create(Theme theme) {
        this.themeRepository.save(theme);
        this.themeRepository.flush();
        return theme;
    }

    public void edit(Theme theme) {
        Optional<Theme> resultDb = this.themeRepository.findById(theme.getId());
        if (resultDb.isPresent()) {
            Theme oldTheme = resultDb.get();
            oldTheme.setDescription(theme.getDescription());
            oldTheme.setNom(theme.getNom());
            oldTheme.setIcon(theme.getIcon());
            this.themeRepository.save(oldTheme);
            this.themeRepository.flush();
        }
    }
}
