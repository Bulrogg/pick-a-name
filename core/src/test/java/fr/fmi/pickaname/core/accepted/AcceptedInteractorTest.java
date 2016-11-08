package fr.fmi.pickaname.core.accepted;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import fr.fmi.pickaname.core.configuration.ConfigurationRepository;
import fr.fmi.pickaname.core.entities.Settings;
import fr.fmi.pickaname.core.entities.Sorting;
import fr.fmi.pickaname.core.exception.TechnicalException;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.inOrder;

public class AcceptedInteractorTest {

    @Mock Settings settings;
    @Mock Sorting sorting;
    @Mock AcceptedPresenter presenter;
    @Mock ConfigurationRepository configurationRepository;
    @InjectMocks AcceptedInteractor interactor;

    @Before
    public void setup() throws TechnicalException {
        MockitoAnnotations.initMocks(this);
        given(configurationRepository.getSorting()).willReturn(sorting);
        given(configurationRepository.getSettings()).willReturn(settings);
    }

    @Test
    public void loadAcceptedFirstNames_WhenFirstNamesSaved_ShouldPresentTheFirstNames() throws Exception {
        // Given
        final List<String> firstNames = Arrays.asList("A", "B");
        given(sorting.getAccepted()).willReturn(firstNames);
        given(settings.getLastName()).willReturn("LAST NAME");

        // When
        interactor.loadAcceptedFirstNames();

        // Then
        final InOrder inOrder = inOrder(presenter, configurationRepository);
        inOrder.verify(presenter).presentLoading();
        inOrder.verify(configurationRepository).getSettings();
        inOrder.verify(configurationRepository).getSorting();
        inOrder.verify(presenter).presentFirstNames(firstNames, "LAST NAME");
    }

    @Test
    public void loadAcceptedFirstNames_WhenNoFirstNameSaved_ShouldPresentNoFirstNameSaved() throws Exception {
        // Given
        given(sorting.getAccepted()).willReturn(new ArrayList<String>());

        // When
        interactor.loadAcceptedFirstNames();

        // Then
        final InOrder inOrder = inOrder(presenter, configurationRepository);
        inOrder.verify(presenter).presentLoading();
        inOrder.verify(configurationRepository).getSorting();
        inOrder.verify(presenter).presentNoFirstNameAccepted();
    }

    @Test
    public void loadAcceptedFirstNames_WhenRepositoryFailure_ShouldPresentloadingError() throws Exception {
        // Given
        given(sorting.getAccepted()).willThrow(TechnicalException.class);

        // When
        interactor.loadAcceptedFirstNames();

        // Then
        final InOrder inOrder = inOrder(presenter, configurationRepository);
        inOrder.verify(presenter).presentLoading();
        inOrder.verify(configurationRepository).getSorting();
        inOrder.verify(presenter).presentLoadingFailure();
    }

}