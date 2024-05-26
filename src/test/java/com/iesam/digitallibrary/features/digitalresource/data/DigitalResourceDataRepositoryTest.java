package com.iesam.digitallibrary.features.digitalresource.data;

import com.iesam.digitallibrary.features.digitalresource.data.local.DigitalResourceFileLocalDataSource;
import com.iesam.digitallibrary.features.digitalresource.domain.DigitalResource;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
public class DigitalResourceDataRepositoryTest {

    @Mock
    private DigitalResourceFileLocalDataSource digitalResourceFileLocalDataSource;

    private DigitalResourceDataRepository digitalResourceDataRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        digitalResourceDataRepository = new DigitalResourceDataRepository(digitalResourceFileLocalDataSource);
    }

    @AfterEach
    void tearDown() {
        reset(digitalResourceFileLocalDataSource);
    }

    @Test
    void getDigitalResourcesSuccessfully() {
        // Given
        DigitalResource resource1 = new DigitalResource("isbn1", "title1", "author1", "genre1", "year1");
        DigitalResource resource2 = new DigitalResource("isbn2", "title2", "author2", "genre2", "year2");
        List<DigitalResource> expectedResources = Arrays.asList(resource1, resource2);
        when(digitalResourceFileLocalDataSource.findAll()).thenReturn(expectedResources);

        // When
        List<DigitalResource> resources = digitalResourceDataRepository.getDigitalResources();

        // Then
        verify(digitalResourceFileLocalDataSource, times(1)).findAll();
        assertEquals(expectedResources, resources);
    }

    @Test
    void getDigitalResourceSuccessfully() {
        // Given
        String isbn = "isbn1";
        DigitalResource expectedResource = new DigitalResource(isbn, "title1", "author1", "genre1", "year1");
        when(digitalResourceFileLocalDataSource.findById(isbn)).thenReturn(expectedResource);

        // When
        DigitalResource resource = digitalResourceDataRepository.getDigitalResource(isbn);

        // Then
        verify(digitalResourceFileLocalDataSource, times(1)).findById(isbn);
        assertEquals(expectedResource, resource);
    }
}
