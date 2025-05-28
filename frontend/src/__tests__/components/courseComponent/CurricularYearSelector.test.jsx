
import React from 'react';
import { render, screen, fireEvent } from '@testing-library/react';
import CurricularYearSelector from '../../../components/courseComponent/CurricularYearSelector';

describe('CurricularYearSelector', () => {
    it('renders the label and default option', () => {
        render(
            <CurricularYearSelector
                years={[1, 2, 3]}
                value=""
                onChange={() => {}}
            />
        );

        expect(screen.getByLabelText('Curricular Year')).toBeInTheDocument();
        expect(screen.getByText('Select a curricular year')).toBeInTheDocument();
    });

    it('renders all options from years prop', () => {
        render(
            <CurricularYearSelector
                years={[1, 2, 3]}
                value=""
                onChange={() => {}}
            />
        );

        expect(screen.getByRole('option', { name: '1' })).toBeInTheDocument();
        expect(screen.getByRole('option', { name: '2' })).toBeInTheDocument();
        expect(screen.getByRole('option', { name: '3' })).toBeInTheDocument();
    });

    it('displays the selected value', () => {
        render(
            <CurricularYearSelector
                years={[1, 2, 3]}
                value="2"
                onChange={() => {}}
            />
        );

        const select = screen.getByLabelText('Curricular Year');
        expect(select.value).toBe('2');
    });

    it('calls onChange when value is changed', () => {
        const handleChange = jest.fn();

        render(
            <CurricularYearSelector
                years={[1, 2, 3]}
                value=""
                onChange={handleChange}
            />
        );

        const select = screen.getByLabelText('Curricular Year');
        fireEvent.change(select, { target: { value: '3' } });

        expect(handleChange).toHaveBeenCalledTimes(1);
    });
});
