package PAI.controller;

import PAI.VOs.Date;
import PAI.VOs.Description;
import PAI.service.schoolYear.ISchoolYearService;
import org.springframework.stereotype.Component;

@Component
public class US07_IWantToCreateASchoolYearController {

    private final ISchoolYearService schoolYearService;

    // Constructor
    public US07_IWantToCreateASchoolYearController (ISchoolYearService schoolYearService) {
        if (schoolYearService == null) {
            throw new IllegalArgumentException("School Year Service must not be null.");
        }
        this.schoolYearService = schoolYearService;
    }

    // Creates a new School Year
    public boolean addSchoolYear (String descriptionInfo, String startDateInfo, String endDateInfo) throws Exception {
        Description description = new Description(descriptionInfo);
        Date startDate = new Date(startDateInfo);
        Date endDate = new Date(endDateInfo);

        return schoolYearService.addSchoolYear(description, startDate, endDate);
    }
}