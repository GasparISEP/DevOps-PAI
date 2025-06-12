package PAI.assembler.schoolYear;
import PAI.controllerRest.SchoolYearRestController;
import PAI.dto.schoolYear.CurrentSchoolYearDTO;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

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

        List<EntityModel<CurrentSchoolYearDTO>> schoolYearList = new ArrayList<>();

        for (CurrentSchoolYearDTO dto : dtos) {
            schoolYearList.add(toEntityModel(dto));
        }

        CollectionModel<EntityModel<CurrentSchoolYearDTO>> collectionModel = CollectionModel.of(schoolYearList);

        collectionModel.add(linkTo(methodOn(SchoolYearRestController.class).getAllSchoolYears()).withSelfRel());

        return collectionModel;
    }

    private EntityModel<CurrentSchoolYearDTO> toEntityModel(CurrentSchoolYearDTO dto) {
        EntityModel<CurrentSchoolYearDTO> model = EntityModel.of(dto);

        model.add(linkTo(methodOn(SchoolYearRestController.class).getSchoolYearByID(dto.id()))
                .withSelfRel());

        return model;
    }
}
