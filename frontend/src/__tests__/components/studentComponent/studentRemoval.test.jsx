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

    it('handles error when getAllStudents fails', async () => {
        studentService.getAllStudents.mockRejectedValueOnce(new Error('fetch failed'));
        render(
            <MemoryRouter>
                <StudentCourseEditionForm />
            </MemoryRouter>
        );
        expect(await screen.findByText(/Remove Student/)).toBeInTheDocument();
    });

    it('handles non-array response from getCourseEditionsByStudent', async () => {
        enrolmentService.getCourseEditionsByStudent.mockResolvedValueOnce({});
        render(
            <MemoryRouter>
                <StudentCourseEditionForm />
            </MemoryRouter>
        );
        const studentInput = screen.getByLabelText(/Enter Student Number/i);
        fireEvent.change(studentInput, { target: { value: '1000001' } });
        expect(await screen.findByText(/Remove Student/)).toBeInTheDocument();
    });

    it('handles error when getCourseEditionsByStudent fails', async () => {
        enrolmentService.getCourseEditionsByStudent.mockRejectedValueOnce(new Error('fetch failed'));
        render(
            <MemoryRouter>
                <StudentCourseEditionForm />
            </MemoryRouter>
        );
        const studentInput = screen.getByLabelText(/Enter Student Number/i);
        fireEvent.change(studentInput, { target: { value: '1000001' } });
        expect(await screen.findByText(/Remove Student/)).toBeInTheDocument();
    });

    it('shows error when removing without selecting course edition', async () => {
        render(
            <MemoryRouter>
                <StudentCourseEditionForm />
            </MemoryRouter>
        );

        const studentInput = screen.getByLabelText(/Enter Student Number/i);
        fireEvent.change(studentInput, { target: { value: '1000001' } });
        fireEvent.blur(studentInput);

        await screen.findByText(/Alchemy \(ALCH\)/);

        const removeButton = screen.getByRole('button', { name: /REMOVE/i });
        fireEvent.click(removeButton);

        expect(await screen.findByText(/Please select a Course Edition/i)).toBeInTheDocument();
    });

    it('successfully removes student from course edition', async () => {
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

        const removeButton = screen.getByRole('button', { name: /REMOVE/i });
        fireEvent.click(removeButton);

        expect(await screen.findByText(/Student removed successfully/i)).toBeInTheDocument();
    });

    it('handles error when removal fails', async () => {
        enrolmentService.removeStudentFromCourseEditionService.mockRejectedValueOnce(new Error('remove failed'));
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

        const removeButton = screen.getByRole('button', { name: /REMOVE/i });
        fireEvent.click(removeButton);

        expect(await screen.findByText(/Unsuccessful/i)).toBeInTheDocument();
    });

    it('ignores non-digit input for student number', () => {
        render(
            <MemoryRouter>
                <StudentCourseEditionForm />
            </MemoryRouter>
        );

        const studentInput = screen.getByLabelText(/Enter Student Number/i);
        fireEvent.change(studentInput, { target: { value: 'abc' } });

        expect(studentInput).toHaveValue('');
    });

    it('shows error for student number outside valid range', async () => {
        render(
            <MemoryRouter>
                <StudentCourseEditionForm />
            </MemoryRouter>
        );

        const studentInput = screen.getByLabelText(/Enter Student Number/i);
        fireEvent.change(studentInput, { target: { value: '999999' } });
        fireEvent.blur(studentInput);

        expect(await screen.findByText(/Student number must be between/)).toBeInTheDocument();
    });

    it('closes the modal when onClose is called', async () => {
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

        const removeButton = screen.getByRole('button', { name: /REMOVE/i });
        fireEvent.click(removeButton);

        const closeButton = await screen.findByRole('button', { name: /Close/i });
        fireEvent.click(closeButton);

        expect(screen.queryByRole('button', { name: /Close/i })).toBeNull();
    });
});
