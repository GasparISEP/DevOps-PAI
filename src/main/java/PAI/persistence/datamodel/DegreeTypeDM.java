package PAI.persistence.datamodel;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "degree_types")
public class DegreeTypeDM {

    @Id
    @Column(name = "DegreeTypeID", nullable = false, length = 36)
    private String id;

    @Column(name = "DegreeTypeName", nullable = false, length = 100)
    private String name;

    @Column(name = "MaxECTS", nullable = false)
    private int maxEcts;


        protected DegreeTypeDM() {

        }

        public DegreeTypeDM(String id, String name, int maxEcts) {
            this.id = id;
            this.name = name;
            this.maxEcts = maxEcts;
        }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getMaxEcts() {
        return maxEcts;
    }
}
