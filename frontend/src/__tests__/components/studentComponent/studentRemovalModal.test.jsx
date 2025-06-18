import React from 'react';
import { render, screen, fireEvent } from '@testing-library/react';
import '@testing-library/jest-dom';
import StudentRemovalModal from '../../../components/studentComponent/StudentRemovalModal';


describe('StudentRemovalModal', () => {

    const mockCourseEdition = {
        courseName: 'Software Engineering',
        courseAcronym: 'SE',
        studyPlanStartYear: 2023
    };

    test('renders success message correctly', () => {
        render(
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
        const mockCourseEdition = {
            courseName: 'Software Engineering',
            courseAcronym: 'SE',
            studyPlanStartYear: 2023,
        };

        render(
            <StudentRemovalModal
                isSuccess={false}
                studentID="7654321"
                courseEdition={mockCourseEdition}
                onClose={jest.fn()}
            />
        );

        // Verifica se o título de insucesso aparece
        expect(screen.getByText('Unsuccessful!')).toBeInTheDocument();

        // Verifica o texto do parágrafo de erro, usando função matcher para texto fragmentado
        const messageElement = screen.getByText((_, element) => {
            return (
                element.tagName.toLowerCase() === 'p' &&
                element.textContent.includes('Error trying to remove student') &&
                element.textContent.includes('7654321') &&
                element.textContent.includes('Software Engineering (SE) - 2023')
            );
        });

        expect(messageElement).toBeInTheDocument();

        // Verifica se o botão Close aparece
        expect(screen.getByRole('button', { name: /close/i })).toBeInTheDocument();
    });




    test('calls onClose when Close button is clicked', () => {
        const onCloseMock = jest.fn();

        render(
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
});
