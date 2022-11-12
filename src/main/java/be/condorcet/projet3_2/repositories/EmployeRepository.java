package be.condorcet.projet3_2.repositories;

import be.condorcet.projet3_2.entities.Discipline;
import be.condorcet.projet3_2.entities.Employe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeRepository extends JpaRepository<Employe, Integer> {
    List<Employe> findEmployesByEmpNomLike(String nom);

    List<Employe> findEmployesByEmpDisLike(Discipline discipline);
}
