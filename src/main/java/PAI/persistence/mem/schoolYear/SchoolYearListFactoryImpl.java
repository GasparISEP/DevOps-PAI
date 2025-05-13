package PAI.persistence.mem.schoolYear;

import PAI.domain.schoolYear.SchoolYear;

import java.util.ArrayList;
import java.util.List;

public class SchoolYearListFactoryImpl implements ISchoolYearListFactory {

    public List<SchoolYear> newArrayList() {return new ArrayList<>();}

    @Override
    public List<SchoolYear> copySchoolYearArrayList(List<SchoolYear> list) {
        return new ArrayList<>(list);
    }


}
