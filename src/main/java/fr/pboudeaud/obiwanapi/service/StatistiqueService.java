package fr.pboudeaud.obiwanapi.service;

import fr.pboudeaud.obiwanapi.entity.Statistique;
import fr.pboudeaud.obiwanapi.repository.StatistiqueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value="StatistiqueService")
public class StatistiqueService {
    @Autowired
    private StatistiqueRepository statistiqueRepository;

    public List<Statistique> getAll() {
        return this.statistiqueRepository.findAll();
    }

    public Statistique create(Statistique statistique) {
        this.statistiqueRepository.save(statistique);
        this.statistiqueRepository.flush();
        return statistique;
    }
}
