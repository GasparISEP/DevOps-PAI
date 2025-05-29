package PAI.service.degreeType;

import PAI.VOs.DegreeTypeID;
import PAI.domain.degreeType.DegreeType;
import PAI.dto.degreeType.RegisterDegreeTypeCommand;

import java.util.Optional;

public interface IDegreeTypeRegistrationService {
    DegreeType createAndSaveDegreeType(RegisterDegreeTypeCommand requestCommand) throws Exception;

    Iterable<DegreeType> getAllDegreeTypes();

    Optional<DegreeType> getDegreeTypeById(DegreeTypeID id);
}
