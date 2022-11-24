package be.condorcet.projet3_2.services;

import be.condorcet.projet3_2.entities.Discipline;
import be.condorcet.projet3_2.repositories.DisciplineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(rollbackOn = Exception.class)
public class DisciplineServiceImpl implements InterfDisciplineService {

    @Autowired
    private DisciplineRepository disciplineRepository;

    @Override
    public List<Discipline> read(String nom) {
        return  disciplineRepository.findDisciplinesByDisNomLike(nom+"%");
    }

    @Override
    public Discipline create(Discipline discipline) throws Exception {
        disciplineRepository.save(discipline);
        return discipline;
    }

    @Override
    public Discipline read(Integer id) throws Exception {
        Optional<Discipline> ocl = disciplineRepository.findById(id);
        return ocl.get();
    }

    @Override
    public Discipline update(Discipline discipline) throws Exception {
        read(discipline.getIdDis());
        disciplineRepository.save(discipline);
        return discipline;
    }

    @Override
    public void delete(Discipline discipline) throws Exception {
        disciplineRepository.deleteById(discipline.getIdDis());
    }

    @Override
    public List<Discipline> all() throws Exception {
        return disciplineRepository.findAll();
    }

    @Override
    public Page<Discipline> allp(Pageable pageable) throws Exception {
        return disciplineRepository.findAll(pageable);
    }
}
