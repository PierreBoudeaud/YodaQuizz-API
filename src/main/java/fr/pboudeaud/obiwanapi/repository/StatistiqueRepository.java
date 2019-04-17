package fr.pboudeaud.obiwanapi.repository;

import fr.pboudeaud.obiwanapi.entity.Statistique;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatistiqueRepository extends JpaRepository<Statistique, Integer> {
}
