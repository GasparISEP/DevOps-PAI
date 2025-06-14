import { render, screen, fireEvent } from '@testing-library/react';
import ProgrammeErrorModal from '../../../components/programmeComponent/ProgrammeErrorModal';

describe('ProgrammeErrorModal', () => {

    test('renders the error title', () => {

        render(<ProgrammeErrorModal error="This is an error message" onClose={() => {}} />);

        expect(screen.getByText('Registration Error')).toBeInTheDocument();
    });

    test('displays the error message passed via props', () => {

        const errorMsg = 'This is an error message';

        render(<ProgrammeErrorModal error={errorMsg} onClose={() => {}} />);

        expect(screen.getByText(errorMsg)).toBeInTheDocument();
    });

    test('calls onClose when the Close button is clicked', () => {

        const onCloseMock = jest.fn();

        render(<ProgrammeErrorModal error="This is an error message" onClose={onCloseMock} />);

        const closeButton = screen.getByRole('button', { name: /close/i });
        fireEvent.click(closeButton);

        expect(onCloseMock).toHaveBeenCalledTimes(1);
    });

});