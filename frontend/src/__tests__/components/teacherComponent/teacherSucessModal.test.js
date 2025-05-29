import React from 'react';
import { render, screen, fireEvent } from '@testing-library/react';
import TeacherSuccessModal from '../../../components/teacherComponent/TeacherSuccessModal';

describe('TeacherSuccessModal', () => {
    const departments = [
        { id: '1', name: 'Math' },
        { id: '2', name: 'Physics' }
    ];
    const success = {
        name: 'John Doe',
        id: 'JDO',
        email: 'john@isep.ipp.pt',
        nif: '123456789',
        academicBackground: 'PhD',
        street: 'Main St',
        postalCode: '4000-001',
        location: 'Porto',
        country: 'Portugal',
        countryCode: '+351',
        phoneNumber: '912345678',
        departmentID: '2'
    };

    it('should not render when success is falsy', () => {
        const { container } = render(<TeacherSuccessModal success={null} departments={departments} onClose={jest.fn()} />);
        expect(container.firstChild).toBeNull();
    });

    it('should render all teacher info and close button', () => {
        render(<TeacherSuccessModal success={success} departments={departments} onClose={jest.fn()} />);
        expect(screen.getByText('Success!')).toBeInTheDocument();
        expect(screen.getByText('The teacher was registered successfully.')).toBeInTheDocument();
        expect(screen.getByText('John Doe')).toBeInTheDocument();
        expect(screen.getByText('JDO')).toBeInTheDocument();
        expect(screen.getByText('john@isep.ipp.pt')).toBeInTheDocument();
        expect(screen.getByText('123456789')).toBeInTheDocument();
        expect(screen.getByText('PhD')).toBeInTheDocument();
        expect(screen.getByText('Main St')).toBeInTheDocument();
        expect(screen.getByText('4000-001')).toBeInTheDocument();
        expect(screen.getByText('Porto')).toBeInTheDocument();
        expect(screen.getByText('Portugal')).toBeInTheDocument();
        expect(screen.getByText('+351')).toBeInTheDocument();
        expect(screen.getByText('912345678')).toBeInTheDocument();
        expect(screen.getByText('Physics')).toBeInTheDocument();
        expect(screen.getByRole('button', { name: /close/i })).toBeInTheDocument();
    });

    it('should call onClose when close button is clicked', () => {
        const onClose = jest.fn();
        render(<TeacherSuccessModal success={success} departments={departments} onClose={onClose} />);
        fireEvent.click(screen.getByRole('button', { name: /close/i }));
        expect(onClose).toHaveBeenCalledTimes(1);
    });
});

