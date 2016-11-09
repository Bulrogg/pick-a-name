package fr.fmi.pickaname.app.reinit.controller;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import fr.fmi.pickaname.core.reinit.ReinitInteractor;

import static org.mockito.Mockito.verify;

public class ReinitControllerImplTest {

    @Mock ReinitInteractor interactor;
    @InjectMocks ReinitControllerImpl controller;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void reinitialize_ShouldCallReinitInteractor() throws Exception {
        // Given

        // When
        controller.reinitialize();

        // Then
        verify(interactor).reinitializeConfiguration();
    }

}