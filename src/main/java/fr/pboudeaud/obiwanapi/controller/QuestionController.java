package fr.pboudeaud.obiwanapi.controller;

import fr.pboudeaud.obiwanapi.entity.Question;
import fr.pboudeaud.obiwanapi.entity.Quizz;
import fr.pboudeaud.obiwanapi.payload.UploadFileResponse;
import fr.pboudeaud.obiwanapi.service.FileStorageService;
import fr.pboudeaud.obiwanapi.service.QuestionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.servlet.view.RedirectView;

import javax.annotation.Resource;

@Controller(value = "QuestionViewController")
@RequestMapping(path = "/quizz/{quizzId}/questions")
public class QuestionController {
    @Resource(name = "FileStorageService")
    private FileStorageService fileStorageService;

    @Resource(name = "QuestionService")
    private QuestionService questionService;

    @GetMapping
    public String getAll(@PathVariable("quizzId")Quizz quizz, Model model) {
        model.addAttribute("quizz", quizz);
        return "question/question-all";
    }

    @GetMapping(path = "/{id}")
    public String show(@PathVariable("id")Question question, Model model) {
        model.addAttribute("question", question);
        return "question/question-show";
    }

    @GetMapping("/createedit/{id}")
    public String createForm(@PathVariable("quizzId")Quizz quizz, @PathVariable("id")Question question, Model model) {
        if (question == null) {
            question = new Question(quizz);
        }
        model.addAttribute("laQuestion", question);
        return "question/question-createedit";
    }

    @PostMapping("/createedit")
    public RedirectView createEdit(@ModelAttribute Question question, Model model) {
        if(question.getId() > 0) {
            this.questionService.edit(question);
        } else {
            this.questionService.create(question);
        }
        return new RedirectView("/quizz/" + question.getQuizz().getId() + "/questions/" + question.getId());
    }

    @PostMapping("/{id}/changeimage")
    public RedirectView changeImage(@RequestParam("image") MultipartFile file, @PathVariable("id") Question question, Model model) {
        String fileName = fileStorageService.storeFile(file, question);

        question.setImage(fileName);
        this.questionService.updateImage(question);

        return new RedirectView("/quizz/" + question.getQuizz().getId() + "/questions/" + question.getId());
    }

    @GetMapping("/{id}/delete")
    public String deleteForm(@PathVariable("id") Question question, Model model) {
        model.addAttribute("question", question);
        return "question/question-delete";
    }

    @PostMapping("/{id}/delete")
    public RedirectView delete(@PathVariable("id") Question question) {
        this.questionService.delete(question.getId());
        return new RedirectView("/quizz/" + question.getQuizz().getId() + "/questions");
    }
}
