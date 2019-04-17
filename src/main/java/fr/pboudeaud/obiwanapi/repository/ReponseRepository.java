package fr.pboudeaud.obiwanapi.repository;

import fr.pboudeaud.obiwanapi.entity.Reponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReponseRepository extends JpaRepository<Reponse, Integer> {
}
