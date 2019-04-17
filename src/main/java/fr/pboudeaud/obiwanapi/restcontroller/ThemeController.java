package fr.pboudeaud.obiwanapi.restcontroller;

import fr.pboudeaud.obiwanapi.entity.Theme;
import fr.pboudeaud.obiwanapi.service.ThemeService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping(path = "/api/themes")
public class ThemeController {
    @Resource(name = "ThemeService")
    private ThemeService themeService;

    @RequestMapping
    public List<Theme> getAll() {
        return this.themeService.getAll();
    }

    @RequestMapping(path = "{id}")
    public Theme getById(@PathVariable("id") Theme theme) {
        return theme;
    }

    @RequestMapping(method = RequestMethod.PUT)
    public Theme create(@RequestBody Theme theme) {
        this.themeService.create(theme);
        return theme;
    }
}
