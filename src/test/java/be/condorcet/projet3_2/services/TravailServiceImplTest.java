package be.condorcet.projet3_2.services;

import be.condorcet.projet3_2.entities.Discipline;
import be.condorcet.projet3_2.entities.Employe;
import be.condorcet.projet3_2.entities.Projet;
import be.condorcet.projet3_2.entities.Travail;
import be.condorcet.projet3_2.entities.associations.TravailID;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TravailServiceImplTest {

    @Autowired
    TravailServiceImpl travailService;
    @Autowired
    EmployeServiceImpl employeService;
    @Autowired
    ProjetServiceImpl projetService;

    @Autowired
    DisciplineServiceImpl disciplineService;

    Discipline dis;
    Employe emp;
    Projet pj;
    Travail tra;


    @BeforeEach
    void setUp() {
        try {


            pj = new Projet(null, "TitreTest", null, null, new BigDecimal(10), new ArrayList<>(), new ArrayList<>());
            projetService.create(pj);
            System.out.println("Création du projet: " + pj);

            dis = new Discipline(null, "DisTest", "DescTest", new ArrayList<>(), new ArrayList<>());
            disciplineService.create(dis);
            System.out.println("Creation de la discipline");

            emp = new Employe(null, "0000", "Test@Mail.com", "NomTest", "PrenomTest", "TelTest", new ArrayList<>(), dis);
            employeService.create(emp);
            System.out.println("Création de l'employé : " + emp);


            try {
                int idPj = pj.getIdProjet();
                int idEmp = emp.getIdEmp();

                TravailID id = new TravailID(idEmp, idPj);
                tra = new Travail(id, 10, null, emp, pj);
                travailService.create(tra);

                System.out.println("Creation d'invest: " + tra);

            } catch (Exception e) {
                System.out.println("Erreur lors de la création d'invest " + tra);
            }
        } catch (Exception e) {
            System.out.println("Erreur lors de la création de l'employé " + e.getMessage());
        }
    }

    @AfterEach
    void tearDown() {

        try {
            travailService.delete(tra);
            System.out.println("Delete Complete de: " + tra);
        } catch (Exception e) {
            fail("Error lors du delete" + e);
        }
        try {
            employeService.delete(emp);
            System.out.println("Delete complete de: " + emp);
            projetService.delete(pj);
            System.out.println("Delete complete de: " + pj);
            disciplineService.delete(dis);
            System.out.println("Delete complete de: " + dis);

        } catch (Exception e) {
            fail("ERROR Lors du delete: " + e);
        }
    }

    @Test
    void create() {
        assertNotEquals(0, emp.getIdEmp(), "Id Employé non inséré");
        assertNotEquals(0, pj.getIdProjet(), "Id Projet non inséré");
        assertEquals(10, tra.getTaPourcent(), "Pourcentage investissement non record");
    }

    @Test
    void read() {
        try {
            Travail tmp = travailService.read(tra.getId());

            assertEquals(tmp.getTaPourcent(), tra.getTaPourcent(), "Pourcentage différents");
        } catch (Exception e) {
            System.out.println("Error lors de la lecture: " + e);
        }
    }

    @Test
    void update() {
        try {
            tra.setTaPourcent(10);
            tra = travailService.update(tra);
            assertEquals(10, tra.getTaPourcent(), "Pourcentage inchanger");
        } catch (Exception e) {
            fail("Error update fail: " + e);
        }

    }

    @Test
    void delete() {
        try {
            travailService.delete(tra);
            Assertions.assertThrows(Exception.class, () -> {
                travailService.read(tra.getId());
            }, "Record non delete");
        } catch (Exception e) {
            fail("Erreur de delete: " + e);
        }
    }

    @Test
    void all() {
        try {
            List<Travail> listTravail = travailService.all();
            assertNotEquals(0, listTravail.size(), "Liste vide");
        } catch (Exception e) {
            fail("Error read ALL: " + e);
        }
    }
}