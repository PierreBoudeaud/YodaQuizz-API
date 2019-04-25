package fr.pboudeaud.obiwanapi.restcontroller;

import fr.pboudeaud.obiwanapi.entity.Quizz;
import fr.pboudeaud.obiwanapi.service.QuizzService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/api/quizz")
public class QuizzController {
    @Resource(name = "QuizzService")
    private QuizzService quizzService;

    @RequestMapping
    public List<Quizz> getAll() {
        List<Quizz> allQuizz =  this.quizzService.getAll();
        for (Quizz quizz: allQuizz) {
            quizz.setQuestions(new ArrayList<>());
        }
        return allQuizz;
    }

    @RequestMapping(params = { "valid" })
    public List<Quizz> getAllActive(@RequestParam("valid") boolean valid) {
        List<Quizz> allQuizz =  this.quizzService.getAllValid(valid);
        for (Quizz quizz: allQuizz) {
            quizz.setQuestions(new ArrayList<>());
        }
        return allQuizz;
    }

    @RequestMapping(path = "/{id}")
    public Quizz getById(@PathVariable("id") Quizz quizz) {
        return quizz;
    }

    @RequestMapping(method = RequestMethod.PUT)
    public Quizz create(@RequestBody Quizz quizz) {
        this.quizzService.create(quizz);
        return quizz;
    }
}
