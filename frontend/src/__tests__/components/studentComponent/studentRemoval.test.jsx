import React from 'react';
import { render, screen, waitFor, fireEvent } from '@testing-library/react';
import { MemoryRouter } from 'react-router-dom';
import StudentCourseEditionForm from '../../../components/studentComponent/StudentRemoval';
import * as studentService from '../../../services/studentService';
import * as enrolmentService from '../../../services/removeStudentFromCourseEditionService';

jest.mock('../../../services/studentService');
jest.mock('../../../services/removeStudentFromCourseEditionService');

jest.mock('../../../services/studentService', () => ({
    getAllStudents: jest.fn().mockResolvedValue([
        { studentID: '1000001', name: 'AAA' },
        { studentID: '1000002', name: 'BBB' },
    ]),
}));

jest.mock('../../../services/removeStudentFromCourseEditionService', () => ({
    getCourseEditionsByStudent: jest.fn((studentID) => {
        if (studentID === '1000001') {
            return Promise.resolve([
                {
                    courseEditionGeneratedUUID: '17f8a682-bb3c-465b-be7e-052733e26a36',
                    courseName: 'Engenharia Informática',
                    courseAcronym: 'EI',
                },
            ]);
        }
        return Promise.resolve([]);
    }),
    removeStudentFromCourseEditionService: jest.fn().mockResolvedValue(),
}));


describe('StudentCourseEditionForm', () => {
    const mockStudents = [
        { studentID: '1000001', name: 'AAA' },
        { studentID: '1000002', name: 'BBB' }
    ];

    const mockCourseEditions = [
        {
            courseEditionGeneratedUUID: '17f8a682-bb3c-465b-be7e-052733e26a36',
            courseName: 'Alchemy',
            courseAcronym: 'ALCH'
        },
        {
            courseEditionGeneratedUUID: 'b8ca393f-84c9-4a04-9e1e-3c859fca32d4',
            courseName: 'Arithmancy',
            courseAcronym: 'ARIT'
        }
    ];

    beforeEach(() => {
        studentService.getAllStudents.mockResolvedValue(mockStudents);
        enrolmentService.getCourseEditionsByStudent.mockResolvedValue(mockCourseEditions);
        enrolmentService.removeStudentFromCourseEditionService.mockResolvedValue();
    });

    afterEach(() => jest.clearAllMocks());

    it('renders all form elements', async () => {
        render(
            <MemoryRouter>
                <StudentCourseEditionForm />
            </MemoryRouter>
        );

        await screen.findByText('Remove Student');

        expect(screen.getByLabelText(/Select Student/i)).toBeInTheDocument();
        expect(screen.getByLabelText(/Course Edition/i)).toBeInTheDocument();
        expect(screen.getByRole('button', { name: /CLEAR/i })).toBeInTheDocument();
        expect(screen.getByRole('button', { name: /REMOVE/i })).toBeInTheDocument();
    });


    it('populates student select dropdown', async () => {
        render(
            <MemoryRouter>
                <StudentCourseEditionForm />
            </MemoryRouter>
        );

        const studentSelect = await screen.findByLabelText(/Select Student/i);

        expect(screen.getByText(/AAA \(1000001\)/)).toBeInTheDocument();
        expect(screen.getByText(/BBB \(1000002\)/)).toBeInTheDocument();

        fireEvent.change(studentSelect, { target: { value: '1000001' } });
        expect(studentSelect).toHaveValue('1000001');
    });

    it('fetches and displays course editions when a student is selected', async () => {
        render(
            <MemoryRouter>
                <StudentCourseEditionForm />
            </MemoryRouter>
        );

        const studentSelect = await screen.findByLabelText(/Select Student/i);
        fireEvent.change(studentSelect, { target: { value: '1000001' } });

        await screen.findByText(/Alchemy \(ALCH\)/);
        expect(screen.getByText(/Arithmancy \(ARIT\)/)).toBeInTheDocument();
    });

    it('shows error if trying to remove without selecting course edition', async () => {
        render(
            <MemoryRouter>
                <StudentCourseEditionForm />
            </MemoryRouter>
        );

        const studentSelect = await screen.findByLabelText(/Select Student/i);
        fireEvent.change(studentSelect, { target: { value: '1000001' } });

        fireEvent.click(screen.getByRole('button',{ name:/REMOVE/i}));

        expect(await screen.findByText(/Please select a Course Edition/i)).toBeInTheDocument();
    });

    it('clears form on CLEAR button click', async () => {
        render(
            <MemoryRouter>
                <StudentCourseEditionForm />
            </MemoryRouter>
        );

        const studentSelect = await screen.findByLabelText(/Select Student/i);
        fireEvent.change(studentSelect, { target: { value: '1000001' } });

        const courseEditionSelect = await screen.findByLabelText(/Course Edition/i);
        fireEvent.change(courseEditionSelect, {
            target: { value: '17f8a682-bb3c-465b-be7e-052733e26a36' }
        });

        fireEvent.click(screen.getByText(/CLEAR/i));

        expect(studentSelect).toHaveValue('');
        expect(courseEditionSelect).toHaveValue('');
    });

});
