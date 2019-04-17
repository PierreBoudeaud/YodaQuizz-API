package fr.pboudeaud.obiwanapi.repository;

import fr.pboudeaud.obiwanapi.entity.Theme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ThemeRepository extends JpaRepository<Theme, Integer> {
    List<Theme> findAllByOrderByNomAsc();
}
