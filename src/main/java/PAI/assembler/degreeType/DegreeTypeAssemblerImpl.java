package PAI.assembler.degreeType;

import PAI.VOs.MaxEcts;
import PAI.VOs.Name;
import PAI.domain.degreeType.DegreeType;
import PAI.dto.degreeType.DegreeTypeDTO;
import PAI.dto.degreeType.RegisterDegreeTypeCommand;
import PAI.dto.degreeType.RegisterDegreeTypeRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class DegreeTypeAssemblerImpl implements IDegreeTypeAssembler{

    @Override
    public RegisterDegreeTypeCommand toRegisterDegreeTypeCommand (RegisterDegreeTypeRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("RegisterDegreeTypeRequest cannot be null.");
        }

        Name name = new Name(request.name());
        MaxEcts maxEcts = new MaxEcts(request.maxEcts());

        return new RegisterDegreeTypeCommand(name, maxEcts);
    }

    @Override
    public DegreeTypeDTO toDTO(DegreeType degreeType) {
        if (degreeType == null) {
            throw new IllegalArgumentException("DegreeType cannot be null.");
        }

        return new DegreeTypeDTO(
                degreeType.identity().getDTID(),
                degreeType.getName().getName(),
                degreeType.getMaxEcts().getMaxEcts()
        );
    }

    @Override
    public List<DegreeTypeDTO> toDTOs(List<DegreeType> degreeTypes) {
        if (degreeTypes == null) {
            throw new IllegalArgumentException("degreeTypes list cannot be null");
        }

        return degreeTypes.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

}
