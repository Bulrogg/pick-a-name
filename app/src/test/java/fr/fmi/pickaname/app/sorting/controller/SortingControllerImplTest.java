package fr.fmi.pickaname.app.sorting.controller;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import fr.fmi.pickaname.core.sort.SortingInteractor;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.verify;

public class SortingControllerImplTest {

    @Mock SortingInteractor interactor;
    @InjectMocks SortingControllerImpl controller;

    @Captor ArgumentCaptor<String> firstNameCaptor;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void load_ShouldCallInteractorLoad() throws Exception {
        // Given

        // When
        controller.load();

        // Then
        verify(interactor, only()).load();
    }

    @Test
    public void accept_ShouldCallInteractorAccept() throws Exception {
        // Given
        final String firstName = "FIRST NAME";

        // When
        controller.accept(firstName);

        // Then
        verify(interactor, only()).accept(firstNameCaptor.capture());
        assertThat(firstNameCaptor.getValue()).isEqualTo("FIRST NAME");
    }

    @Test
    public void refuse_ShouldCallInteractorRefuse() throws Exception {
        // Given
        final String firstName = "FIRST NAME";

        // When
        controller.refuse(firstName);

        // Then
        verify(interactor, only()).refuse(firstNameCaptor.capture());
        assertThat(firstNameCaptor.getValue()).isEqualTo("FIRST NAME");
    }

}