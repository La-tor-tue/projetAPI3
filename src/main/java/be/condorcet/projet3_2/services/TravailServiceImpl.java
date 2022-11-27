package be.condorcet.projet3_2.services;

import be.condorcet.projet3_2.entities.Travail;
import be.condorcet.projet3_2.entities.associations.TravailID;
import be.condorcet.projet3_2.repositories.TravailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


@Service
@Transactional(rollbackOn = Exception.class)
public class TravailServiceImpl implements InterfTravailService{

    @Autowired
    private TravailRepository travailRepository;

    @Override
    public Travail create(Travail travail) throws Exception {
        travailRepository.save(travail);
        return travail;
    }

    @Override
    public Travail read(TravailID id) throws Exception {
        Optional<Travail> oTrav=travailRepository.findById(id);
        return oTrav.get();
    }

    @Override
    public Travail update(Travail travail) throws Exception {
        read(travail.getId());
        travailRepository.save(travail);
        return travail;
    }

    @Override
    public void delete(Travail travail) throws Exception {
        travailRepository.delete(travail);
    }

    @Override
    public List<Travail> all() throws Exception {
        return travailRepository.findAll();
    }

    @Override
    public Page<Travail> allp(Pageable pageable) throws Exception {
        return travailRepository.findAll(pageable);
    }

    @Override
    public Travail read(Integer idPj, Integer idEmp) throws Exception {
        return travailRepository.findTravailById_IdEmpAndId_IdPj(idEmp,idPj);
    }
}
