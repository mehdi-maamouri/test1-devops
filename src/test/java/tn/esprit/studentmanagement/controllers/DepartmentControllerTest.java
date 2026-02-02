package tn.esprit.studentmanagement.controllers;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DepartmentControllerTest {
    @Mock
    private DepartmentRepository departmentRepository; // Mock the repository

    @InjectMocks
    private DepartmentController departmentController; // Controller under test

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Initialize mocks
    }
     @Test
    void testGetAllDepartments() {
        // Arrange: create fake departments
        Department dep1 = new Department(1L, "Computer Science");
        Department dep2 = new Department(2L, "Mathematics");
        List<Department> fakeDepartments = Arrays.asList(dep1, dep2);

        // Define mock behavior
        when(departmentRepository.findAll()).thenReturn(fakeDepartments);

        // Act: call controller method
        List<Department> result = departmentController.getAllDepartments();

        // Assert: check results
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Mathematics", result.get(1).getName());

        // Verify repository method was called exactly once
        verify(departmentRepository, times(1)).findAll();
    }


    @Test
    void testPipelineIsWorking() {
        // Simple test that always passes
        assertTrue(true);
        assertEquals(1, 1);
    }

    @Test
    void testBasicMath() {
        // Another simple test
        int result = 1 + 1;
        assertEquals(2, result);
    }

    @Test
    void testStringComparison() {
        // Simple string test
        String message = "Pipeline Test";
        assertNotNull(message);
        assertEquals("Pipeline Test", message);
    }
}
