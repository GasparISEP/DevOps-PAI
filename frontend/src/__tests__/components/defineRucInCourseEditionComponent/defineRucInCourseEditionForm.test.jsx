import React from 'react';
import { render, screen, fireEvent, waitFor } from '@testing-library/react';
import { MemoryRouter } from 'react-router-dom';
import DefineRucInCourseEditionForm from '../../../components/defineRucInCourseEditionComponent/defineRucInCourseEditionForm';
import * as service from '../../../services/DefineRucInCourseEditionService';
import * as teacherService from '../../../services/teacherService';

jest.spyOn(service, 'getAllSchoolYears').mockResolvedValue([
    { id: '2023', description: '2023/2024' }
]);
jest.spyOn(service, 'findAllCourseEditions').mockResolvedValue([
    { courseEditionGeneratedID: '1', name: 'CE1', schoolYearID: '2023' }
]);
jest.spyOn(teacherService, 'getAllTeachers').mockResolvedValue([
    { id: '1', name: 'Prof. X' }
]);
jest.spyOn(service, 'defineRucInCourseEdition').mockResolvedValue({});

describe('DefineRucInCourseEditionForm', () => {
    beforeEach(() => {
        jest.clearAllMocks();
    });

    test('Renders selects and allows successful submission', async () => {
        render(<MemoryRouter>
            <DefineRucInCourseEditionForm />
        </MemoryRouter>);
        await waitFor(() => screen.getByRole('combobox', { name: /School Year/i }));

        fireEvent.change(screen.getByRole('combobox', { name: /School Year/i }), { target: { value: '2023' } });
        fireEvent.change(screen.getByRole('combobox', { name: /Course Edition \/ Programme Edition/i }), { target: { value: '1' } });
        fireEvent.change(screen.getByRole('combobox', { name: /Teacher/i }), { target: { value: '1' } });

        fireEvent.click(screen.getByRole('button', { name: /REGISTER/i }));

        await waitFor(() => {
            expect(screen.getByText(/RUC successfully defined/i)).toBeInTheDocument();
        });
    });
});