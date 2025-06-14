import React from 'react';
import { render, screen, fireEvent } from '@testing-library/react';
import ProgrammeSuccessModal from '../../../components/programmeComponent/ProgrammeSuccessModal';


describe('ProgrammeSuccessModal', () => {
    test('displays and clicks "Display Details" button correctly', () => {
        const mockSuccess = {
            name: 'Test Programme',
            acronym: 'TP',
            quantSemesters: 6,
            degreeTypeID: 1,
            departmentID: 2,
            teacherID: 3,
        };

        const mockDegreeTypes = [{id: 1, name: 'Degree Type 1'}];
        const mockDepartments = [{id: 2, name: 'Department 1'}];
        const mockTeachers = [{id: 3, name: 'Teacher 1'}];

        const onDisplay = jest.fn();
        const onClose = jest.fn();

        render(
            <ProgrammeSuccessModal
                success={mockSuccess}
                degreeTypes={mockDegreeTypes}
                departments={mockDepartments}
                teachers={mockTeachers}
                onDisplay={onDisplay}
                onClose={onClose}
                loadingDetails={false}
                detailsDisplayed={false}/>
        );

        const displayButton = screen.getByRole('button', {name: /display details/i});
        expect(displayButton).toBeInTheDocument();

        fireEvent.click(displayButton);
        expect(onDisplay).toHaveBeenCalled();
    });

    test('displays "Unknown" for missing degree type, department, and teacher', () => {
        const mockSuccess = {
            name: 'Test Programme',
            acronym: 'TP',
            quantSemesters: 6,
            maxECTS: 120,
            degreeTypeID: 999,
            departmentID: 888,
            teacherID: 777,
        };

        render(
            <ProgrammeSuccessModal
                success={mockSuccess}
                degreeTypes={[]}
                departments={[]}
                teachers={[]}
                onDisplay={jest.fn()}
                onClose={jest.fn()}
                loadingDetails={false}
                detailsDisplayed={true}
            />
        );

        expect(screen.getByText((_, el) => el.textContent === 'Degree Type: Unknown')).toBeInTheDocument();
        expect(screen.getByText((_, el) => el.textContent === 'Department: Unknown')).toBeInTheDocument();
        expect(screen.getByText((_, el) => el.textContent === "Programme's Director: Unknown")).toBeInTheDocument();
    });

    test('shows "Loading..." when loadingDetails is true', () => {
        const mockSuccess = {
            name: 'Test Programme',
            acronym: 'TP',
            quantSemesters: 6,
            degreeTypeID: 1,
            departmentID: 2,
            teacherID: 3,
        };

        const mockDegreeTypes = [{ id: 1, name: 'Degree Type 1' }];
        const mockDepartments = [{ id: 2, name: 'Department 1' }];
        const mockTeachers = [{ id: 3, name: 'Teacher 1' }];

        render(
            <ProgrammeSuccessModal
                success={mockSuccess}
                degreeTypes={mockDegreeTypes}
                departments={mockDepartments}
                teachers={mockTeachers}
                onDisplay={jest.fn()}
                onClose={jest.fn()}
                loadingDetails={true}
                detailsDisplayed={false}/>
        );

        expect(screen.getByRole('button', { name: /loading/i })).toBeInTheDocument();
    });

});

