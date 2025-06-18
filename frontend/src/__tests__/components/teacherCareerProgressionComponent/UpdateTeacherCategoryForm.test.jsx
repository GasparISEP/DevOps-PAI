import React from 'react';
import { render, screen, fireEvent, waitFor } from '@testing-library/react';
import { MemoryRouter } from 'react-router-dom';
import UpdateTeacherCategoryForm from '../../../components/teacherCareerProgressionComponent/UpdateTeacherCategoryForm';
import * as updateService from '../../../services/updateTeacherCategoryService';

jest.mock('../../../services/updateTeacherCategoryService');

const renderWithRouter = (ui) => {
    return render(<MemoryRouter>{ui}</MemoryRouter>);
};

jest.mock('../../../components/teacherCareerProgressionComponent/TeacherSelect', () => (props) => {
    return (
        <div>
            <select
                aria-label="teacher"
                name="teacher"
                value={props.value}
                onChange={(e) => props.onChange({ target: { name: 'teacher', value: e.target.value } })}
            >
                <option value="">Select</option>
                <option value="t1">Teacher One</option>
            </select>
            {props.error && <div className="form-error">{props.error}</div>}
        </div>
    );
});

jest.mock('../../../components/teacherCareerProgressionComponent/CategorySelect', () => (props) => {
    return (
        <div>
            <select
                aria-label="teacher category"
                name="teacherCategory"
                value={props.value}
                onChange={(e) => props.onChange({ target: { name: 'teacherCategory', value: e.target.value } })}
            >
                <option value="">Select</option>
                <option value="c1">Category One</option>
            </select>
            {props.error && <div className="form-error">{props.error}</div>}
        </div>
    );
});


jest.mock('../../../components/teacherCareerProgressionComponent/DateInput', () => (props) => {
    return (
        <div>
            <input
                type="text"
                placeholder="Select a Date"
                value={props.value}
                onChange={(e) => props.onChange(e.target.value)}
            />
            {props.error && <div className="form-error">{props.error}</div>}
        </div>
    );
});

jest.mock('../../../components/teacherCareerProgressionComponent/SuccessModal', () => ({ onClose }) => {
    return (
        <div>
            <p>Success!</p>
            <button onClick={onClose}>Close Success</button>
        </div>
    );
});

