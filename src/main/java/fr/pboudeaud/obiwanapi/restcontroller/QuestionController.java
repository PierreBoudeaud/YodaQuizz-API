package fr.pboudeaud.obiwanapi.restcontroller;

import fr.pboudeaud.obiwanapi.entity.Question;
import fr.pboudeaud.obiwanapi.service.QuestionService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping(path = "/api/questions")
public class QuestionController {
    @Resource(name = "QuestionService")
    private QuestionService questionService;

    @RequestMapping
    public List<Question> getAll() {
        return this.questionService.getAll();
    }

    @RequestMapping(path = "/{id}")
    public Question getById(@PathVariable("id") Question question) {
        return question;
    }

    @RequestMapping(method = RequestMethod.PUT)
    public Question create(@RequestBody Question question) {
        this.questionService.create(question);
        return question;
    }
}
