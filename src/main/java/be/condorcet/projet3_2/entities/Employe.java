package be.condorcet.projet3_2.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@ToString
@Entity
@Table(name = "APIEMPLOYE", schema = "ORA9", catalog = "ORCL")
public class Employe {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "APIEMPLOYE_GEN")
    @SequenceGenerator(name = "APIEMPLOYE_GEN", sequenceName = "APIEMPLOYE_SEQ1", allocationSize = 1)
    @Column(name = "IDEMP")
    private Integer idEmp;
    @NonNull
    @Column(name = "EMPMATRICULE")
    private String empMatricule;
    @Column(name = "EMPMAIL")
    private String empMail;
    @NonNull
    @Column(name = "EMPNOM")
    private String empNom;
    @NonNull
    @Column(name = "EMPPRENOM")
    private String empPrenom;
    @Column(name = "EMPTEL")
    private String empTel;

    @JsonIgnore
    @ToString.Exclude
    @OneToMany(mappedBy = "idEmp")
    private List<Travail> listTravail;


    @ManyToOne
    @JoinColumn(name = "IDDIS")
    @NonNull
    private Discipline empDis;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employe employe = (Employe) o;
        return idEmp.equals(employe.idEmp) && empMatricule.equals(employe.empMatricule);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idEmp, empMatricule);
    }
}
