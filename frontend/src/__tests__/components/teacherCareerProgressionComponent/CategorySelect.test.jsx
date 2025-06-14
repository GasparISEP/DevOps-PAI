import React from 'react';
import { render, screen, fireEvent } from '@testing-library/react';
import CategorySelect from '../../../components/teacherCareerProgressionComponent/CategorySelect';

describe('CategorySelect', () => {
    const mockOptions = [
        { id: 'c1', name: 'Beginner' },
        { id: 'c2', name: 'Advanced' },
    ];

    const setup = (props = {}) => {
        const defaultProps = {
            value: '',
            onChange: jest.fn(),
            options: mockOptions,
            error: '',
        };
        return render(<CategorySelect {...defaultProps} {...props} />);
    };

    it('renders label and select input', () => {
        setup();
        expect(screen.getByLabelText(/teacher category/i)).toBeInTheDocument();
        expect(screen.getByRole('combobox')).toBeInTheDocument();
    });

    it('renders default option and category options', () => {
        setup();
        const options = screen.getAllByRole('option');
        expect(options).toHaveLength(mockOptions.length + 1); // includes default
        expect(options[0].textContent).toBe('-- Select a Category --');
        expect(options[1].textContent).toBe('Beginner');
        expect(options[2].textContent).toBe('Advanced');
    });

    it('calls onChange when an option is selected', () => {
        const handleChange = jest.fn();
        setup({ onChange: handleChange });
        fireEvent.change(screen.getByRole('combobox'), { target: { value: 'c2' } });
        expect(handleChange).toHaveBeenCalledTimes(1);
    });

    it('renders error message when error prop is provided', () => {
        setup({ error: 'Category is required' });
        expect(screen.getByText('Category is required')).toBeInTheDocument();
    });

    it('does not render error message when error is empty', () => {
        setup({ error: '' });
        expect(screen.queryByText(/required/i)).not.toBeInTheDocument();
    });
});
