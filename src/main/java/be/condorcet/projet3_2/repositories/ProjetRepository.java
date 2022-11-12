package be.condorcet.projet3_2.repositories;

import be.condorcet.projet3_2.entities.Projet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjetRepository extends JpaRepository<Projet,Integer> {

    List<Projet> findProjetsByPjTitreLike(String titre);
}