jest.mock('../../../components/teacherCareerProgressionComponent/ErrorModal', () => ({ onClose, message }) => {
    return (
        <div>
            <p>{message}</p>
            <button onClick={onClose}>Close Error</button>
        </div>
    );
});

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

    it('updates form fields on change', async () => {
        renderWithRouter(<UpdateTeacherCategoryForm />);

        fireEvent.change(screen.getByLabelText(/teacher$/i), {
            target: { value: 't1' }
        });

        fireEvent.change(screen.getByLabelText(/teacher category/i), {
            target: { value: 'c1' }
        });

        const dateInput = screen.getByPlaceholderText(/date/i);
        fireEvent.change(dateInput, { target: { value: '2025-06-19' } });

        expect(dateInput.value).toBe('2025-06-19');
    });

    it('clears the form when clicking CLEAR', async () => {
        renderWithRouter(<UpdateTeacherCategoryForm />);

        const teacherSelect = screen.getByLabelText(/teacher$/i);
        fireEvent.change(teacherSelect, { target: { value: 't1' } });

        fireEvent.click(screen.getByRole('button', { name: /clear/i }));

        expect(teacherSelect.value).toBe('');
    });

    it('submits form successfully and shows success modal', async () => {
        const mockResponse = {
            teacher: 'Teacher One',
            teacherCategory: 'Category One',
            date: '2025-06-19',
            _links: {}
        };

        updateService.updateTeacherCategory.mockResolvedValueOnce({ data: mockResponse });

        renderWithRouter(<UpdateTeacherCategoryForm />);

        await screen.findByText(/Teacher One/);
        await screen.findByText(/Category One/);

        fireEvent.change(screen.getByLabelText(/teacher$/i), { target: { value: 't1' } });
        fireEvent.change(screen.getByLabelText(/teacher category/i), { target: { value: 'c1' } });
        fireEvent.change(screen.getByPlaceholderText(/date/i), { target: { value: '2025-06-19' } });

        fireEvent.click(screen.getByRole('button', { name: /update/i }));

        await waitFor(() => {
            expect(updateService.updateTeacherCategory).toHaveBeenCalledWith({
                teacherId: 't1',
                teacherCategoryID: 'c1',
                date: '2025-06-19'
            });
        });

        await screen.findByText(/the teacher category was updated successfully/i);
    });

    it('shows error modal on failed submit and closes it when clicking Close', async () => {
        updateService.updateTeacherCategory.mockRejectedValueOnce({
            response: { data: { message: 'Server error' } }
        });

        renderWithRouter(<UpdateTeacherCategoryForm />);

        await screen.findByText(/Teacher One/);
        await screen.findByText(/Category One/);

        fireEvent.change(screen.getByLabelText(/teacher$/i), { target: { value: 't1' } });
        fireEvent.change(screen.getByLabelText(/teacher category/i), { target: { value: 'c1' } });
        fireEvent.change(screen.getByPlaceholderText(/date/i), { target: { value: '2025-06-19' } });

        fireEvent.click(screen.getByRole('button', { name: /update/i }));

        await screen.findByText(/server error/i);

        fireEvent.click(screen.getByRole('button', { name: /close/i }));

        expect(screen.queryByText(/server error/i)).not.toBeInTheDocument();
    });

    it('logs error if fetch options fail', async () => {
        console.error = jest.fn(); // mock do console.error

        global.fetch = jest.fn(() => Promise.reject(new Error('Network error')));

        renderWithRouter(<UpdateTeacherCategoryForm />);

        await waitFor(() => {
            expect(console.error).toHaveBeenCalledWith("Failed to load options:", expect.any(Error));
        });
    });

    it('clears form and closes modal when SuccessModal onClose is triggered', async () => {
        const mockResponse = {
            teacher: 'Teacher One',
            teacherCategory: 'Category One',
            date: '2025-06-19',
            _links: {}
        };

        updateService.updateTeacherCategory.mockResolvedValueOnce({ data: mockResponse });

        renderWithRouter(<UpdateTeacherCategoryForm />);

        await screen.findByText(/Teacher One/);
        await screen.findByText(/Category One/);

        fireEvent.change(screen.getByLabelText(/teacher$/i), { target: { value: 't1' } });
        fireEvent.change(screen.getByLabelText(/teacher category/i), { target: { value: 'c1' } });
        fireEvent.change(screen.getByPlaceholderText(/date/i), { target: { value: '2025-06-19' } });

        fireEvent.click(screen.getByRole('button', { name: /update/i }));

        await screen.findByText(/the teacher category was updated successfully/i);

        fireEvent.click(screen.getByRole('button', { name: /close/i }));

        expect(screen.getByLabelText(/teacher$/i).value).toBe('');
        expect(screen.getByLabelText(/teacher category/i).value).toBe('');
        expect(screen.getByPlaceholderText(/date/i).value).toBe('');
    });

    test('shows and removes validation errors as fields are corrected', async () => {
        renderWithRouter(<UpdateTeacherCategoryForm />);

        fireEvent.click(screen.getByRole('button', { name: /update/i }));

        expect(await screen.findByText('⚠️ Choose a Teacher.')).toBeInTheDocument();
        expect(await screen.findByText('⚠️ Choose a Teacher Category.')).toBeInTheDocument();

        fireEvent.change(screen.getByLabelText(/teacher$/i), {
            target: { value: 't1' }
        });
        fireEvent.change(screen.getByLabelText(/teacher category/i), {
            target: { value: 'c1' }
        });
        fireEvent.change(screen.getByPlaceholderText(/date/i), {
            target: { value: '2025-06-19' }
        });

        await waitFor(() => {
            expect(screen.queryByText('⚠️ Choose a Teacher.')).not.toBeInTheDocument();
            expect(screen.queryByText('⚠️ Choose a Teacher Category.')).not.toBeInTheDocument();
        });
    });

    test('shows date error when date is cleared after submit', async () => {
        renderWithRouter(<UpdateTeacherCategoryForm />);

        fireEvent.change(screen.getByLabelText(/teacher$/i), { target: { value: 't1' } });
        fireEvent.change(screen.getByLabelText(/teacher category/i), { target: { value: 'c1' } });
        fireEvent.change(screen.getByPlaceholderText(/date/i), { target: { value: '2025-06-19' } });

        fireEvent.click(screen.getByRole('button', { name: /update/i }));

        fireEvent.change(screen.getByPlaceholderText(/date/i), { target: { value: '' } });

        await waitFor(() => {
            expect(screen.getByText('⚠️ Insert the date.')).toBeInTheDocument();
        });
    });

    test('shows teacher error when teacher field is cleared after submit', async () => {
        renderWithRouter(<UpdateTeacherCategoryForm />);

        fireEvent.change(screen.getByLabelText(/teacher$/i), { target: { value: 't1' } });
        fireEvent.click(screen.getByRole('button', { name: /update/i }));

        fireEvent.change(screen.getByLabelText(/teacher$/i), { target: { value: '' } });

        await waitFor(() => {
            expect(screen.getByText('⚠️ Choose a Teacher.')).toBeInTheDocument();
        });
    });

    it('calls onClose and clears modal state on success modal close', async () => {
        const mockResponse = {
            teacher: 'Teacher One',
            teacherCategory: 'Category One',
            date: '2025-06-19',
            _links: {}
        };

        updateService.updateTeacherCategory.mockResolvedValueOnce({ data: mockResponse });

        renderWithRouter(<UpdateTeacherCategoryForm />);

        await screen.findByText(/Teacher One/);
        await screen.findByText(/Category One/);

        fireEvent.change(screen.getByLabelText(/teacher$/i), { target: { value: 't1' } });
        fireEvent.change(screen.getByLabelText(/teacher category/i), { target: { value: 'c1' } });
        fireEvent.change(screen.getByPlaceholderText(/date/i), { target: { value: '2025-06-19' } });

        fireEvent.click(screen.getByRole('button', { name: /update/i }));

        await screen.findByText(/Success!/i);

        fireEvent.click(screen.getByRole('button', { name: /Close Success/i }));

        expect(screen.getByLabelText(/teacher$/i).value).toBe('');
        expect(screen.getByLabelText(/teacher category/i).value).toBe('');
        expect(screen.getByPlaceholderText(/date/i).value).toBe('');
    });

    it('clears error state when closing ErrorModal with default fallback error', async () => {
        updateService.updateTeacherCategory.mockRejectedValueOnce({});

        renderWithRouter(<UpdateTeacherCategoryForm />);

        await screen.findByText(/Teacher One/);
        await screen.findByText(/Category One/);

        fireEvent.change(screen.getByLabelText(/teacher$/i), { target: { value: 't1' } });
        fireEvent.change(screen.getByLabelText(/teacher category/i), { target: { value: 'c1' } });
        fireEvent.change(screen.getByPlaceholderText(/date/i), { target: { value: '19-06-2025' } }); // <-- Correto!

        fireEvent.click(screen.getByRole('button', { name: /update/i }));

        await screen.findByText(/Unexpected error\./i);

        fireEvent.click(screen.getByRole('button', { name: /Close Error/i }));

        await waitFor(() => {
            expect(screen.queryByText(/Unexpected error\./i)).not.toBeInTheDocument();
        });
    });

    it('sets success data and shows modal on successful submission', async () => {
        const mockResponse = {
            teacher: 'Teacher One',
            teacherCategory: 'Category One',
            date: '2025-06-19',
            _links: {}
        };

        updateService.updateTeacherCategory.mockResolvedValueOnce({ data: mockResponse });

        renderWithRouter(<UpdateTeacherCategoryForm />);

        await screen.findByText(/Teacher One/);
        await screen.findByText(/Category One/);

        fireEvent.change(screen.getByLabelText(/teacher$/i), { target: { value: 't1' } });
        fireEvent.change(screen.getByLabelText(/teacher category/i), { target: { value: 'c1' } });
        fireEvent.change(screen.getByPlaceholderText(/date/i), { target: { value: '19-06-2025' } });

        fireEvent.click(screen.getByRole('button', { name: /update/i }));

        await screen.findByText(/Success!/i);
    });

    it('calls delete on newErrors when teacher and date are corrected', async () => {
        renderWithRouter(<UpdateTeacherCategoryForm />);

        fireEvent.click(screen.getByRole('button', { name: /update/i }));

        fireEvent.change(screen.getByLabelText(/teacher$/i), {
            target: { value: 't1' }
        });

        fireEvent.change(screen.getByPlaceholderText(/date/i), {
            target: { value: '2025-06-19' }
        });

    });

    it('removes teacher error when field is corrected after submission', async () => {
        renderWithRouter(<UpdateTeacherCategoryForm />);

        fireEvent.click(screen.getByRole('button', { name: /update/i }));

        fireEvent.change(screen.getByLabelText(/teacher$/i), {
            target: { value: 't1' }
        });

        await waitFor(() => {
            expect(screen.queryByText(/choose a teacher/i)).not.toBeInTheDocument();
        });
    });

});
