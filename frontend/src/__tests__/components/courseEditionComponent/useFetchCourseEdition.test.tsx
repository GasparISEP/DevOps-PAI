import { renderHook, waitFor } from '@testing-library/react';
import { jest } from '@jest/globals';
import useFetchCourseEditions from '../../../components/courseEditionComponent/useFetchCourseEditions';
import { findAllCourseEditions } from '../../../services/DefineRucInCourseEditionService';
import { fetchEnrolmentCount } from '../../../services/enrolmentCountInCourseEditionService';

// Mock services
jest.mock('../../../services/DefineRucInCourseEditionService');
jest.mock('../../../services/enrolmentCountInCourseEditionService');

const mockFindAllCourseEditions = findAllCourseEditions as jest.MockedFunction<typeof findAllCourseEditions>;
const mockFetchEnrolmentCount = fetchEnrolmentCount as jest.MockedFunction<typeof fetchEnrolmentCount>;

describe('useFetchCourseEditions', () => {
    beforeEach(() => {
        jest.clearAllMocks();
    });

    test('fetches and sorts course editions correctly', async () => {
        // Arrange
        const mockCourseEditions = [
            {
                courseEditionGeneratedID: '1',
                programmeAcronym: 'EI',
                schoolYearID: '2024',
                courseAcronym: 'PROG',
                courseName: 'Programação',
                studyPlanImplementationDate: '2024-01-01',
                courseEditionID: 'PROG-2024',
                teacherID: 'T001'
            },
            {
                courseEditionGeneratedID: '2',
                programmeAcronym: 'EI',
                schoolYearID: '2024',
                courseAcronym: 'BD',
                courseName: 'Base de Dados',
                studyPlanImplementationDate: '2024-01-01',
                courseEditionID: 'BD-2024',
                teacherID: 'T002'
            }
        ];

        mockFindAllCourseEditions.mockResolvedValue({
            _embedded: { courseEditionResponseDTOList: mockCourseEditions }
        });

        mockFetchEnrolmentCount
            .mockResolvedValueOnce({ studentCount: 25 })
            .mockResolvedValueOnce({ studentCount: 40 });

        // Act
        const { result } = renderHook(() => useFetchCourseEditions());

        // Assert
        await waitFor(() => {
            expect(result.current).toHaveLength(2);
        });

        expect(result.current[0]).toEqual({
            ...mockCourseEditions[1],
            enrolmentCount: 40
        });

        expect(result.current[1]).toEqual({
            ...mockCourseEditions[0],
            enrolmentCount: 25
        });

        expect(mockFindAllCourseEditions).toHaveBeenCalledTimes(1);
        expect(mockFetchEnrolmentCount).toHaveBeenCalledTimes(2);
    });

    test('handles error when fetching course editions', async () => {
        // Arrange
        mockFindAllCourseEditions.mockRejectedValue(new Error('Network error'));

        const consoleSpy = jest.spyOn(console, 'error').mockImplementation();

        // Act
        const { result } = renderHook(() => useFetchCourseEditions());

        // Assert
        await waitFor(() => {
            expect(consoleSpy).toHaveBeenCalledWith(
                'Failed to load Course Editions:',
                expect.any(Error)
            );
        });

        expect(result.current).toEqual([]);

        consoleSpy.mockRestore();
    });

    test('handles response without _embedded property', async () => {
        // Arrange
        mockFindAllCourseEditions.mockResolvedValue({} as any);

        // Act
        const { result } = renderHook(() => useFetchCourseEditions());

        // Assert
        await waitFor(() => {
            expect(result.current).toEqual([]);
        });
    });

    test('sets enrolmentCount to 0 when fetch fails', async () => {
        // Arrange
        const mockCourseEditions = [
            {
                courseEditionGeneratedID: '1',
                programmeAcronym: 'EI',
                schoolYearID: '2024',
                courseAcronym: 'PROG',
                courseName: 'Programação',
                studyPlanImplementationDate: '2024-01-01',
                courseEditionID: 'PROG-2024',
                teacherID: 'T001'
            }
        ];

        mockFindAllCourseEditions.mockResolvedValue({
            _embedded: { courseEditionResponseDTOList: mockCourseEditions }
        });

        mockFetchEnrolmentCount.mockRejectedValue(new Error('Failed to fetch count'));

        const consoleSpy = jest.spyOn(console, 'error').mockImplementation();

        // Act
        const { result } = renderHook(() => useFetchCourseEditions());

        // Assert
        await waitFor(() => {
            expect(result.current).toHaveLength(1);
        });

        expect(result.current[0].enrolmentCount).toBe(0);
        expect(consoleSpy).toHaveBeenCalledWith(
            'Failed to fetch enrolment count for edition:',
            mockCourseEditions[0],
            expect.any(Error)
        );

        consoleSpy.mockRestore();
    });

    test('sorts course editions by enrolmentCount in descending order', async () => {
        // Arrange
        const mockCourseEditions = [
            { courseEditionGeneratedID: '1', courseName: 'Course A' },
            { courseEditionGeneratedID: '2', courseName: 'Course B' },
            { courseEditionGeneratedID: '3', courseName: 'Course C' }
        ].map(partial => ({
            programmeAcronym: 'EI',
            schoolYearID: '2024',
            courseAcronym: 'TEST',
            studyPlanImplementationDate: '2024-01-01',
            courseEditionID: 'TEST-2024',
            teacherID: 'T001',
            ...partial
        }));

        mockFindAllCourseEditions.mockResolvedValue({
            _embedded: { courseEditionResponseDTOList: mockCourseEditions }
        });

        mockFetchEnrolmentCount
            .mockResolvedValueOnce({ studentCount: 15 })
            .mockResolvedValueOnce({ studentCount: 50 })
            .mockResolvedValueOnce({ studentCount: 30 });

        // Act
        const { result } = renderHook(() => useFetchCourseEditions());

        // Assert
        await waitFor(() => {
            expect(result.current).toHaveLength(3);
        });

        expect(result.current[0].courseName).toBe('Course B');
        expect(result.current[0].enrolmentCount).toBe(50);

        expect(result.current[1].courseName).toBe('Course C');
        expect(result.current[1].enrolmentCount).toBe(30);

        expect(result.current[2].courseName).toBe('Course A');
        expect(result.current[2].enrolmentCount).toBe(15);
    });

    test('handles empty course editions list', async () => {
        // Arrange
        mockFindAllCourseEditions.mockResolvedValue({
            _embedded: { courseEditionResponseDTOList: [] }
        });

        // Act
        const { result } = renderHook(() => useFetchCourseEditions());

        // Assert
        await waitFor(() => {
            expect(result.current).toEqual([]);
        });

        expect(mockFetchEnrolmentCount).not.toHaveBeenCalled();
    });

    test('does not refetch on re-render', async () => {
        // Arrange
        mockFindAllCourseEditions.mockResolvedValue({
            _embedded: { courseEditionResponseDTOList: [] }
        });

        // Act
        const { rerender } = renderHook(() => useFetchCourseEditions());

        await waitFor(() => {
            expect(mockFindAllCourseEditions).toHaveBeenCalledTimes(1);
        });

        // Re-render
        rerender();

        // Assert - should not call again due to empty dependency array
        expect(mockFindAllCourseEditions).toHaveBeenCalledTimes(1);
    });
});