package be.condorcet.projet3_2.entities.associations;

import java.io.Serializable;
import java.util.Objects;

public class TravailID implements Serializable {

    private int idEmp;

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
