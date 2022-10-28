package be.condorcet.projet3_2.entities;

import lombok.*;

import javax.persistence.*;
import java.util.List;

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
    private Integer idEmp;
    @NonNull
    private String empMatricule;
    private String empMail;
    @NonNull
    private String empNom;
    @NonNull
    private String empPrenom;
    private String empTel;

    @ToString.Exclude
    @OneToMany(mappedBy = "taEmp")
    private List<Travail> listTravail;

    @ManyToOne
    @JoinColumn(name = "IDDIS")
    @NonNull
    private Discipline empDis;
}
