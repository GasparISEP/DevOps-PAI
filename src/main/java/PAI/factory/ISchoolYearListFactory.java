package PAI.factory;


import PAI.domain.SchoolYear;


import java.util.List;


public interface ISchoolYearListFactory {

    List<SchoolYear> newArrayList();

    List<SchoolYear> copySchoolYearArrayList(List<SchoolYear> list);
}
