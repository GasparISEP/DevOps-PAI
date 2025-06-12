package PAI.assembler.schoolYear;
import PAI.controllerRest.SchoolYearRestController;
import PAI.dto.schoolYear.CurrentSchoolYearDTO;
import PAI.dto.schoolYear.SchoolYearDTO;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class SchoolYearHateoasAssembler implements ISchoolYearHateoasAssembler {

    @Override
    public EntityModel<CurrentSchoolYearDTO> toModel(CurrentSchoolYearDTO dto) {
        return EntityModel.of(dto,
                linkTo(methodOn(SchoolYearRestController.class)
                        .getSchoolYearByID(dto.id()))
                        .withSelfRel(),

                linkTo(methodOn(SchoolYearRestController.class)
                        .getAllSchoolYears())
                        .withRel("all")
        );
    }

    @Override
    public CollectionModel<EntityModel<CurrentSchoolYearDTO>> CollectionModel(Iterable<CurrentSchoolYearDTO> dtos) {
        return null;
    }
}
