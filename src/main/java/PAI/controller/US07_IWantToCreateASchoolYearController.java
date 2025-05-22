package PAI.controller;

import PAI.VOs.Date;
import PAI.VOs.Description;
import PAI.domain.schoolYear.SchoolYear;
import PAI.service.schoolYear.ISchoolYearService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class US07_IWantToCreateASchoolYearController {

    private final ISchoolYearService schoolYearService;

    // Constructor
    @Autowired
    public US07_IWantToCreateASchoolYearController (ISchoolYearService schoolYearService) {
        validateServiceArguments(schoolYearService);
        this.schoolYearService = schoolYearService;
    }

    private void validateServiceArguments(ISchoolYearService schoolYearService) {
        if (schoolYearService == null) {
            throw new IllegalArgumentException("Services cannot be null.");
        }
    }

    // Creates a new School Year
    public SchoolYear addSchoolYear (String descriptionInfo, String startDateInfo, String endDateInfo) throws Exception {
        try {
            Description description = new Description(descriptionInfo);
            Date startDate = new Date(startDateInfo);
            Date endDate = new Date(endDateInfo);

            return schoolYearService.addSchoolYear(description, startDate, endDate);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid input: " + e.getMessage(), e);
        } catch (Exception e) {
            throw new Exception("Failed to add school year: " + e.getMessage(), e);
        }
    }
}