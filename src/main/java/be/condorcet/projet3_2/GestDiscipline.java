package be.condorcet.projet3_2;

import be.condorcet.projet3_2.entities.Discipline;
import be.condorcet.projet3_2.services.DisciplineServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;
import java.util.Map;

@Controller
@RequestMapping("/disciplines")
public class GestDiscipline {
    @Autowired
    private DisciplineServiceImpl disciplineService;

    @RequestMapping("/tous")
    public String affTous(Map<String, Object> model) {
        try {
            Collection<Discipline> lDis = disciplineService.all();
            model.put("mesDis", lDis);
        } catch (Exception e) {
            model.put("error", e.getMessage());
            return "error";
        }
        return "affdisall";
    }

    @RequestMapping("/create")
    public String create(@RequestParam String nom, @RequestParam String desc, Map<String, Object> model) {
        Discipline discipline = new Discipline(nom);
        try {
            disciplineService.create(discipline);
            discipline = disciplineService.read(discipline.getIdDis());
            discipline.setDisDesc(desc);
            disciplineService.update(discipline);
            model.put("newDis", discipline);

        } catch (Exception e) {
            model.put("error", e.getMessage());
            return "error";
        }
        return "newDis";
    }

    @RequestMapping("/update")
    public String update(@RequestParam int idDis, String desc, Map<String, Object> model) {
        try {
            Discipline discipline= disciplineService.read(idDis);
            discipline.setDisDesc(desc);
            disciplineService.update(discipline);
            model.put("myDis", discipline);

        } catch (Exception e) {
            model.put("error", e.getMessage());
            return "error";
        }
        return "affdis";
    }

    @RequestMapping("/read")
    public String read(@RequestParam int idDis, Map<String, Object> model) {
        try {
            Discipline discipline = disciplineService.read(idDis);
            disciplineService.update(discipline);
            model.put("myDis", discipline);
        } catch (Exception e) {
            model.put("error", e.getMessage());
            return "error";
        }
        return "affdis";
    }

    @RequestMapping("/delete")
    public String delete(@RequestParam int idDis, Map<String, Object> model) {
        try {
            Discipline discipline = disciplineService.read(idDis);
            disciplineService.delete(discipline);
            model.put("myDis", discipline);
        } catch (Exception e) {
            model.put("error", e.getMessage());
            return "error";
        }
        return "deldis";
    }
}
