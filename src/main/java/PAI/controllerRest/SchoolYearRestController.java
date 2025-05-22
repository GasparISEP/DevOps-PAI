package PAI.controllerRest;

import PAI.assembler.schoolYear.ISchoolYearAssembler;
import PAI.service.schoolYear.ISchoolYearService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/schoolyear")
public class SchoolYearRestController {

    private final ISchoolYearAssembler iSchoolYearMapperDTO;
    private final ISchoolYearService iSchoolYearService;

    public SchoolYearRestController (ISchoolYearAssembler iSYMapperDTO, ISchoolYearService iSYService) {
        iSchoolYearMapperDTO = iSYMapperDTO;
        iSchoolYearService = iSYService;
    }
}
