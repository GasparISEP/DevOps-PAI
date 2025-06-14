import React from 'react';
import { render, screen, fireEvent } from '@testing-library/react';
import TeacherSelect from '../../../components/teacherCareerProgressionComponent/TeacherSelect';

describe('TeacherSelect component', () => {
    const mockOptions = [
        { id: 't1', name: 'Alice Johnson' },
        { id: 't2', name: 'Bob Smith' },
    ];

    const setup = (props = {}) => {
        const defaultProps = {
            value: '',
            onChange: jest.fn(),
            options: mockOptions,
            error: '',
        };
        return render(<TeacherSelect {...defaultProps} {...props} />);
    };

    it('renders label and select input', () => {
        setup();
        expect(screen.getByLabelText(/teacher/i)).toBeInTheDocument();
        expect(screen.getByRole('combobox')).toBeInTheDocument();
    });

    it('renders default option and teacher options', () => {
        setup();
        const options = screen.getAllByRole('option');
        expect(options).toHaveLength(mockOptions.length + 1); // including default
        expect(options[0].textContent).toBe('-- Select a Teacher --');
        expect(options[1].textContent).toContain('t1 - Alice Johnson');
        expect(options[2].textContent).toContain('t2 - Bob Smith');
    });

    it('calls onChange when a different option is selected', () => {
        const handleChange = jest.fn();
        setup({ onChange: handleChange });
        fireEvent.change(screen.getByRole('combobox'), { target: { value: 't2' } });
        expect(handleChange).toHaveBeenCalledTimes(1);
    });

    it('renders an error message when error is provided', () => {
        setup({ error: 'This field is required' });
        expect(screen.getByText(/this field is required/i)).toBeInTheDocument();
    });

    it('does not render error message when error is empty', () => {
        setup({ error: '' });
        expect(screen.queryByText(/this field is required/i)).not.toBeInTheDocument();
    });
});
