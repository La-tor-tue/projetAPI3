package be.condorcet.projet3_2.services;

import be.condorcet.projet3_2.entities.Discipline;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DisciplineServiceImplTest {

    @Autowired
    private InterfDisciplineService disciplineService;

    Discipline dis;

    @BeforeEach
    void setUp() {
        try {
            dis = new Discipline(null, "NomTest", "DescTest", new ArrayList<>(), new ArrayList<>());
            disciplineService.create(dis);
            System.out.println("Création de la discipline: " + dis);
        } catch (Exception e) {
            System.out.println("Erreur lors de la création de la discipline " + dis + " erreur: " + e.getMessage());
        }
    }

    @AfterEach
    void tearDown() {
        try {
            disciplineService.delete(dis);
            System.out.println("Destruction de la discipline: " + dis);
        } catch (Exception e) {
            System.out.println("TEAR DOWN Erreur lors de la destruction de la discipline " + dis + " erreur: " + e.getMessage());
        }
    }

    @Test
    void doublon() {
        Discipline disDouble = new Discipline(null, "NomTest", "DescTest", new ArrayList<>(), new ArrayList<>());
        Assertions.assertThrows(Exception.class, () -> {
            disciplineService.create(disDouble);
        }, "Creation de doublon");

    }

    @Test
    void read() {
        try {
            int idDis = dis.getIdDis();
            Discipline disTest = disciplineService.read(idDis);
            assertEquals("NomTest", disTest.getDisNom(), "Nom diff" + " NomTest" + "-" + disTest.getDisNom());
            assertEquals("DescTest", disTest.getDisDesc(), "Desc diff" + " DescTest" + "-" + disTest.getDisDesc());
        } catch (Exception e) {
            fail("Recherche raté " + e);
        }
    }

    @Test
    void create() {
        assertNotEquals(0, dis.getIdDis(), "ID discipline inchangé");
        assertEquals("NomTest", dis.getDisNom(), "Nom de la discipline non enregistré");
        assertEquals("DescTest", dis.getDisDesc(), "Description de la discipline non enregistré");
    }

    @Test
    void update() {
        try {
            dis.setDisDesc("DescTestUpdate");
            dis.setDisNom("NomTestUpdate");
            dis = disciplineService.update(dis);

            assertEquals("NomTestUpdate", dis.getDisNom(), "Nom de la discipline différents: " + "NomTestUpdate / " + dis.getDisNom());
            assertEquals("DescTestUpdate", dis.getDisDesc(), "Description de la discipline différents: " + "DescTest Update / " + dis.getDisDesc());
        } catch (Exception e) {
            fail("Erreur lors de l'update: " + e);
        }
    }

    @Test
    void delete() {
        try {
            disciplineService.delete(dis);
            Assertions.assertThrows(Exception.class, () -> {
                disciplineService.read(dis.getIdDis());
            },"Record Non Detruit");
        } catch (Exception e) {
            fail("Erreur lors du delete"+e);
        }
    }

    @Test
    void all(){
        try {
            List<Discipline> disciplineList = disciplineService.all();
            assertNotEquals(0,disciplineList.size(),"La liste est vide");
        } catch (Exception e) {
            fail("Erreur lors de la lecture de toutes les disciplines "+e);
        }
    }
}