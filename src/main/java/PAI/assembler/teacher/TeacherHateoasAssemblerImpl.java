package PAI.assembler.teacher;

import PAI.dto.teacher.TeacherDTO;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class TeacherHateoasAssemblerImpl implements RepresentationModelAssembler<TeacherDTO, EntityModel<TeacherDTO>>, ITeacherHateoasAssembler {
    @Override
    public EntityModel<TeacherDTO> toModel (TeacherDTO dto) {
        return null;
    }
}
