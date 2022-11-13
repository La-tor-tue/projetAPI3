package be.condorcet.projet3_2.entities;


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
@Table(name = "APIDISCIPLINES", schema = "ORA9", catalog = "ORCL")
public class Discipline {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "APIDISCIPLINES_GEN")
    @SequenceGenerator(name = "APIDISCIPLINES_GEN", sequenceName = "APIDISCIPLINES_SEQ1", allocationSize = 1)
    @Column(name = "IDDIS")
    private Integer idDis;

    @NonNull
    @Column(name = "DISNOM")
    private String disNom;

    @Column(name = "DISDESCRIPTION")
    private String disDesc;

    @OneToMany(mappedBy = "empDis")
    @ToString.Exclude
    private List<Employe> listEmp;

    @OneToMany(mappedBy = "idDis",fetch = FetchType.LAZY,cascade = CascadeType.ALL,orphanRemoval = true)
    @ToString.Exclude
    private List<Invest> listInvest;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Discipline that = (Discipline) o;
        return Objects.equals(idDis, that.idDis) && disNom.equals(that.disNom);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idDis, disNom);
    }
}
