package PAI.assembler.studyPlan;

import PAI.dto.studyPlan.StudyPlanResponseDTO;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;

public interface IStudyPlanHATEOASAssembler extends RepresentationModelAssembler<StudyPlanResponseDTO, EntityModel<StudyPlanResponseDTO>>{

}
