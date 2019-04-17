package fr.pboudeaud.obiwanapi.restcontroller;

import fr.pboudeaud.obiwanapi.entity.Type;
import fr.pboudeaud.obiwanapi.service.TypeService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping(path = "/api/types")
public class TypeController {
    @Resource(name = "TypeService")
    private TypeService typeService;

    @RequestMapping
    public List<Type> getAll() {
        return this.typeService.getAll();
    }

    @RequestMapping(path = "/{id}")
    public Type getById(@PathVariable("id") Type type) {
        return type;
    }

    @RequestMapping(method = RequestMethod.PUT)
    public Type create(@RequestBody Type type) {
        this.typeService.create(type);
        return type;
    }
}
