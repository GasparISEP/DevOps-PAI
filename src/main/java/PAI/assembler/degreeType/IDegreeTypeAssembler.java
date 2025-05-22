package PAI.assembler.degreeType;

import PAI.domain.degreeType.DegreeType;
import PAI.dto.degreeType.DegreeTypeDTO;
import PAI.dto.degreeType.RegisterDegreeTypeCommand;
import PAI.dto.degreeType.RegisterDegreeTypeRequest;

public interface IDegreeTypeAssembler {

    RegisterDegreeTypeCommand toRegisterDegreeTypeCommand(RegisterDegreeTypeRequest request);

    DegreeTypeDTO toDTO (DegreeType degreeType);
}
