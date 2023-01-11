package be.condorcet.projet3_2.webservices;


import be.condorcet.projet3_2.entities.Discipline;
import be.condorcet.projet3_2.entities.Employe;
import be.condorcet.projet3_2.services.DisciplineServiceImpl;
import be.condorcet.projet3_2.services.EmployeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*",allowedHeaders = "*",exposedHeaders = "*")
@RestController
@RequestMapping("/disciplines")
public class RestDiscipline {

    @Autowired
    private DisciplineServiceImpl disciplineService;

    @Autowired
    private EmployeServiceImpl employeService;

    //----Lire une Discipline pour un id------
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Discipline> getDiscipline(@PathVariable(value = "id")int id)throws Exception{
        System.out.println("Recherche de la discipline: "+id);
        Discipline discipline = disciplineService.read(id);
        return new ResponseEntity<>(discipline, HttpStatus.OK);
    }

    //-----Lire une discipline sur un Nom----
    @RequestMapping(value = "/nom={nom}", method = RequestMethod.GET)
    public ResponseEntity<List<Discipline>> getDiscipline(@PathVariable(value = "nom") String nom) throws Exception{
        System.out.println("Recherche des disciplines ayant le nom: "+nom);
        List<Discipline> lDis = disciplineService.read(nom);
        return new ResponseEntity<>(lDis,HttpStatus.OK);
    }

    //-----Créer une Discipline ---------------
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<Discipline> createDiscipline(@RequestBody Discipline discipline) throws Exception{
        System.out.println("Creation de la Discipline: "+discipline.getDisNom());
        disciplineService.create(discipline);
        return new ResponseEntity<>(discipline,HttpStatus.OK);
    }

    //------Update une Discipline ------------
    @RequestMapping(value = "/{id}",method = RequestMethod.PUT)
    public ResponseEntity<Discipline> updateDiscipline(@PathVariable(value = "id")int id,@RequestBody Discipline updiscipline) throws Exception{
        System.out.println("Update de la discipline: "+id);
        updiscipline.setIdDis(id);
        Discipline discipline = disciplineService.update(updiscipline);
        return new ResponseEntity<>(discipline,HttpStatus.OK);
    }

    //-----Delete une Discipline----------------
    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteDiscipline(@PathVariable(value = "id")int id) throws Exception{
        System.out.println("Delete de la discipline: "+id);
        Discipline discipline = disciplineService.read(id);
        disciplineService.delete(discipline);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //-----Trouver tous les disciplines --------
    @RequestMapping(value = "/all",method = RequestMethod.GET)
    public ResponseEntity<List<Discipline>> getAll() throws Exception{
        System.out.println("Recherche de tous les disciplines");
        return new ResponseEntity<>(disciplineService.all(),HttpStatus.OK);
    }

    @RequestMapping(value = "/allByNoSpec",method = RequestMethod.GET)
    public ResponseEntity<List<Discipline>> getAllByNoSpec() throws Exception{
        System.out.println("Recherche de tous les disciplines sans spécialiste");
        List<Discipline> listDis = disciplineService.all();
        List<Discipline> listDisNoSpec=new ArrayList<>();
        for (int i =0; i<listDis.size();i++){
            if (employeService.allByDis(listDis.get(i).getIdDis()).size()==0){
                listDisNoSpec.add(listDis.get(i));
            }
        }
        return new ResponseEntity<>(listDisNoSpec,HttpStatus.OK);
    }

    //-----Trouver toutes les Disciplines Pageable  ---------

    @RequestMapping(value = "/allp",method = RequestMethod.GET)
    public ResponseEntity<Page<Discipline>> getAll(Pageable pageable) throws Exception{
        System.out.println("Recherche de tous les Discipliness");
        return new ResponseEntity<>(disciplineService.allp(pageable),HttpStatus.OK);
    }

    //------Gestion des erreures ------------
    @ExceptionHandler({Exception.class})
    public ResponseEntity<Void>  handleIOException(Exception e) {
        System.out.println("ERROR: "+e.getMessage());
        return ResponseEntity.notFound().header("ERROR",e.getMessage()).build();
    }
}
