package fr.pboudeaud.obiwanapi.restcontroller;

import fr.pboudeaud.obiwanapi.entity.Statistique;
import fr.pboudeaud.obiwanapi.service.StatistiqueService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping(path = "/api/statistiques")
public class StatistiqueController {
    @Resource(name = "StatistiqueService")
    private StatistiqueService statistiqueService;

    @RequestMapping
    public List<Statistique> getAll() {
        return this.statistiqueService.getAll();
    }

    @RequestMapping(path = "/{id}")
    public Statistique getById(@PathVariable("id") Statistique statistique) {
        return statistique;
    }

    @RequestMapping(method = RequestMethod.PUT)
    public Statistique create(@RequestBody Statistique statistique) {
        this.statistiqueService.create(statistique);
        return statistique;
    }
}
