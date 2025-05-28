import React from 'react';
import { render, screen, fireEvent, waitFor } from '@testing-library/react';
import CourseForm from '../../../components/courseComponent/CourseForm';
import * as courseService from '../../../services/courseService';
jest.mock('../../../services/courseService');


describe('CourseForm component', () => {
    const mockProgrammes = [
        { acronym: 'CS', name: 'Computer Science' },
        { acronym: 'EE', name: 'Electrical Engineering' },
    ];

    const mockCourses = [
        { acronym: 'CS101', name: 'Intro to CS' },
        { acronym: 'EE202', name: 'Circuits' },
    ];

    beforeEach(() => {
        // Mock global fetch para retornar programas e cursos
        global.fetch = jest.fn((url) => {
            if (url.includes('/programmes/ids')) {
                return Promise.resolve({
                    json: () => Promise.resolve(mockProgrammes),
                });
            }
            if (url.includes('/courses/ids')) {
                return Promise.resolve({
                    json: () => Promise.resolve(mockCourses),
                });
            }
            if (url.includes('/programmes/Computer Science/CS')) {
                return Promise.resolve({
                    json: () => Promise.resolve({ quantSemesters: 6, maxECTS: 30 }),
                });
            }
            return Promise.resolve({ json: () => Promise.resolve({}) });
        });

        // Limpar mocks anteriores
        courseService.registerCourseInStudyPlan.mockClear();
    });

    it('renders form with selectors and inputs', async () => {
        render(<CourseForm />);

        // Espera as opções serem carregadas
        await waitFor(() => {
            expect(screen.getByText(/Register a Course/i)).toBeInTheDocument();
        });

        // Verifica selects e input ECTS
        expect(screen.getByLabelText(/programme/i) || screen.getByRole('combobox', { name: /programme/i })).toBeInTheDocument();
        expect(screen.getByLabelText(/course/i) || screen.getByRole('combobox', { name: /course/i })).toBeInTheDocument();
        expect(screen.getByLabelText(/semester/i) || screen.getByRole('combobox', { name: /semester/i })).toBeInTheDocument();
        expect(screen.getByLabelText(/curricularYear/i) || screen.getByRole('combobox', { name: /curricularYear/i })).toBeInTheDocument();
        expect(screen.getByLabelText(/duration/i) || screen.getByRole('combobox', { name: /duration/i })).toBeInTheDocument();
        expect(screen.getByLabelText(/ects/i) || screen.getByRole('spinbutton', { name: /ects/i })).toBeInTheDocument();
    });

    it('submits form successfully', async () => {
        const responseSuccess = {
            programme: 'CS',
            course: 'CS101',
            curricularYear: 1,
            semester: 1,
            duration: 1,
            qtdECTS: 10,
        };

        courseService.registerCourseInStudyPlan.mockResolvedValue(responseSuccess);

        render(<CourseForm />);

        // Espera dados carregarem
        await waitFor(() => screen.getByText(/Register a Course/i));

        // Selecionar programa
        fireEvent.change(screen.getByRole('combobox', { name: /programme/i }), { target: { value: 'CS' } });
        // Espera detalhes do programa carregarem (mockado)
        await waitFor(() => expect(screen.getByRole('combobox', { name: /semester/i })).not.toBeDisabled());

        // Preencher os outros campos
        fireEvent.change(screen.getByRole('combobox', { name: /course/i }), { target: { value: 'CS101' } });
        fireEvent.change(screen.getByRole('combobox', { name: /semester/i }), { target: { value: '1' } });
        fireEvent.change(screen.getByRole('combobox', { name: /curricularYear/i }), { target: { value: '1' } });
        fireEvent.change(screen.getByRole('combobox', { name: /duration/i }), { target: { value: '1' } });
        fireEvent.change(screen.getByRole('spinbutton', { name: /ects/i }), { target: { value: '10' } });

        // Submit
        fireEvent.click(screen.getByRole('button', { name: /submit/i }));

        // Espera chamada do serviço
        await waitFor(() => {
            expect(courseService.registerCourseInStudyPlan).toHaveBeenCalledTimes(1);
            expect(screen.getByText(/Course successfully added to the programme!/i)).toBeInTheDocument();
        });
    });

    it('shows error on invalid numeric fields', async () => {
        render(<CourseForm />);
        await waitFor(() => screen.getByText(/Register a Course/i));

        fireEvent.change(screen.getByRole('combobox', { name: /programme/i }), { target: { value: 'CS' } });
        await waitFor(() => screen.getByRole('combobox', { name: /semester/i }));

        fireEvent.change(screen.getByRole('combobox', { name: /course/i }), { target: { value: 'CS101' } });
        fireEvent.change(screen.getByRole('combobox', { name: /semester/i }), { target: { value: 'notANumber' } });
        fireEvent.change(screen.getByRole('combobox', { name: /curricularYear/i }), { target: { value: '1' } });
        fireEvent.change(screen.getByRole('combobox', { name: /duration/i }), { target: { value: '1' } });
        fireEvent.change(screen.getByRole('spinbutton', { name: /ects/i }), { target: { value: '10' } });

        fireEvent.click(screen.getByRole('button', { name: /submit/i }));

        await waitFor(() => {
            expect(screen.getByText(/All numeric fields must be filled and valid numbers./i)).toBeInTheDocument();
            expect(courseService.registerCourseInStudyPlan).not.toHaveBeenCalled();
        });
    });
});
