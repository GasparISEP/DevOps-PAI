
import React from 'react';
import { render, screen, fireEvent } from '@testing-library/react';
import CourseSelector from '../../../components/courseComponent/CourseSelector';

describe('CourseSelector component', () => {
    const mockCourses = [
        { acronym: 'CS101', name: 'Introduction to Computer Science' },
        { acronym: 'MATH201', name: 'Calculus II' },
    ];

    it('renders the select element with label', () => {
        render(<CourseSelector courses={mockCourses} value="" onChange={() => {}} />);
        expect(screen.getByLabelText(/course/i)).toBeInTheDocument();
    });

    it('displays course options', () => {
        render(<CourseSelector courses={mockCourses} value="" onChange={() => {}} />);
        expect(screen.getByText('Select a course')).toBeInTheDocument();
        expect(screen.getByText('CS101 - Introduction to Computer Science')).toBeInTheDocument();
        expect(screen.getByText('MATH201 - Calculus II')).toBeInTheDocument();
    });

    it('calls onChange when a course is selected', () => {
        const handleChange = jest.fn();
        render(<CourseSelector courses={mockCourses} value="" onChange={handleChange} />);
        const select = screen.getByLabelText(/course/i);
        fireEvent.change(select, { target: { value: 'MATH201' } });
        expect(handleChange).toHaveBeenCalledTimes(1);
    });

    it('shows the correct selected value', () => {
        render(<CourseSelector courses={mockCourses} value="CS101" onChange={() => {}} />);
        const select = screen.getByLabelText(/course/i);
        expect(select.value).toBe('CS101');
    });
});
