package PAI.persistence.springdata.degreeType;

import PAI.persistence.datamodel.degreeType.DegreeTypeDataModel;
import PAI.persistence.datamodel.degreeType.DegreeTypeIDDataModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDegreeTypeRepoSpringData extends JpaRepository<DegreeTypeDataModel, DegreeTypeIDDataModel> { }
