import React from 'react';
import { render, screen, fireEvent } from '@testing-library/react';
import '@testing-library/jest-dom';
import StudentRemovalModal from '../../../components/studentComponent/StudentRemovalModal';
import { BrowserRouter } from 'react-router-dom';


const mockNavigate = jest.fn();
jest.mock('react-router-dom', () => ({
    ...jest.requireActual('react-router-dom'),
    useNavigate: () => mockNavigate
}));

const mockCourseEdition = {
    courseName: 'Software Engineering',
    courseAcronym: 'SE',
    studyPlanStartYear: 2023
};

describe('StudentRemovalModal', () => {

    const renderWithRouter = (ui) => {
        return render(<BrowserRouter>{ui}</BrowserRouter>);
    };

    test('renders success message correctly', () => {
        renderWithRouter(
            <StudentRemovalModal
                isSuccess={true}
                studentID="1234567"
                courseEdition={mockCourseEdition}
                onClose={jest.fn()}
            />
        );

        expect(screen.getByText('Success!')).toBeInTheDocument();

        const messageElement = screen.getByText((_, element) => {
            return element.tagName.toLowerCase() === 'p' &&
                element.textContent.includes('The student 1234567 was removed from') &&
                element.textContent.includes('Software Engineering (SE) - 2023') &&
                element.textContent.includes('successfully.');
        });

        expect(messageElement).toBeInTheDocument();
    });

    test('renders error message correctly', () => {
        renderWithRouter(
            <StudentRemovalModal
                isSuccess={false}
                studentID="7654321"
                courseEdition={mockCourseEdition}
                onClose={jest.fn()}
            />
        );

        expect(screen.getByText('Unsuccessful!')).toBeInTheDocument();

        const messageElement = screen.getByText((_, element) => {
            return (
                element.tagName.toLowerCase() === 'p' &&
                element.textContent.includes('Error trying to remove student') &&
                element.textContent.includes('7654321') &&
                element.textContent.includes('Software Engineering (SE) - 2023')
            );
        });

        expect(messageElement).toBeInTheDocument();
        expect(screen.getByRole('button', { name: /close/i })).toBeInTheDocument();
    });

    test('calls onClose when Close button is clicked', () => {
        const onCloseMock = jest.fn();

        renderWithRouter(
            <StudentRemovalModal
                isSuccess={true}
                studentID="1234567"
                courseEdition={mockCourseEdition}
                onClose={onCloseMock}
            />
        );

        const closeButton = screen.getByRole('button', { name: /close/i });
        fireEvent.click(closeButton);

        expect(onCloseMock).toHaveBeenCalledTimes(1);
    });

    test('calls navigate when list image is clicked', () => {
        renderWithRouter(
            <StudentRemovalModal
                isSuccess={true}
                studentID="1234567"
                courseEdition={mockCourseEdition}
                onClose={jest.fn()}
            />
        );

        const listIcon = screen.getByRole('img', { name: /view all/i });
        fireEvent.click(listIcon);

        expect(mockNavigate).toHaveBeenCalledWith('/courseeditions/display');
    });
});

