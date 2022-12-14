package be.condorcet.projet3_2.entities;

import be.condorcet.projet3_2.entities.associations.InvestID;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity(name = "Invest")
@Table(name = "APIINVESTISSEMENT", schema = "ORA9", catalog = "ORCL")
public class Invest {

    @EmbeddedId
    @AttributeOverrides({
            @AttributeOverride(name = "idDis",column = @Column(name = "IDDIS")),
            @AttributeOverride(name="idPj",column = @Column(name = "IDPJ"))
    })
    private InvestID id;

    @ToString.Exclude
    @ManyToOne
    @MapsId("idDis")
    private Discipline idDis;

    @ToString.Exclude

    @ManyToOne
    @MapsId("idPj")
    private Projet idPj;

    @Column(name = "INVQUANTITEJH")
    private int invQuantJH;


}
