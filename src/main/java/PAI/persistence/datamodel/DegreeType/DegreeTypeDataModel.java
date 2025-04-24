package PAI.persistence.datamodel.DegreeType;

import jakarta.persistence.*;

@Entity
@Table(name = "degree_types")
public class DegreeTypeDataModel {

    @EmbeddedId
    private DegreeTypeIDDataModel id;

    @Column(name = "DegreeTypeName", nullable = false, length = 100)
    private String name;

    @Column(name = "MaxECTS", nullable = false)
    private int maxEcts;

    protected DegreeTypeDataModel() {}

    public DegreeTypeDataModel(DegreeTypeIDDataModel id, String name, int maxEcts) {
        this.id = id;
        this.name = name;
        this.maxEcts = maxEcts;
    }

    public DegreeTypeIDDataModel getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getMaxEcts() {
        return maxEcts;
    }
}
