import React from 'react';
import { render, screen, fireEvent } from '@testing-library/react';
import EnrolmentCountModal from '../../../components/courseEditionComponent/EnrolmentCountModal';

describe('EnrolmentCountModal', () => {
    const defaultProps = {
        isOpen: true,
        onClose: jest.fn(),
        count: 5,
        courseName: 'Test Course',
    };

    afterEach(() => {
        jest.clearAllMocks();
    });

    test('does not render if isOpen is false', () => {
        const { container } = render(<EnrolmentCountModal {...defaultProps} isOpen={false} />);
        expect(container.firstChild).toBeNull();
    });

    test('renders modal with course name and enrolment count', () => {
        render(<EnrolmentCountModal {...defaultProps} />);
        expect(screen.getByText('Enrolment Count')).toBeInTheDocument();
        expect(screen.getByText('Course:')).toBeInTheDocument();
        expect(screen.getByText('Test Course')).toBeInTheDocument();
        expect(screen.getByText('Enrolled Students:')).toBeInTheDocument();
        expect(screen.getByText('5')).toBeInTheDocument();
    });

    test('displays 0 when count is null or undefined', () => {
        render(<EnrolmentCountModal {...defaultProps} count={null} />);
        expect(screen.getByText('0')).toBeInTheDocument();
    });

    test('calls onClose when Close button is clicked', () => {
        render(<EnrolmentCountModal {...defaultProps} />);
        fireEvent.click(screen.getByText('Close'));
        expect(defaultProps.onClose).toHaveBeenCalled();
    });

    test('does not close modal when clicking inside content', () => {
        render(<EnrolmentCountModal {...defaultProps} />);
        fireEvent.click(screen.getByText('Enrolment Count'));
        expect(defaultProps.onClose).not.toHaveBeenCalled();
    });
});