import { render, screen, waitFor, fireEvent } from '@testing-library/react';
import userEvent from '@testing-library/user-event';
import StudentProgrammeEnrolmentForm from '../../../components/studentComponent/StudentProgrammeEnrolmentForm';
import * as studentService from '../../../services/studentService';
import { MemoryRouter } from 'react-router-dom';

jest.mock('../../../services/studentService');

describe('StudentProgrammeEnrolmentForm', () => {
  beforeEach(() => {
    jest.resetAllMocks();
    studentService.findAllDepartments.mockResolvedValue([
      { id: 'dept1', name: 'Departamento de Engenharia' }
    ]);
    studentService.findAllAccessMethods.mockResolvedValue([
      { id: 1, name: 'Online' }
    ]);
    studentService.findAllProgrammes.mockResolvedValue([
      { acronym: 'ENG', name: 'Engenharia', departmentID: 'dept1' }
    ]);
  });

  test('allows user to fill and submit the form', async () => {
    // Render form inside router
    render(
        <MemoryRouter>
          <StudentProgrammeEnrolmentForm />
        </MemoryRouter>
    );

    // Wait for departments to load
    await waitFor(() => expect(studentService.findAllDepartments).toHaveBeenCalled());

    // Fill all fields
    await userEvent.type(screen.getByLabelText(/Student Unique Number/i), '1234567');

    userEvent.click(screen.getByText('Select Department'));
    userEvent.click(await screen.findByText('Departamento de Engenharia'));

    await waitFor(() => expect(studentService.findAllProgrammes).toHaveBeenCalled());

    userEvent.click(screen.getByText('Select Programme'));
    userEvent.click(await screen.findByText('Engenharia'));

    userEvent.click(screen.getByText('Select Access Method'));
    userEvent.click(await screen.findByText('Online'));

    await userEvent.type(screen.getByLabelText(/Enrolment Date/i), '12-06-2025');

    // Mock API response for enrolment
    studentService.enrolStudentInProgramme.mockResolvedValue({ message: 'Success' });

    // Submit form
    await userEvent.click(screen.getByRole('button', { name: /enrol/i }));

    // Check for success modal
    await waitFor(() => {
      expect(screen.getByText(/Success!/i)).toBeInTheDocument();
    });
  });

  test('shows error modal if findAllDepartments fails', async () => {
    studentService.findAllDepartments.mockRejectedValueOnce(new Error('Failed to load departments'));
    studentService.findAllAccessMethods.mockResolvedValueOnce([]);
    studentService.findAllProgrammes.mockResolvedValueOnce([]);

    render(<MemoryRouter><StudentProgrammeEnrolmentForm /></MemoryRouter>);

    // Expect error modal to appear
    await waitFor(() => {
      expect(screen.getByText(/Error loading departments/i)).toBeInTheDocument();
    });
  });

  test('shows error modal if findAllProgrammes fails', async () => {
    studentService.findAllProgrammes.mockRejectedValueOnce(new Error('Failed to load programmes'));

    render(<MemoryRouter><StudentProgrammeEnrolmentForm /></MemoryRouter>);

    // Select department to trigger programme fetch
    userEvent.click(await screen.findByText('Select Department'));
    userEvent.click(await screen.findByText('Departamento de Engenharia'));

    // Expect error modal
    await waitFor(() => {
      expect(screen.getByText(/Error loading programmes/i)).toBeInTheDocument();
    });
  });

  test('clears programme list when department is unselected', async () => {
    render(<MemoryRouter><StudentProgrammeEnrolmentForm /></MemoryRouter>);

    await waitFor(() => expect(studentService.findAllDepartments).toHaveBeenCalled());

    // Select department
    userEvent.click(screen.getByText('Select Department'));
    userEvent.click(await screen.findByText('Departamento de Engenharia'));

    await waitFor(() => expect(studentService.findAllProgrammes).toHaveBeenCalled());

    // Deselect department (simulate clear)
    const clearButton = document.querySelector('.form-group .css-1xc3v61-indicatorContainer');
    fireEvent.mouseDown(clearButton);

    // Programme should reset
    await waitFor(() => {
      expect(screen.getByText('Select Programme')).toBeInTheDocument();
      expect(screen.queryByText('Engenharia')).not.toBeInTheDocument();
    });
  });

  test('shows error modal if required fields are missing', async () => {
    render(<MemoryRouter><StudentProgrammeEnrolmentForm /></MemoryRouter>);

    await waitFor(() => expect(studentService.findAllDepartments).toHaveBeenCalled());

    // Click submit without filling anything
    await userEvent.click(screen.getByRole('button', { name: /enrol/i }));

    // Expect validation error modal
    await waitFor(() => {
      expect(screen.getByText(/Please fill in all fields\./i)).toBeInTheDocument();
    });
  });

  test('shows error if Student Unique Number is not exactly 7 digits', async () => {
    render(<MemoryRouter><StudentProgrammeEnrolmentForm /></MemoryRouter>);

    await waitFor(() => expect(studentService.findAllDepartments).toHaveBeenCalled());

    // Fill invalid student ID
    await userEvent.type(screen.getByLabelText(/Student Unique Number/i), '1234');

    // Select valid department
    userEvent.click(screen.getByText('Select Department'));
    userEvent.click(await screen.findByText('Departamento de Engenharia'));

    await waitFor(() => expect(studentService.findAllProgrammes).toHaveBeenCalled());

    // Fill remaining fields correctly
    userEvent.click(screen.getByText('Select Programme'));
    userEvent.click(await screen.findByText('Engenharia'));

    userEvent.click(screen.getByText('Select Access Method'));
    userEvent.click(await screen.findByText('Online'));

    await userEvent.type(screen.getByLabelText(/Enrolment Date/i), '12-06-2025');

    // Submit
    await userEvent.click(screen.getByRole('button', { name: /enrol/i }));

    // Expect specific validation error
    await waitFor(() => {
      expect(screen.getByText(/Student Unique Number must be exactly 7 digits/i)).toBeInTheDocument();
    });
  });

  test('shows error if enrolment date is not in DD-MM-YYYY format', async () => {
    render(<MemoryRouter><StudentProgrammeEnrolmentForm /></MemoryRouter>);

    await waitFor(() => expect(studentService.findAllDepartments).toHaveBeenCalled());

    // Fill correct values except for date
    await userEvent.type(screen.getByLabelText(/Student Unique Number/i), '1234567');

    userEvent.click(screen.getByText('Select Department'));
    userEvent.click(await screen.findByText('Departamento de Engenharia'));

    await waitFor(() => expect(studentService.findAllProgrammes).toHaveBeenCalled());

    userEvent.click(screen.getByText('Select Programme'));
    userEvent.click(await screen.findByText('Engenharia'));

    userEvent.click(screen.getByText('Select Access Method'));
    userEvent.click(await screen.findByText('Online'));

    await userEvent.type(screen.getByLabelText(/Enrolment Date/i), '2025-06-12');

    await userEvent.click(screen.getByRole('button', { name: /enrol/i }));

    await waitFor(() => {
      const errors = screen.getAllByText(/Date must be in DD-MM-YYYY format/i);
      expect(errors.length).toBeGreaterThan(0);
    });
  });

  test('shows success modal when enrolment succeeds', async () => {
    render(<MemoryRouter><StudentProgrammeEnrolmentForm /></MemoryRouter>);

    await waitFor(() => expect(studentService.findAllDepartments).toHaveBeenCalled());

    // Fill valid form
    await userEvent.type(screen.getByLabelText(/Student Unique Number/i), '1234567');
    await userEvent.click(screen.getByLabelText(/Department/i));
    await userEvent.click(await screen.findByText(/Departamento de Engenharia/i));
    await userEvent.click(screen.getByText('Select Programme'));
    await userEvent.click(screen.getByRole('option', { name: /Engenharia/ }));
    await userEvent.click(screen.getByText('Select Access Method'));
    await userEvent.click(await screen.findByText(/Online/));
    await userEvent.type(screen.getByLabelText(/Enrolment Date/i), '15-06-2025');

    studentService.enrolStudentInProgramme.mockResolvedValueOnce({ message: 'Success' });

    await userEvent.click(screen.getByRole('button', { name: /enrol/i }));

    expect(await screen.findByText(/Success/)).toBeInTheDocument();
  });

  test('shows error modal when enrolment fails', async () => {
    render(<MemoryRouter><StudentProgrammeEnrolmentForm /></MemoryRouter>);

    await waitFor(() => expect(studentService.findAllDepartments).toHaveBeenCalled());

    // Fill valid form
    await userEvent.type(screen.getByLabelText(/Student Unique Number/i), '1234567');
    await userEvent.click(screen.getByLabelText(/Department/i));
    await userEvent.click(await screen.findByText(/Departamento de Engenharia/i));
    await userEvent.click(screen.getByText('Select Programme'));
    await userEvent.click(screen.getByRole('option', { name: /Engenharia/ }));
    await userEvent.click(screen.getByText('Select Access Method'));
    await userEvent.click(await screen.findByText(/Online/));
    await userEvent.type(screen.getByLabelText(/Enrolment Date/i), '15-06-2025');

    studentService.enrolStudentInProgramme.mockRejectedValueOnce(new Error('API failure'));

    await userEvent.click(screen.getByRole('button', { name: /enrol/i }));

    expect(await screen.findByText(/API failure/)).toBeInTheDocument();
  });

  test('shows and hides loading state', async () => {
    render(<MemoryRouter><StudentProgrammeEnrolmentForm /></MemoryRouter>);

    await waitFor(() => expect(studentService.findAllDepartments).toHaveBeenCalled());

    // Fill form
    await userEvent.type(screen.getByLabelText(/Student Unique Number/i), '1234567');
    await userEvent.click(screen.getByLabelText(/Department/i));
    await userEvent.click(await screen.findByText(/Departamento de Engenharia/i));
    await userEvent.click(screen.getByText('Select Programme'));
    await userEvent.click(screen.getByRole('option', { name: /Engenharia/ }));
    await userEvent.click(screen.getByText('Select Access Method'));
    await userEvent.click(await screen.findByText(/Online/));
    await userEvent.type(screen.getByLabelText(/Enrolment Date/i), '15-06-2025');

    // Simulate delayed response
    studentService.enrolStudentInProgramme.mockImplementationOnce(() =>
        new Promise(resolve => setTimeout(() => resolve({ message: 'ok' }), 500))
    );

    await userEvent.click(screen.getByRole('button', { name: /enrol/i }));

    await waitFor(() => expect(screen.queryByText(/Submitting.../i)).not.toBeInTheDocument());
  });

  test('displays and closes error modal with correct message', async () => {
    render(<MemoryRouter><StudentProgrammeEnrolmentForm /></MemoryRouter>);

    await waitFor(() => expect(studentService.findAllDepartments).toHaveBeenCalled());

    await userEvent.click(screen.getByRole('button', { name: /enrol/i }));

    // Check modal content
    const errorHeading = await screen.findByText(/Error/i);
    expect(errorHeading).toBeInTheDocument();
    expect(screen.getByText(/Please fill in all fields\./i)).toBeInTheDocument();

    // Close modal
    const closeButton = screen.getByRole('button', { name: /close/i });
    await userEvent.click(closeButton);

    // Modal should be gone
    await waitFor(() => {
      expect(screen.queryByText(/Error/i)).not.toBeInTheDocument();
    });
  });

  test('calls window.location.reload when closing success modal', async () => {
    // Mock window.location.reload
    const reloadMock = jest.fn();
    Object.defineProperty(window, 'location', {
      value: { reload: reloadMock },
      writable: true,
    });

    studentService.enrolStudentInProgramme.mockResolvedValue({ message: 'Success' });

    render(<MemoryRouter><StudentProgrammeEnrolmentForm /></MemoryRouter>);

    await waitFor(() => expect(studentService.findAllDepartments).toHaveBeenCalled());

    // Fill the form
    await userEvent.type(screen.getByLabelText(/Student Unique Number/i), '1234567');
    await userEvent.click(screen.getByLabelText(/Department/i));
    await userEvent.click(await screen.findByText('Departamento de Engenharia'));
    await userEvent.click(screen.getByText('Select Programme'));
    await userEvent.click(await screen.findByText('Engenharia'));
    await userEvent.click(screen.getByText('Select Access Method'));
    await userEvent.click(await screen.findByText('Online'));
    await userEvent.type(screen.getByLabelText(/Enrolment Date/i), '12-06-2025');

    // Submit
    await userEvent.click(screen.getByRole('button', { name: /enrol/i }));

    // Click Close on success modal
    const closeSuccessBtn = await screen.findByRole('button', { name: /close/i });
    await userEvent.click(closeSuccessBtn);

    // Expect reload
    expect(reloadMock).toHaveBeenCalled();
  });
});
