package PAI.factory;

import PAI.domain.*;

public class TeacherFactoryImpl implements ITeacherFactory {

    private ITeacherCareerProgressionFactory _teacherCareerProgressionFactory;
    private ITeacherCareerProgressionListFactory _teacherCareerProgressionListFactory;

    public TeacherFactoryImpl(TeacherCareerProgressionFactoryImpl tcpFact, TeacherCareerProgressionListFactoryImpl tcplFact){
        _teacherCareerProgressionFactory = tcpFact;
        _teacherCareerProgressionListFactory = tcplFact;
    }

    public Teacher createTeacher(String acronym, String name, String email, String nif, String phoneNumber,
                                 String academicBackground, String street, String postalCode, String location,
                                 String country, IAddressFactory addressFactory, String date, TeacherCategory category, int workingPercentage,
                                 Department department) throws IllegalArgumentException {

        return new Teacher(acronym, name, email, nif, phoneNumber, academicBackground, street, postalCode,
                location, country, addressFactory, date, category, workingPercentage, department, _teacherCareerProgressionFactory, _teacherCareerProgressionListFactory);
    }
}
