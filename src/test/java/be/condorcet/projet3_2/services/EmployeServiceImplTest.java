package be.condorcet.projet3_2.services;

import be.condorcet.projet3_2.entities.Discipline;
import be.condorcet.projet3_2.entities.Employe;
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
class EmployeServiceImplTest {

    @Autowired
    private InterfEmployeService employeService;

    @Autowired
    private InterfDisciplineService disciplineService;

    Employe emp;
    Discipline dis;

    @BeforeEach
    void setUp() {
        try {
            dis = new Discipline(null, "NomTest", "DescTest", new ArrayList<>(), new ArrayList<>());
            disciplineService.create(dis);
            System.out.println("Création de la discipline: " + dis);
            try {
                emp = new Employe(null, "0000", "Test@Mail.com", "NomTest", "PrenomTest", "TelTest", new ArrayList<>(), dis);
                employeService.create(emp);
                System.out.println("Création de l'employé : " + dis);
            } catch (Exception e) {
                System.out.println("Erreur lors de la création de l'employé " + emp + " erreur: " + e.getMessage());
            }
        } catch (Exception e) {
            System.out.println("Erreur lors de la création de la discipline " + dis + " erreur: " + e.getMessage());
        }
    }

    @AfterEach
    void tearDown() {
        try {
            employeService.delete(emp);
            System.out.println("Destruction de l employe: " + emp);

        } catch (Exception e) {
            System.out.println("TEAR DOWN Erreur lors de la destruction de l employe " + emp + " erreur: " + e.getMessage());
        }
        try {
            disciplineService.delete(dis);
            System.out.println("Destruction de la discipline: " + dis);
        } catch (Exception e) {
            System.out.println("TEAR DOWN Erreur lors de la destruction de la discipline " + dis + " erreur: " + e.getMessage());
        }
    }

    @Test
    void create() {
        assertNotEquals(0, emp.getIdEmp(), "ID employe inchangé");
        assertEquals("0000", emp.getEmpMatricule(), "Matricule différents");
        assertEquals("Test@Mail.com", emp.getEmpMail(), "Mail différents");
        assertEquals("NomTest", emp.getEmpNom(), "Nom différents");
        assertEquals("PrenomTest", emp.getEmpPrenom(), "Prenom différents");
        assertEquals("TelTest", emp.getEmpTel(), "tel différents");
    }

    @Test
    void read() {
        try {
            int idEmp = emp.getIdEmp();
            Employe empTest = employeService.read(idEmp);
            assertEquals("0000", emp.getEmpMatricule(), "Matricule différents");
            assertEquals("Test@Mail.com", emp.getEmpMail(), "Mail différents");
            assertEquals("NomTest", emp.getEmpNom(), "Nom différents");
            assertEquals("PrenomTest", emp.getEmpPrenom(), "Prenom différents");
            assertEquals("TelTest", emp.getEmpTel(), "tel différents");
        } catch (Exception e) {
            fail("Recherche raté" + e);
        }
    }

    @Test
    void update() {
        try {
            emp.setEmpMatricule("Test");
            emp=employeService.update(emp);

            assertEquals("Test",emp.getEmpMatricule(),"Matricule de l'employé différents");
        }catch (Exception e){
            fail("Error lors de l'update: "+e);
        }
    }

    @Test
    void delete() {
        try{
            employeService.delete(emp);
            Assertions.assertThrows(Exception.class, () -> {
                employeService.read(emp.getIdEmp());
            },"Record Non Detruit");
        }catch (Exception e){
            fail("Erreur lors du delete du record: "+e);
        }
    }

    @Test
    void all() {
        try {
            List<Employe> employeList=employeService.all();
            assertNotEquals(0,employeList.size(),"Liste vide");
        }catch (Exception e){
            fail("Error lors du read all "+e);
        }
    }
}