package PAI.factory;

import PAI.domain.SchoolYear;

import java.util.ArrayList;

public class SchoolYearListFactory  implements SchoolYearListFactoryInterface {

    public ArrayList<SchoolYear> newArrayList() {return new ArrayList<>();}
}
