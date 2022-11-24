package be.condorcet.projet3_2.webservices;

import be.condorcet.projet3_2.entities.Invest;
import be.condorcet.projet3_2.entities.Travail;
import be.condorcet.projet3_2.entities.associations.InvestID;
import be.condorcet.projet3_2.entities.associations.TravailID;
import be.condorcet.projet3_2.services.TravailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*",allowedHeaders = "*",exposedHeaders = "*")
@RestController
@RequestMapping("/travails")
public class RestTravail {

    @Autowired
    private TravailServiceImpl travailService;

    //----Lire un Travail pour un id------
    @RequestMapping(value = "/{idEmp}/{idPj}", method = RequestMethod.GET)
    public ResponseEntity<Travail> getTravail(@PathVariable(value = "idEmp") int idEmp,
                                              @PathVariable(value = "idPj") int idPj)throws Exception{
        System.out.println("Recherche d'un Travail: "+idEmp+ " et "+idPj);
        Travail travail =  travailService.read(new TravailID(idEmp,idPj));
        return new ResponseEntity<>(travail, HttpStatus.OK);
    }

    //-----Lire un Invest sur ............ ----
    /*  ????????CODE POUR RECHERCHER LES INVEST D UNE DISCIPLINE OU D UN PROJET ??????
    @RequestMapping(value = "/nom={nom}", method = RequestMethod.GET)
    public ResponseEntity<List<Discipline>> getDiscipline(@PathVariable(value = "nom") String nom) throws Exception{
        System.out.println("Recherche des disciplines ayant le nom: "+nom);
        List<Discipline> lDis = disciplineService.read(nom);
        return new ResponseEntity<>(lDis,HttpStatus.OK);
    }
    */

    //-----Créer un Travail ---------------
    // Pas sure de l algorithme de creation
    // /!\ DANS L OBJET TRAVAIL LES IDS DOIVENT FIGURER DEDANS AVEC UN TravailID /!\
    @RequestMapping(value = "/{idEmp}/{idPj}", method = RequestMethod.POST)
    public ResponseEntity<Travail> createTravail(@PathVariable(value = "idEmp") int idEmp,
                                                 @PathVariable(value = "idPj") int idPj,
                                                 @RequestBody Travail travail) throws Exception{
        System.out.println("Creation du Travail': "+idEmp+" et "+idPj);
        TravailID id = new TravailID(idEmp,idPj);
        travail.setId(id);
        travailService.create(travail);
        return new ResponseEntity<>(travail,HttpStatus.OK);
    }

    //------Update un Travail ------------
    @RequestMapping(value = "/{idEmp}/{idPj}",method = RequestMethod.PUT)
    public ResponseEntity<Travail> updateTravail(@PathVariable(value = "idEmp") int idEmp,
                                                @PathVariable(value = "idPj") int idPj,
                                                @RequestBody Travail uptravail) throws Exception{
        System.out.println("Update d'un Travail: "+idEmp+ " et "+idPj);
        uptravail.setId(new TravailID(idEmp,idPj));
        Travail travail = travailService.update(uptravail);
        return new ResponseEntity<>(travail,HttpStatus.OK);
    }

    //-----Delete un TRAVAIL----------------
    @RequestMapping(value = "/{idEmp}/{idPj}",method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteTravail(@PathVariable(value = "idEmp") int idEmp,
                                             @PathVariable(value = "idPj") int idPj) throws Exception{
        System.out.println("Delete d'un Travail: "+idEmp+ " et "+idPj);
        Travail travail =  travailService.read(new TravailID(idEmp,idPj));
        travailService.delete(travail);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //-----Trouver toutes les TRAVAUX --------
    @RequestMapping(value = "/all",method = RequestMethod.GET)
    public ResponseEntity<List<Travail>> getAll() throws Exception{
        System.out.println("Recherche de tous les Travaux");
        return new ResponseEntity<>(travailService.all(),HttpStatus.OK);
    }

    //-----Trouver toutes les Travail Pageable  ---------

    @RequestMapping(value = "/allp",method = RequestMethod.GET)
    public ResponseEntity<Page<Travail>> getAll(Pageable pageable) throws Exception{
        System.out.println("Recherche de tous les Travaux");
        return new ResponseEntity<>(travailService.allp(pageable),HttpStatus.OK);
    }

    //------Gestion des erreures ------------
    @ExceptionHandler({Exception.class})
    public ResponseEntity<Void>  handleIOException(Exception e) {
        System.out.println("ERROR: "+e.getMessage());
        return ResponseEntity.notFound().header("ERROR",e.getMessage()).build();
    }
}
