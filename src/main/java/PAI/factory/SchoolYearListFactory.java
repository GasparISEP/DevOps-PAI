package PAI.factory;


import PAI.domain.SchoolYear;


import java.util.ArrayList;
import java.util.List;


public interface SchoolYearListFactory {

    List<SchoolYear> newArrayList();

    List<SchoolYear> copySchoolYearArrayList(List<SchoolYear> list);
}
