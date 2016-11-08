package fr.fmi.pickaname.app.rejected.controller;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import fr.fmi.pickaname.core.rejected.RejectedInteractor;

import static org.mockito.Mockito.verify;

public class RejectedControllerImplTest {

    @Mock RejectedInteractor interactor;
    @InjectMocks RejectedControllerImpl controller;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void load_ShouldCallLoadRejectFirstNamesOfTheInteractor() throws Exception {
        // Given

        // When
        controller.load();

        // Then
        verify(interactor).loadRejectedFirstNames();
    }
}