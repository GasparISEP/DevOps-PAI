// __tests__/CourseEditionDisplay.test.jsx
import React from 'react';
import { render, screen, fireEvent, waitFor } from '@testing-library/react';
import { MemoryRouter } from 'react-router-dom';
import CourseEditionDisplay from '../../../components/courseEditionComponent/CourseEditionDisplay';
import { fetchEnrolmentCount } from '../../../services/enrolmentCountInCourseEditionService';

// Mock do serviço de contagem de inscrições
jest.mock('../../../services/enrolmentCountInCourseEditionService', () => ({
    fetchEnrolmentCount: jest.fn(),
}));

// Mock do fetch global
beforeEach(() => {
    jest.clearAllMocks();
    global.fetch = jest.fn();
});

const sampleData = [
    {
        courseEditionGeneratedID: '11111111-1111-1111-1111-111111111111',
        programmeAcronym: 'ABC',
        courseName: 'Course One',
        courseAcronym: 'C1',
        schoolYearID: '2024/25',
    },
    {
        courseEditionGeneratedID: '22222222-2222-2222-2222-222222222222',
        programmeAcronym: 'XYZ',
        courseName: 'Course Two',
        courseAcronym: 'C2',
        schoolYearID: '2023/24',
    },
];

describe('CourseEditionDisplay', () => {
    test('renderiza tabela com dados após fetch', async () => {
        global.fetch.mockResolvedValueOnce({
            json: async () => sampleData,
        });

        render(
            <MemoryRouter>
                <CourseEditionDisplay />
            </MemoryRouter>
        );

        // Espera pelo carregamento dos dados
        await waitFor(() => {
            expect(global.fetch).toHaveBeenCalledWith(
                `${process.env.REACT_APP_API_URL}/course-editions`
            );
        });

        // Verifica se ambos os items aparecem na tabela
        expect(await screen.findByText('ABC')).toBeInTheDocument();
        expect(screen.getByText('Course One')).toBeInTheDocument();
        expect(screen.getByText('C1')).toBeInTheDocument();
        expect(screen.getByText('2024/25')).toBeInTheDocument();

        expect(screen.getByText('XYZ')).toBeInTheDocument();
        expect(screen.getByText('Course Two')).toBeInTheDocument();
        expect(screen.getByText('C2')).toBeInTheDocument();
        expect(screen.getByText('2023/24')).toBeInTheDocument();
    });

    test('filtra resultados conforme input e select', async () => {
        global.fetch.mockResolvedValueOnce({
            json: async () => sampleData,
        });

        render(
            <MemoryRouter>
                <CourseEditionDisplay />
            </MemoryRouter>
        );

        await screen.findByText('ABC');

        // Mudar campo de filtro para "Course Name"
        fireEvent.change(screen.getByRole('combobox'), {
            target: { value: 'course name' },
        });

        // Digitar valor que só bate no segundo item
        fireEvent.change(screen.getByPlaceholderText(/Search by Course Name/i), {
            target: { value: 'Two' },
        });

        // Apenas "Course Two" deve aparecer
        expect(screen.queryByText('ABC')).not.toBeInTheDocument();
        expect(screen.getByText('Course Two')).toBeInTheDocument();
    });

    test('paginacao e selecao de items por pagina', async () => {
        // Criar 15 items para testar paginação
        const many = Array.from({ length: 15 }, (_, i) => ({
            ...sampleData[0],
            courseEditionGeneratedID: `id-${i}`,
            programmeAcronym: `P${i}`,
        }));
        global.fetch.mockResolvedValueOnce({ json: async () => many });

        render(
            <MemoryRouter>
                <CourseEditionDisplay />
            </MemoryRouter>
        );

        await screen.findByText('P0');

        // Por defeito mostra 10
        expect(screen.getByText('P0')).toBeInTheDocument();
        expect(screen.getByText('P9')).toBeInTheDocument();
        expect(screen.queryByText('P10')).not.toBeInTheDocument();

        // Next page
        fireEvent.click(screen.getByText('Next'));
        expect(await screen.findByText('P10')).toBeInTheDocument();

        // Mudar para 5 por página
        fireEvent.click(screen.getByText('5'));
        // Página volta a 1, mostra P0-P4
        expect(await screen.findByText('P4')).toBeInTheDocument();
        expect(screen.queryByText('P5')).not.toBeInTheDocument();
    });

    test('abre ActionMenu e conta enrolments, mostra modal', async () => {
        global.fetch.mockResolvedValueOnce({ json: async () => sampleData });
        fetchEnrolmentCount.mockResolvedValueOnce({ count: 42 });

        render(
            <MemoryRouter>
                <CourseEditionDisplay />
            </MemoryRouter>
        );

        await screen.findByText('ABC');

        // Abrir menu de ação do primeiro item
        const menuButtons = screen.getAllByText('⋮');
        fireEvent.click(menuButtons[0]);

        // Clicar em "Count Enrolments"
        fireEvent.click(screen.getByText('Count Enrolments'));

        // Esperar chamada ao serviço
        await waitFor(() => {
            expect(fetchEnrolmentCount).toHaveBeenCalledWith(
                '11111111-1111-1111-1111-111111111111'
            );
        });

        // Modal deve aparecer com contagem e nome do curso
        expect(await screen.findByText('Enrolment Count')).toBeInTheDocument();
        const names = screen.getAllByText('Course One');
        expect(names[1]).toBeInTheDocument();
        expect(screen.getByText('42')).toBeInTheDocument();

        // Fechar modal
        fireEvent.click(screen.getByText('Close'));
        expect(screen.queryByText('Enrolment Count')).not.toBeInTheDocument();
    });

    test('quando nao ha resultados mostra mensagem', async () => {
        global.fetch.mockResolvedValueOnce({ json: async () => [] });

        render(
            <MemoryRouter>
                <CourseEditionDisplay />
            </MemoryRouter>
        );

        await screen.findByText('No results found.');

        expect(screen.getByText('No results found.')).toBeInTheDocument();
    });
});