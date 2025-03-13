package PAI.factory;


import PAI.domain.SchoolYear;


import java.util.ArrayList;
import java.util.List;


public interface SchoolYearListFactory {

    ArrayList<SchoolYear> newArrayList();

    List<SchoolYear> copySchoolYearArrayList(List<SchoolYear> list);
}
