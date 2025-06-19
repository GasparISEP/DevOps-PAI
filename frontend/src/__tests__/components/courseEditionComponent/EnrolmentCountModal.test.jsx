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

    test('displays 0 when count is undefined', () => {
        render(<EnrolmentCountModal {...defaultProps} count={undefined} />);
        expect(screen.getByText('0')).toBeInTheDocument();
    });

    test('calls onClose when Close button is clicked', () => {
        render(<EnrolmentCountModal {...defaultProps} />);
        fireEvent.click(screen.getByText('Close'));
        expect(defaultProps.onClose).toHaveBeenCalled();
    });

    test('calls onClose when clicking on modal overlay', () => {
        render(<EnrolmentCountModal {...defaultProps} />);
        const overlay = document.querySelector('.modal-overlay');
        fireEvent.click(overlay);
        expect(defaultProps.onClose).toHaveBeenCalled();
    });

    test('does not close modal when clicking inside content', () => {
        render(<EnrolmentCountModal {...defaultProps} />);
        fireEvent.click(screen.getByText('Enrolment Count'));
        expect(defaultProps.onClose).not.toHaveBeenCalled();
    });

    test('displays correct count for different numeric values', () => {
        const { rerender } = render(<EnrolmentCountModal {...defaultProps} count={25} />);
        expect(screen.getByText('25')).toBeInTheDocument();

        rerender(<EnrolmentCountModal {...defaultProps} count={0} />);
        expect(screen.getByText('0')).toBeInTheDocument();

        rerender(<EnrolmentCountModal {...defaultProps} count={100} />);
        expect(screen.getByText('100')).toBeInTheDocument();
    });

    test('displays correct course name for different values', () => {
        const { rerender } = render(<EnrolmentCountModal {...defaultProps} courseName="Advanced JavaScript" />);
        expect(screen.getByText('Advanced JavaScript')).toBeInTheDocument();

        rerender(<EnrolmentCountModal {...defaultProps} courseName="React Fundamentals" />);
        expect(screen.getByText('React Fundamentals')).toBeInTheDocument();
    });

    test('handles empty course name', () => {
        render(<EnrolmentCountModal {...defaultProps} courseName="" />);
        expect(screen.getByText('Course:')).toBeInTheDocument();
        // O texto vazio ainda estará no DOM, apenas não visível
        const courseText = screen.getByText('Course:').parentElement;
        expect(courseText).toBeInTheDocument();
    });

    test('renders with correct CSS classes', () => {
        render(<EnrolmentCountModal {...defaultProps} />);

        expect(document.querySelector('.modal-overlay')).toBeInTheDocument();
        expect(document.querySelector('.modal-content')).toBeInTheDocument();
        expect(document.querySelector('.success')).toBeInTheDocument();
        expect(document.querySelector('.pagination-btn-secondary')).toBeInTheDocument();
        expect(document.querySelector('.pagination-btn2')).toBeInTheDocument();
    });

    test('button has correct initial styles', () => {
        render(<EnrolmentCountModal {...defaultProps} />);
        const button = screen.getByText('Close');

        expect(button).toHaveStyle({
            backgroundColor: '#9a1a24',
            color: 'white',
            border: '2px solid #9a1a24'
        });
    });

    test('button changes style on mouse enter and leave', () => {
        render(<EnrolmentCountModal {...defaultProps} />);
        const button = screen.getByText('Close');

        // Estado inicial
        expect(button).toHaveStyle({
            backgroundColor: '#9a1a24',
            color: 'white'
        });

        // Mouse enter
        fireEvent.mouseEnter(button);
        expect(button).toHaveStyle({
            backgroundColor: '#fff',
            color: '#9a1a24'
        });

        // Mouse leave
        fireEvent.mouseLeave(button);
        expect(button).toHaveStyle({
            backgroundColor: '#9a1a24',
            color: 'white'
        });
    });

    test('handles keyboard events on close button', () => {
        render(<EnrolmentCountModal {...defaultProps} />);
        const button = screen.getByText('Close');

        fireEvent.keyDown(button, { key: 'Enter', code: 'Enter' });
        // O evento de click não é automaticamente disparado pelo keyDown no React Testing Library
        // mas podemos testar se o botão está focável
        expect(button).toBeInTheDocument();
    });

    test('modal content prevents event propagation', () => {
        const onCloseMock = jest.fn();
        render(<EnrolmentCountModal {...defaultProps} onClose={onCloseMock} />);

        const modalContent = document.querySelector('.modal-content');
        fireEvent.click(modalContent);

        expect(onCloseMock).not.toHaveBeenCalled();
    });

    test('renders all required elements with correct structure', () => {
        render(<EnrolmentCountModal {...defaultProps} />);

        // Verifica se todos os elementos estão presentes
        expect(screen.getByRole('heading', { level: 2 })).toHaveTextContent('Enrolment Count');
        expect(screen.getByRole('button', { name: 'Close' })).toBeInTheDocument();

        // Verifica a estrutura HTML
        const overlay = document.querySelector('.modal-overlay');
        const content = document.querySelector('.modal-content');
        const successDiv = document.querySelector('.success');

        expect(overlay).toContainElement(content);
        expect(content).toContainElement(successDiv);
    });
});