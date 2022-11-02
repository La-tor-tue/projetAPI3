package be.condorcet.projet3_2.services;

import be.condorcet.projet3_2.entities.Travail;
import be.condorcet.projet3_2.entities.associations.TravailID;
import be.condorcet.projet3_2.repositories.TravailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


//SE RENSEIGNER SUR LES IDS CONCATENER A PASSER EN COMMENTAIRE
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
        return null;
    }

    @Override
    public void delete(Travail travail) throws Exception {

    }

    @Override
    public List<Travail> all() throws Exception {
        return null;
    }

    @Override
    public Travail read(Integer idPj, Integer idEmp) throws Exception {
        return travailRepository.findTravailByIdEmpAndIdPj(idEmp,idPj);
    }
}
