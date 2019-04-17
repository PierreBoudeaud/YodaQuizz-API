package fr.pboudeaud.obiwanapi.service;

import fr.pboudeaud.obiwanapi.entity.Type;
import fr.pboudeaud.obiwanapi.repository.TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service(value = "TypeService")
public class TypeService {
    @Autowired
    private TypeRepository typeRepository;

    public List<Type> getAll() {
        return this.typeRepository.findAllByOrderByNomAsc();
    }

    public Type create(Type type) {
        this.typeRepository.save(type);
        this.typeRepository.flush();
        return type;
    }

    public void edit(Type type) {
        Optional<Type> resultDb = this.typeRepository.findById(type.getId());
        if (resultDb.isPresent()) {
            Type oldTheme = resultDb.get();
            oldTheme.setDescription(type.getDescription());
            oldTheme.setNom(type.getNom());
            oldTheme.setIcon(type.getIcon());
            this.typeRepository.save(oldTheme);
            this.typeRepository.flush();
        }
    }
}
