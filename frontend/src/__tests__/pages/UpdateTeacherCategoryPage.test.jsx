import React from 'react';
import { render, screen } from '@testing-library/react';
import UpdateTeacherCategoryPage from '../../pages/teacherCategoryPage/UpdateTeacherCategoryPage';

// Mock child components to isolate the page test
jest.mock('../../components/teacherCareerProgressionComponent/UpdateTeacherCategoryForm', () => () => <div data-testid="update-form" />);
jest.mock('../../components/NavBar', () => () => <nav data-testid="navbar">NavBar</nav>);
jest.mock('../../components/Footer', () => () => <footer data-testid="footer">Footer</footer>);

describe('UpdateTeacherCategoryPage', () => {
    test('renders without crashing', () => {
        render(<UpdateTeacherCategoryPage/>);
        expect(screen.getByTestId('navbar')).toBeInTheDocument();
        expect(screen.getByTestId('update-form')).toBeInTheDocument();
        expect(screen.getByTestId('footer')).toBeInTheDocument();
    });

    test('NavBar is rendered at the top', () => {
        render(<UpdateTeacherCategoryPage/>);
        const nav = screen.getByTestId('navbar');
        expect(nav).toHaveTextContent('NavBar');
    });

    test('UpdateTeacherCategoryForm is rendered correctly', () => {
        render(<UpdateTeacherCategoryPage/>);
        const form = screen.getByTestId('update-form');
        expect(form).toBeInTheDocument();
    });

    test('Footer is rendered at the bottom', () => {
        render(<UpdateTeacherCategoryPage/>);
        const footer = screen.getByTestId('footer');
        expect(footer).toHaveTextContent('Footer');
    });

    test('Page should not crash with empty child components', () => {
        jest.resetModules(); // Clear mocks
        jest.doMock('../../components/teacherCareerProgressionComponent/UpdateTeacherCategoryForm', () => () => null);
        jest.doMock('../../components/NavBar', () => () => null);
        jest.doMock('../../components/Footer', () => () => null);

        const UpdateTeacherCategoryPageWithNulls = require('../../pages/teacherCategoryPage/UpdateTeacherCategoryPage').default;
        render(<UpdateTeacherCategoryPageWithNulls/>);
        // If it doesn't crash, test passes
        expect(true).toBe(true);
    });

    test('Handles missing CSS gracefully', () => {
        // Simulate CSS import failure
        jest.mock('../../styles/UpdateTeacherCategoryPage.css', () => {
            throw new Error('CSS load failed');
        });

        const consoleSpy = jest.spyOn(console, 'error').mockImplementation(() => {
        });
        expect(() => render(<UpdateTeacherCategoryPage/>)).not.toThrow();
        consoleSpy.mockRestore();
    });

    test('Does not render unexpected elements', () => {
        render(<UpdateTeacherCategoryPage/>);
        expect(screen.queryByText('Unexpected')).not.toBeInTheDocument();
    });
});