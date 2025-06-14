import React from 'react';
import { render, screen, fireEvent, waitFor } from '@testing-library/react';
import { MemoryRouter } from 'react-router-dom';
import UpdateTeacherCategoryForm from '../../../components/teacherCareerProgressionComponent/UpdateTeacherCategoryForm';
import * as updateService from '../../../services/updateTeacherCategoryService';

jest.mock('../../../services/updateTeacherCategoryService');

const renderWithRouter = (ui) => {
    return render(<MemoryRouter>{ui}</MemoryRouter>);
};

describe('UpdateTeacherCategoryForm', () => {
    beforeEach(() => {
        jest.resetAllMocks();
        global.fetch = jest.fn((url) => {
            if (url.includes('/teachers')) {
                return Promise.resolve({
                    json: () => Promise.resolve({
                        _embedded: {
                            teacherDTOList: [
                                { id: 't1', name: 'Teacher One' },
                            ]
                        }
                    })
                });
            } else if (url.includes('/teacher-categories')) {
                return Promise.resolve({
                    json: () => Promise.resolve([
                        { id: 'c1', name: 'Category One' },
                    ])
                });
            }
            return Promise.reject(new Error('Unknown endpoint'));
        });
    });

    it('renders form with fetched options', async () => {
        renderWithRouter(<UpdateTeacherCategoryForm />);
        expect(await screen.findByText(/Update Teacher Category/i)).toBeInTheDocument();
        expect(await screen.findByText(/Teacher One/)).toBeInTheDocument();
        expect(await screen.findByText(/Category One/)).toBeInTheDocument();
    });

    it('validates required fields before submission', async () => {
        renderWithRouter(<UpdateTeacherCategoryForm />);
        fireEvent.click(screen.getByRole('button', { name: /update/i }));

        await waitFor(() => {
            expect(screen.getByText((content) => content.includes('⚠️ Choose a Teacher.'))).toBeInTheDocument();
            expect(screen.getByText((content) => content.includes('⚠️ Choose a Teacher Category.'))).toBeInTheDocument();
            expect(screen.getByText((content) => content.includes('⚠️ Insert the date.'))).toBeInTheDocument();
        });
    });
});
