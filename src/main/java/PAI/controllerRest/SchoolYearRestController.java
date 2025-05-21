package PAI.controllerRest;

import PAI.dto.SchoolYear.ISchoolYearMapperDTO;
import PAI.service.schoolYear.ISchoolYearService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/schoolyear")
public class SchoolYearRestController {

    private final ISchoolYearMapperDTO iSchoolYearMapperDTO;
    private final ISchoolYearService iSchoolYearService;

    public SchoolYearRestController (ISchoolYearMapperDTO iSYMapperDTO, ISchoolYearService iSYService) {
        iSchoolYearMapperDTO = iSYMapperDTO;
        iSchoolYearService = iSYService;
    }
}
