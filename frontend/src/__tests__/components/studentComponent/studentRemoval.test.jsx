import React from 'react';
import { render, screen, fireEvent } from '@testing-library/react';
import { MemoryRouter } from 'react-router-dom';
import StudentCourseEditionForm from '../../../components/studentComponent/StudentRemoval';
import * as studentService from '../../../services/studentService';
import * as enrolmentService from '../../../services/removeStudentFromCourseEditionService';

jest.mock('../../../services/studentService');
jest.mock('../../../services/removeStudentFromCourseEditionService');

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
    enrolmentService.getCourseEditionsByStudent.mockImplementation((id) => {
        return id === '1000001' ? Promise.resolve(mockCourseEditions) : Promise.resolve([]);
    });
    enrolmentService.removeStudentFromCourseEditionService.mockResolvedValue();
});

afterEach(() => jest.clearAllMocks());

describe('StudentCourseEditionForm', () => {
    it('renders all form elements', async () => {
        render(
            <MemoryRouter>
                <StudentCourseEditionForm />
            </MemoryRouter>
        );

        expect(await screen.findByText('Remove Student')).toBeInTheDocument();
        expect(screen.getByLabelText(/Enter Student Number/i)).toBeInTheDocument();
        expect(screen.getByLabelText(/Course Edition/i)).toBeInTheDocument();
        expect(screen.getByRole('button', { name: /CLEAR/i })).toBeInTheDocument();
        expect(screen.getByRole('button', { name: /REMOVE/i })).toBeInTheDocument();
    });

    it('fetches and displays course editions when a student ID is entered', async () => {
        render(
            <MemoryRouter>
                <StudentCourseEditionForm />
            </MemoryRouter>
        );

        const studentInput = screen.getByLabelText(/Enter Student Number/i);
        fireEvent.change(studentInput, { target: { value: '1000001' } });
        fireEvent.blur(studentInput);

        expect(await screen.findByText(/Alchemy \(ALCH\)/)).toBeInTheDocument();
        expect(screen.getByText(/Arithmancy \(ARIT\)/)).toBeInTheDocument();
    });

    it('shows error if trying to remove without selecting course edition', async () => {
        render(
            <MemoryRouter>
                <StudentCourseEditionForm />
            </MemoryRouter>
        );

        const studentInput = screen.getByLabelText(/Enter Student Number/i);
        fireEvent.change(studentInput, { target: { value: '1000001' } });
        fireEvent.blur(studentInput);

        fireEvent.click(screen.getByRole('button', { name: /REMOVE/i }));

        expect(await screen.findByText(/Please select a Course Edition/i)).toBeInTheDocument();
    });

    it('clears form on CLEAR button click', async () => {
        render(
            <MemoryRouter>
                <StudentCourseEditionForm />
            </MemoryRouter>
        );

        const studentInput = screen.getByLabelText(/Enter Student Number/i);
        fireEvent.change(studentInput, { target: { value: '1000001' } });
        fireEvent.blur(studentInput);

        const courseEditionSelect = await screen.findByLabelText(/Course Edition/i);
        fireEvent.change(courseEditionSelect, {
            target: { value: '17f8a682-bb3c-465b-be7e-052733e26a36' }
        });

        fireEvent.click(screen.getByText(/CLEAR/i));

        expect(studentInput).toHaveValue('');
        expect(courseEditionSelect).toHaveValue('');
    });

    it('removes student from course edition and shows success message', async () => {
        render(
            <MemoryRouter>
                <StudentCourseEditionForm />
            </MemoryRouter>
        );

        const studentInput = screen.getByLabelText(/Enter Student Number/i);
        fireEvent.change(studentInput, { target: { value: '1000001' } });
        fireEvent.blur(studentInput);

        const courseEditionSelect = await screen.findByLabelText(/Course Edition/i);
        fireEvent.change(courseEditionSelect, {
            target: { value: '17f8a682-bb3c-465b-be7e-052733e26a36' }
        });

        fireEvent.click(screen.getByRole('button', { name: /REMOVE/i }));

        expect(await screen.findByText("Student removed successfully from course edition.")).toBeInTheDocument();
    });

    it('shows message when student has no course editions', async () => {
        render(
            <MemoryRouter>
                <StudentCourseEditionForm />
            </MemoryRouter>
        );

        const studentInput = screen.getByLabelText(/Enter Student Number/i);
        fireEvent.change(studentInput, { target: { value: '1000002' } }); // no editions
        fireEvent.blur(studentInput);

        expect(await screen.findByText(/No course editions found for this student/i)).toBeInTheDocument();
    });

    it('shows error message if removal fails', async () => {
        enrolmentService.removeStudentFromCourseEditionService.mockRejectedValueOnce(new Error('Removal failed'));

        render(
            <MemoryRouter>
                <StudentCourseEditionForm />
            </MemoryRouter>
        );

        const studentInput = screen.getByLabelText(/Enter Student Number/i);
        fireEvent.change(studentInput, { target: { value: '1000001' } });
        fireEvent.blur(studentInput);

        const courseEditionSelect = await screen.findByLabelText(/Course Edition/i);
        fireEvent.change(courseEditionSelect, {
            target: { value: '17f8a682-bb3c-465b-be7e-052733e26a36' }
        });

        fireEvent.click(screen.getByRole('button', { name: /REMOVE/i }));

        expect(await screen.findByText(/Failed to remove student from course edition/i)).toBeInTheDocument();
    });
});
