package PAI.assembler.programmeEditionEnrolment;

import java.util.List;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;

import PAI.dto.programmeEditionEnrolment.ProgrammeEditionEnrolmentDetailDto;

public interface IProgrammeEditionEnrolmentHateoasAssembler {
    CollectionModel<EntityModel<ProgrammeEditionEnrolmentDetailDto>> toCollectionModel(List<ProgrammeEditionEnrolmentDetailDto> programmeEditionEnrolments);
}
