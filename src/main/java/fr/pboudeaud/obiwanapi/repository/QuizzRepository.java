package fr.pboudeaud.obiwanapi.repository;

import fr.pboudeaud.obiwanapi.entity.Quizz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizzRepository extends JpaRepository<Quizz, Integer> {
}
