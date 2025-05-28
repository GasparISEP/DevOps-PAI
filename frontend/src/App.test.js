import { render, screen } from '@testing-library/react';
import App from './App';

test('App renders without crashing', () => {
    render(<App />);
});

test('renders the App component and shows welcome message', () => {
    render(<App />);
    const welcomeElement = screen.getByText(/welcome to isep/i);
    expect(welcomeElement).toBeInTheDocument();
});