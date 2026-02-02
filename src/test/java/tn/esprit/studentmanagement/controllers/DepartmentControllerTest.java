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

public class DepartmentControllerTest { // <-- public class

    @Mock
    private IDepartmentService departmentService;

    @InjectMocks
    private DepartmentController departmentController;

    @BeforeEach
    public void setUp() {  // <-- public
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllDepartment() {  // <-- public
        Department dep = new Department();
        dep.setName("CS");
        dep.setLocation("Building A");
        dep.setPhone("12345");
        dep.setHead("Dr. Smith");

        when(departmentService.getAllDepartments()).thenReturn(List.of(dep));

        List<Department> result = departmentController.getAllDepartment();

        assertEquals(1, result.size());
        assertEquals("CS", result.get(0).getName());

        verify(departmentService, times(1)).getAllDepartments();
    }

    @Test
    public void testPipelineIsWorking() {  // <-- public
        assertTrue(true);
        assertEquals(1, 1);
    }

    @Test
    public void testBasicMath() {  // <-- public
        int result = 1 + 1;
        assertEquals(2, result);
    }

    @Test
    public void testStringComparison() {  // <-- public
        String message = "Pipeline Test";
        assertNotNull(message);
        assertEquals("Pipeline Test", message);
    }
}
