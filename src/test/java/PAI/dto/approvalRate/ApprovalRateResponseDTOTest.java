package PAI.dto.approvalRate;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ApprovalRateResponseDTOTest {

    @Test
    void shouldCreateApprovalRateResponseDTOCorrectly() {
        double expectedRate = 87.5;
        ApprovalRateResponseDTO dto = new ApprovalRateResponseDTO(expectedRate);
        assertEquals(expectedRate, dto.approvalRate());
    }

    @Test
    void shouldHandleZeroRate() {
        ApprovalRateResponseDTO dto = new ApprovalRateResponseDTO(0.0);
        assertEquals(0.0, dto.approvalRate());
    }

    @Test
    void shouldHandleMaxRate() {
        ApprovalRateResponseDTO dto = new ApprovalRateResponseDTO(100.0);
        assertEquals(100.0, dto.approvalRate());
    }
}
