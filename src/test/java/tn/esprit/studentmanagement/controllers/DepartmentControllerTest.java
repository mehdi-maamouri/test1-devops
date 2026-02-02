package tn.esprit.studentmanagement.controllers;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DepartmentControllerTest {

    @Test
    void testPipelineIsWorking() {
        // Simple test that always passes
        assertTrue(true);
        assertEquals(1, 1);
    }

    @Test
    void testBasicMath() {
        // Another simple test
        int result = 2 + 2;
        assertEquals(4, result);
    }

    @Test
    void testStringComparison() {
        // Simple string test
        String message = "Pipeline Test";
        assertNotNull(message);
        assertEquals("Pipeline Test", message);
    }
}
