package be.condorcet.projet3_2.entities.associations;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class TravailID implements Serializable {

    @Column(name = "IDEMP")
    private int idEmp;
    @Column(name = "IDPJ")
    private int idPj;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TravailID travailID = (TravailID) o;
        return idEmp == travailID.idEmp && idPj == travailID.idPj;
    }

    @Override
    public int hashCode() {
        return (idPj + idEmp);
    }
}
