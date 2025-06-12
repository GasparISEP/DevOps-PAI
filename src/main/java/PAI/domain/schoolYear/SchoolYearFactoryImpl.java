package PAI.domain.schoolYear;

import PAI.VOs.Date;
import PAI.VOs.Description;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class SchoolYearFactoryImpl implements ISchoolYearFactory {

    public SchoolYear createSchoolYear(Description description, Date startDate, Date endDate) {
        return new SchoolYear(description, startDate, endDate);

    }

    public SchoolYear recreateSchoolYear(UUID uuid, Description description, Date startDate, Date endDate) {
        return new SchoolYear(uuid, description, startDate, endDate);
    }
}
