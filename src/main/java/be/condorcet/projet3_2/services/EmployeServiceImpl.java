package be.condorcet.projet3_2.services;

import be.condorcet.projet3_2.entities.Employe;
import be.condorcet.projet3_2.repositories.EmployeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(rollbackOn = Employe.class)
public class EmployeServiceImpl implements InterfEmployeService {

    @Autowired
    private EmployeRepository employeRepository;

    @Override
    public Employe create(Employe employe) throws Exception {
        employeRepository.save(employe);
        return employe;
    }

    @Override
    public Employe read(Integer id) throws Exception {
        Optional<Employe> oemp=employeRepository.findById(id);
        return oemp.get();
    }

    @Override
    public Employe update(Employe employe) throws Exception {
        employeRepository.save(employe);
        return employe;
    }

    @Override
    public void delete(Employe employe) throws Exception {
        employeRepository.delete(employe);

    }

    @Override
    public List<Employe> all() throws Exception {
        return employeRepository.findAll();
    }
}