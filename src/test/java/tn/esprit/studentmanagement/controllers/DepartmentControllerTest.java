package tn.esprit.studentmanagement.controllers;

import tn.esprit.studentmanagement.entities.Department;
import tn.esprit.studentmanagement.services.IDepartmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DepartmentControllerTest {

    @Mock
    private IDepartmentService departmentService; // Use the service, not repository

    @InjectMocks
    private DepartmentController departmentController; // Controller under test

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Initialize mocks
    }

    @Test
    void testGetAllDepartment() {
        // Create a fake department
        Department dep = new Department();
        dep.setName("CS");
        dep.setLocation("Building A");
        dep.setPhone("12345");
        dep.setHead("Dr. Smith");

        // Mock the service call
        when(departmentService.getAllDepartments()).thenReturn(List.of(dep));

        // Call the controller method
        List<Department> result = departmentController.getAllDepartment();

        // Assertions
        assertEquals(1, result.size());
        assertEquals("CS", result.get(0).getName());

        // Verify the service was called once
        verify(departmentService, times(1)).getAllDepartments();
    }

    // Simple pipeline test examples
    @Test
    void testPipelineIsWorking() {
        assertTrue(true);
        assertEquals(1, 1);
    }

    @Test
    void testBasicMath() {
        int result = 1 + 1;
        assertEquals(2, result);
    }

    @Test
    void testStringComparison() {
        String message = "Pipeline Test";
        assertNotNull(message);
        assertEquals("Pipeline Test", message);
    }
}
