package com.handleservice.handleworkservice.unitTests.config.argumentsolver;

import com.handleservice.handleworkservice.config.annotation.WorkerId;
import com.handleservice.handleworkservice.config.argumentsolver.WorkerIdArgumentResolver;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.MethodParameter;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.util.UUID;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class WorkerIdArgumentResolverTest {
    @InjectMocks
    private WorkerIdArgumentResolver workerIdArgumentResolver;

    @Mock
    private MethodParameter methodParameter;

    @Mock
    private ModelAndViewContainer mavContainer;


    @Test
    public void testSupportsParameter_shouldReturnTrueWhenAnnotatedParameterIsUUID() {
        // Arrange
        when(methodParameter.hasParameterAnnotation(WorkerId.class)).thenReturn(true);
        //noinspection ALL
        when((Class<UUID>) methodParameter.getParameterType()).thenReturn(UUID.class);

        // Act
        boolean result = workerIdArgumentResolver.supportsParameter(methodParameter);

        // Assert
        assertTrue(result);
    }

    @Test
    public void testSupportsParameter_shouldReturnFalseWhenAnnotatedParameterIsString() {
        // Arrange
        when(methodParameter.hasParameterAnnotation(WorkerId.class)).thenReturn(true);
        //noinspection ALL
        when((Class<String>) methodParameter.getParameterType()).thenReturn(String.class);

        // Act
        boolean result = workerIdArgumentResolver.supportsParameter(methodParameter);

        // Assert
        assertFalse(result);
    }

    @Test
    public void testSupportsParameter_shouldReturnFalseWhenParameterIsNotAnnotated() {
        // Arrange
        when(methodParameter.hasParameterAnnotation(WorkerId.class)).thenReturn(false);

        // Act
        boolean result = workerIdArgumentResolver.supportsParameter(methodParameter);

        // Result
        assertFalse(result);
    }

    @Test
    public void testResolveArgument_shouldReturnRightWorkerIdWhenRequestContainsWorkerId() {
        // Arrange
        UUID expectedWorkerId = UUID.fromString("a3a389d9-909e-43dc-9df1-152ee945312c");
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setAttribute("workerId", expectedWorkerId);

        NativeWebRequest webRequest = new ServletWebRequest(request);

        // Act
        Object result = workerIdArgumentResolver.resolveArgument(methodParameter, mavContainer, webRequest, null);

        // Assert
        assertEquals(expectedWorkerId, result);
    }

    @Test
    public void testResolveArgument_shouldReturnNullWhenRequestDoesNotContainWorkerId() {
        // Arrange
        MockHttpServletRequest request = new MockHttpServletRequest();
        NativeWebRequest webRequest = new ServletWebRequest(request);

        // Act
        Object result = workerIdArgumentResolver.resolveArgument(methodParameter, mavContainer, webRequest, null);

        // Assert
        assertNull(result);
    }
}
