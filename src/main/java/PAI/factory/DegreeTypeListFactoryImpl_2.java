package PAI.factory;

import PAI.domain.DegreeTypeDDD.DegreeType_2;

import java.util.ArrayList;
import java.util.List;

public class DegreeTypeListFactoryImpl_2 implements IDegreeTypeListFactory_2 {
    public List<DegreeType_2> createDegreeType_2List(){
        return new ArrayList<>();
    }
}