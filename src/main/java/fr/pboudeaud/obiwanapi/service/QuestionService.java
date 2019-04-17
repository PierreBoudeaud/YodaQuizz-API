package fr.pboudeaud.obiwanapi.service;

import fr.pboudeaud.obiwanapi.entity.Question;
import fr.pboudeaud.obiwanapi.entity.Reponse;
import fr.pboudeaud.obiwanapi.repository.QuestionRepository;
import fr.pboudeaud.obiwanapi.repository.ReponseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service(value="QuestionService")
public class QuestionService {
    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private ReponseRepository reponseRepository;

    public List<Question> getAll() {
        return this.questionRepository.findAll();
    }

    public Question create(Question question) {
        List<Reponse> reponses = question.getReponses();
        question.setReponses(new ArrayList<>());
        question.getQuizz().getQuestions().add(question);
        this.questionRepository.save(question);
        this.questionRepository.flush();
        question.setReponses(reponses);
        this.reponseRepository.saveAll(question.getReponses());
        this.reponseRepository.flush();
        return question;
    }

    public void edit(Question question) {
        Optional<Question> questionDb = this.questionRepository.findById(question.getId());
        if(questionDb.isPresent()) {
            Question oldQuestion = questionDb.get();
            List<Reponse> oldReponses = oldQuestion.getReponses();

            for(Reponse oldReponse: oldReponses) {
                Reponse reponse = question.getReponseById(oldReponse.getId());
                oldReponse.setCorrect(reponse.isCorrect());
                oldReponse.setNom(reponse.getNom());
            }

            oldQuestion.setIntitule(question.getIntitule());
            this.questionRepository.save(oldQuestion);
            this.questionRepository.flush();
        }
    }

    public void updateImage(Question question) {
        Optional<Question> questionDb = this.questionRepository.findById(question.getId());
        if(questionDb.isPresent()) {
            Question oldQuestion = questionDb.get();
            oldQuestion.setImage(question.getImage());
            this.questionRepository.save(question);
            this.questionRepository.flush();
        }
    }

    public void delete(int id) {
        this.questionRepository.deleteById(id);
    }
}
