package fr.fmi.pickaname.app.accepted.controller;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import fr.fmi.pickaname.core.accepted.AcceptedInteractor;

import static org.mockito.Mockito.verify;

public class AcceptedControllerImplTest {

    @Mock AcceptedInteractor interactor;
    @InjectMocks AcceptedControllerImpl controller;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void load_ShouldCallLoadAcceptFirstNamesOfTheInteractor() throws Exception {
        // Given

        // When
        controller.load();

        // Then
        verify(interactor).loadAcceptedFirstNames();
    }

}