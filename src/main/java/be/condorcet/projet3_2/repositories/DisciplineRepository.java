package be.condorcet.projet3_2.repositories;

import be.condorcet.projet3_2.entities.Discipline;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DisciplineRepository extends JpaRepository<Discipline,Integer> {
}
