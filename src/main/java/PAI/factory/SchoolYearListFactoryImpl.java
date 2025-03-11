package PAI.factory;

import PAI.domain.SchoolYear;

import java.util.ArrayList;

public class SchoolYearListFactoryImpl implements SchoolYearListFactory {

    public ArrayList<SchoolYear> newArrayList() {return new ArrayList<>();}
}
