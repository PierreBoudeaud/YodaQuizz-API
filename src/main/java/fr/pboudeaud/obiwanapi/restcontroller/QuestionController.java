package fr.pboudeaud.obiwanapi.restcontroller;

import fr.pboudeaud.obiwanapi.entity.Question;
import fr.pboudeaud.obiwanapi.entity.Quizz;
import fr.pboudeaud.obiwanapi.service.QuestionService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping(path = "/api/quizz/{quizzId}/questions")
public class QuestionController {
    @Resource(name = "QuestionService")
    private QuestionService questionService;

    @RequestMapping
    public List<Question> getAll(@PathVariable("quizzId")Quizz quizz) {
        return quizz.getQuestions();
    }

    @RequestMapping(path = "/{id}")
    public Question getById(@PathVariable("id") Question question) {
        return question;
    }

    @RequestMapping(method = RequestMethod.PUT)
    public Question create(@PathVariable("quizzId") Quizz quizz, @RequestBody Question question) {
        question.setQuizz(quizz);
        quizz.getQuestions().add(question);
        this.questionService.create(question);
        return question;
    }
}
