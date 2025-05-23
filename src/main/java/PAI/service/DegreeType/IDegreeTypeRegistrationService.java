package PAI.service.DegreeType;

import PAI.domain.degreeType.DegreeType;
import PAI.dto.degreeType.RegisterDegreeTypeCommand;

public interface IDegreeTypeRegistrationService {
    DegreeType createAndSaveDegreeType(RegisterDegreeTypeCommand requestCommand) throws Exception;

    Iterable<DegreeType> getAllDegreeTypes();
}
