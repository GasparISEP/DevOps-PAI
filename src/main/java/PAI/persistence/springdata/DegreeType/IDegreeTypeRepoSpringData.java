package PAI.persistence.springdata.DegreeType;

import PAI.persistence.datamodel.DegreeType.DegreeTypeDataModel;
import PAI.persistence.datamodel.DegreeType.DegreeTypeIDDataModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDegreeTypeRepoSpringData extends JpaRepository<DegreeTypeDataModel, DegreeTypeIDDataModel> { }
