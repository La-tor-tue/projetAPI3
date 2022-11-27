package be.condorcet.projet3_2.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jdk.jfr.Enabled;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@ToString
@Entity
@Table(name = "APIPROJET", schema = "ORA9", catalog = "ORCL")
public class Projet {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "projet_generator")
    @SequenceGenerator(name = "projet_generator", sequenceName = "APIPROJET_SEQ1", allocationSize = 1)
    @Column(name = "IDPJ")
    private Integer idProjet;
    @NonNull
    @Column(name = "PJTITRE")
    private String pjTitre;
    @Column(name = "PJDATEDEBUT")
    private LocalDate pjDateDebut;
    @Column(name = "PJDATEFIN")
    private LocalDate pjDateFin;
    @Column(name = "PJCOUT")
    private BigDecimal pjCout;


    @JsonIgnore
    @ToString.Exclude
    @OneToMany(mappedBy = "idPj")
    private List<Travail> listTravail;

    @JsonIgnore
    @ToString.Exclude
    @OneToMany(mappedBy = "idPj")
    private List<Invest> listInvest;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Projet projet = (Projet) o;
        return idProjet.equals(projet.idProjet) && pjTitre.equals(projet.pjTitre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idProjet, pjTitre);
    }
}
