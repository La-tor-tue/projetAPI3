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

    @ToString.Exclude
    @OneToMany(mappedBy = "taEmp",fetch = FetchType.LAZY,cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Travail> listTravail;

    @ManyToOne
    @JoinColumn(name = "IDDIS")
    @NonNull
    private Discipline empDis;
}
