package PAI.persistence.mem.schoolYear;


import PAI.domain.schoolYear.SchoolYear;


import java.util.List;


public interface ISchoolYearListFactory {

    List<SchoolYear> newArrayList();

    List<SchoolYear> copySchoolYearArrayList(List<SchoolYear> list);
}
