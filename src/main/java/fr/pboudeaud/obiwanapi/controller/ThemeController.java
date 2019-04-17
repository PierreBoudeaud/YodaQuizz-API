package fr.pboudeaud.obiwanapi.controller;

import fr.pboudeaud.obiwanapi.entity.Theme;
import fr.pboudeaud.obiwanapi.service.ThemeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Controller(value = "ThemeViewController")
@RequestMapping("/themes")
public class ThemeController {
    @Resource(name = "ThemeService")
    private ThemeService themeService;

    @GetMapping
    public String getAll(Model model) {
        List<Theme> themes = this.themeService.getAll();
        model.addAttribute("allThemes", themes);
        return "theme/theme-all";
    }

    @GetMapping(path = "/createedit/{id}")
    public String themeForm(@PathVariable("id") Theme theme, Model model) {
        if (theme == null) {
            theme = new Theme();
        }
        model.addAttribute("theme", theme);
        return "theme/theme-createedit";
    }

    @PostMapping(path = "/createedit")
    public String themeCreateEdit(@ModelAttribute Theme theme, Model model) {
        if (theme.getId() > 0) {
            this.themeService.edit(theme);
        } else {
            this.themeService.create(theme);
        }
        return this.getAll(model);
    }
}
