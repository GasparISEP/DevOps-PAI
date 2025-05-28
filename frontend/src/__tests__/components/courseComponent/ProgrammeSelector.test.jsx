import React from 'react';
import {fireEvent, render, screen} from '@testing-library/react';
import '@testing-library/jest-dom';
import ProgrammeSelector from '../../../components/courseComponent/ProgrammeSelector';

describe('ProgrammeSelector', () => {
    const mockProgrammes = [
        { acronym: 'CS', name: 'Computer Science' },
        { acronym: 'ENG', name: 'Engineering' },
    ];
    const mockOnChange = jest.fn();

    // Positive Scenario
    test('renders correctly with valid programmes and triggers onChange on select', () => {
        render(<ProgrammeSelector programmes={mockProgrammes} value="" onChange={mockOnChange} />);

        const select = screen.getByRole('combobox');
        expect(select).toBeInTheDocument();
        expect(select).toHaveValue("");

        // Check options
        expect(screen.getByText('CS - Computer Science')).toBeInTheDocument();
        expect(screen.getByText('ENG - Engineering')).toBeInTheDocument();

        // Trigger change
        fireEvent.change(select, { target: { value: 'CS' } });
        expect(mockOnChange).toHaveBeenCalledTimes(1);
    });

    // Negative Scenario
    test('shows required behavior when no option is selected', () => {
        render(<ProgrammeSelector programmes={mockProgrammes} value="" onChange={mockOnChange} />);
        const select = screen.getByRole('combobox');

        expect(select).toBeRequired();
        expect(select).toHaveValue('');

        // Simulate required validation
        fireEvent.invalid(select);
        expect(select.validationMessage).not.toBe('');
    });

    // Edge Case 1: Empty programme list
    test('renders only default option when programmes list is empty', () => {
        render(<ProgrammeSelector programmes={[]} value="" onChange={mockOnChange} />);
        const options = screen.getAllByRole('option');
        expect(options).toHaveLength(1);
        expect(options[0].textContent).toBe('Select a programme');
    });

    // Edge Case 2: Null or undefined programmes
    test('handles null or undefined programmes', () => {
        const { rerender } = render(<ProgrammeSelector programmes={null} value="" onChange={mockOnChange} />);
        expect(screen.getAllByRole('option')).toHaveLength(1);

        rerender(<ProgrammeSelector programmes={undefined} value="" onChange={mockOnChange} />);
        expect(screen.getAllByRole('option')).toHaveLength(1);
    });

    // Edge Case 3: Duplicate acronyms
    test('renders programmes with duplicate acronyms but different names', () => {
        const dupProgrammes = [
            { acronym: 'BIO', name: 'Biology' },
            { acronym: 'BIO', name: 'Bioinformatics' },
        ];

        render(<ProgrammeSelector programmes={dupProgrammes} value="" onChange={mockOnChange} />);
        expect(screen.getByText('BIO - Biology')).toBeInTheDocument();
        expect(screen.getByText('BIO - Bioinformatics')).toBeInTheDocument();
    });

    // Edge Case 4: Selected value matches an option
    test('select element shows the correct selected value', () => {
        render(<ProgrammeSelector programmes={mockProgrammes} value="ENG" onChange={mockOnChange} />);
        const select = screen.getByRole('combobox');
        expect(select.value).toBe('ENG');
    });
});
