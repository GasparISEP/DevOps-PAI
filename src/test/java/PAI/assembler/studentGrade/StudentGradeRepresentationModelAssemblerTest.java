package PAI.assembler.studentGrade;

import PAI.dto.studentGrade.GradeAStudentResponseDTO;
import org.junit.jupiter.api.Test;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StudentGradeRepresentationModelAssemblerTest {

    private final StudentGradeRepresentationModelAssembler assembler = new StudentGradeRepresentationModelAssembler();

    @Test
    void toModelShouldReturnEntityModelWithCorrectLinks() {
        // Arrange
        GradeAStudentResponseDTO dto = new GradeAStudentResponseDTO(
                12345,
                18.5,
                "2024-06-09",
                "ceid-1",
                "peid-1",
                "cspid-1",
                "pid-1",
                "syid-1",
                "cid-1",
                "spid-1"
        );

        // Act
        EntityModel<GradeAStudentResponseDTO> entityModel = assembler.toModel(dto);

        // Assert
        assertNotNull(entityModel);
        assertEquals(dto, entityModel.getContent());

        List<Link> links = entityModel.getLinks().toList();
        assertNotNull(links);
        assertEquals(3, links.size());

        boolean hasSelfLink = false;
        boolean hasAverageGradeLink = false;
        boolean hasApprovalRateLink = false;

        for (Link link : links) {
            String rel = link.getRel().value();
            String href = link.getHref();

            if (rel.equals("self") && href.contains("/course-editions/studentgrades/register")) {
                hasSelfLink = true;
            }
            if (rel.equals("averageGrade") && href.contains("/course-editions/averagegrade")) {
                hasAverageGradeLink = true;
            }
            if (rel.equals("/approval-rate") && href.contains("/course-editions/approval-rate")) {
                hasApprovalRateLink = true;
            }
        }

        assertTrue(hasSelfLink, "Should contain self link to /studentgrades/register");
        assertTrue(hasAverageGradeLink, "Should contain link to /averagegrade");
        assertTrue(hasApprovalRateLink, "Should contain link to /approval-rate");
    }
}
