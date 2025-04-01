package PAI.domain;

import PAI.VOs.ProgrammeEditionEnrolmentID;
import PAI.VOs.StudentID;
import PAI.ddd.AggregateRoot;

import java.time.LocalDate;
import java.util.Objects;

public class ProgrammeEditionEnrolment implements AggregateRoot<ProgrammeEditionEnrolmentID> {
    private Student _student;
    private ProgrammeEdition _programmeEdition;
    private LocalDate _enrolmentDate;
    private ProgrammeEditionEnrolmentID _enrolmentId;

    //constructor
    public ProgrammeEditionEnrolment(Student student, ProgrammeEdition programmeEdition) {
        validateStudent(student);
        validateProgrammeEdition(programmeEdition);
        this._enrolmentDate = LocalDate.now();
    }

    private void validateStudent(Student student) {
        if (student == null) {
            throw new IllegalArgumentException("Student cannot be null");
        }
        this._student = student;
    }

    private void validateProgrammeEdition(ProgrammeEdition programmeEdition) {
        if (programmeEdition == null) {
            throw new IllegalArgumentException("ProgrammeEdition cannot be null");
        }
        this._programmeEdition = programmeEdition;
    }


    public boolean isEnrolmentAssociatedToDepartmentAndSchoolYear(Department department, SchoolYear schoolYear) {
        return _programmeEdition.isEditionAssociatedToDepartmentAndSchoolYear(department, schoolYear);
    }

    public StudentID getStudentID() {
        return _student.identity();
    }

    public boolean hasSameStudent(Student student) {
        return _student.equals(student);
    }

    public boolean hasSameProgrammeEdition(ProgrammeEdition programmeEdition) {
        return _programmeEdition.equals(programmeEdition);
    }

    public ProgrammeEdition findProgrammeEditionInEnrolment() {
        return _programmeEdition;
    }

    public Student findStudentInProgrammeEdition() {
        return _student;
    }

    @Override
    public int hashCode() {
        return Objects.hash(_student, _programmeEdition);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        ProgrammeEditionEnrolment other = (ProgrammeEditionEnrolment) obj;
        return Objects.equals(this._student, other._student) && Objects.equals(this._programmeEdition, other._programmeEdition);
    }

    @Override
    public ProgrammeEditionEnrolmentID identity() {
        return _enrolmentId;
    }

    @Override
    public boolean sameAs(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }

        ProgrammeEditionEnrolment other = (ProgrammeEditionEnrolment) object;
        return this.identity().equals(other.identity());
    }
}
