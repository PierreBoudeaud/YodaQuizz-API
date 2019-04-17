package fr.pboudeaud.obiwanapi.service;

import fr.pboudeaud.obiwanapi.entity.Quizz;
import fr.pboudeaud.obiwanapi.repository.QuizzRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service(value = "QuizzService")
public class QuizzService {
    @Autowired
    private QuizzRepository quizzRepository;

    public List<Quizz> getAll() {
        return this.quizzRepository.findAll();
    }

    public Quizz create(@RequestBody Quizz quizz) {
        this.quizzRepository.save(quizz);
        this.quizzRepository.flush();
        return quizz;
    }

    public void edit(Quizz quizz) {
        Optional<Quizz> quizzDb = this.quizzRepository.findById(quizz.getId());
        if(quizzDb.isPresent()) {
            Quizz oldQuizz = quizzDb.get();
            oldQuizz.setNom(quizz.getNom());
            oldQuizz.setCreateur(quizz.getCreateur());
            oldQuizz.setDescription(quizz.getDescription());
            oldQuizz.setThemes(quizz.getThemes());
            oldQuizz.setType(quizz.getType());
            this.quizzRepository.save(oldQuizz);
            this.quizzRepository.flush();
        }
    }

    public void delete(int id) {
        this.quizzRepository.deleteById(id);
    }
}
