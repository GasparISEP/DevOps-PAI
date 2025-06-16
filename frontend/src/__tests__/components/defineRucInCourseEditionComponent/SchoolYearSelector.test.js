import { render, screen, fireEvent, act } from '@testing-library/react';
import React, { useState } from 'react';
import SchoolYearSelector from '../../../components/defineRucInCourseEditionComponent/SchoolYearSelector';

const schoolYears = ['2022-2023', '2023-2024', '2024-2025'];

describe('SchoolYearSelector', () => {
    it('updates selected value when controlled', () => {
        function Wrapper() {
            const [value, setValue] = useState('');
            return (
                <SchoolYearSelector
                    schoolYears={schoolYears}
                    value={value}
                    onChange={e => setValue(e.target.value)}
                />
            );
        }

        render(<Wrapper/>);
        const select = screen.getByRole('combobox', {name: 'School Year'});

        expect(select.value).toBe('');

        act(() => {
            fireEvent.change(select, {target: {value: '2023-2024'}});
        });

        expect(select.value).toBe('2023-2024');
    });


    it('calls onChange when user selects a different year', () => {
        function Wrapper() {
            const [value, setValue] = React.useState('');
            return (
                <SchoolYearSelector
                    schoolYears={schoolYears}
                    value={value}
                    onChange={e => {
                        setValue(e.target.value);
                        handleChange(e);
                    }}
                />
            );
        }
        const handleChange = jest.fn();

        render(<Wrapper />);
        const select = screen.getByRole('combobox', { name: 'School Year' });

        fireEvent.change(select, { target: { value: '2023-2024' } });

        expect(handleChange).toHaveBeenCalledTimes(1);
        expect(handleChange.mock.calls[0][0].target.value).toBe('2023-2024');
    });

    it('renders all school years as options', () => {
            render(<SchoolYearSelector schoolYears={schoolYears} value="" onChange={() => {
            }}/>);

            const select = screen.getByRole('combobox', {name: 'School Year'});
            expect(select).toBeInTheDocument();

            schoolYears.forEach(year => {
                expect(screen.getByRole('option', {name: year})).toBeInTheDocument();
            });
        }
    );
    it('shows placeholder when no value is selected', () => {
        render(<SchoolYearSelector schoolYears={schoolYears} value="" onChange={() => {
        }}/>);

        const select = screen.getByRole('combobox', {name: 'School Year'});
        expect(select.value).toBe('');
        expect(screen.getByText('Select a school year')).toBeInTheDocument();
    });
    it('shows selected value when controlled', () => {
        render(<SchoolYearSelector schoolYears={schoolYears} value="2023-2024" onChange={() => {
        }}/>);

        const select = screen.getByRole('combobox', {name: 'School Year'});
        expect(select.value).toBe('2023-2024');
    });


    it('renders with custom placeholder', () => {
        render(
            <SchoolYearSelector
                schoolYears={schoolYears}
                value=""
                onChange={() => {
                }}
                placeholder="Select a school year"
            />
        );
    });
});