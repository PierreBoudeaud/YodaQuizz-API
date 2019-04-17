package fr.pboudeaud.obiwanapi.controller;

import fr.pboudeaud.obiwanapi.entity.Type;
import fr.pboudeaud.obiwanapi.service.TypeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Controller(value = "TypeViewController")
@RequestMapping("/types")
public class TypeController {
    @Resource(name = "TypeService")
    private TypeService typeService;

    @GetMapping
    public String getAll(Model model) {
        List<Type> types = this.typeService.getAll();
        model.addAttribute("allTypes", types);
        return "type/type-all";
    }

    @GetMapping(path = "/createedit/{id}")
    public String typeForm(@PathVariable("id") Type type, Model model) {
        if (type == null) {
            type = new Type();
        }
        model.addAttribute("type", type);
        return "type/type-createedit";
    }

    @PostMapping(path = "/createedit")
    public String typeCreateEdit(@ModelAttribute Type type, Model model) {
        if (type.getId() > 0) {
            this.typeService.edit(type);
        } else {
            this.typeService.create(type);
        }
        return this.getAll(model);
    }
}
