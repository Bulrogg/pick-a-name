package fr.fmi.pickaname.core.sort;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import fr.fmi.pickaname.core.configuration.ConfigurationRepository;
import fr.fmi.pickaname.core.entities.FirstName;
import fr.fmi.pickaname.core.entities.Settings;
import fr.fmi.pickaname.core.exception.TechnicalException;
import fr.fmi.pickaname.core.firstname.GetFirstNamesRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.spy;

public class SortingInteractorTest {

    @Mock FirstName firstName;
    @Mock Settings settings;
    @Mock SortingPresenter presenter;
    @Mock GetFirstNamesRepository getFirstNamesRepository;
    @Mock ConfigurationRepository configurationRepository;
    @InjectMocks SortingInteractor intercator;

    @Captor ArgumentCaptor<String> lastNameCaptor;
    @Captor ArgumentCaptor<FirstName> firstNameCaptor;

    @Before
    public void setup() throws Exception {
        MockitoAnnotations.initMocks(this);
        intercator = spy(intercator);
        given(configurationRepository.getSettings()).willReturn(settings);
    }

    @Test
    public void load_WhenNoError_ShouldPresentLoadingThenSortingScreen() throws Exception {
        // Given
        given(settings.getLastName()).willReturn("LAST NAME");

        // When
        intercator.load();

        // Then
        final InOrder inOrder = inOrder(presenter, configurationRepository, intercator);
        inOrder.verify(presenter).presentLoading();
        inOrder.verify(configurationRepository).getSettings();
        inOrder.verify(presenter).presentSortingScreen(lastNameCaptor.capture());
        inOrder.verify(intercator).presentNextFirstNameToPropose();

        assertThat(lastNameCaptor.getValue()).isEqualTo("LAST NAME");
    }

    @Test
    public void load_WhenErrorInConfigurationRepository_ShouldPresentLoadingThenTechnicalError() throws Exception {
        // Given
        given(configurationRepository.getSettings()).willThrow(TechnicalException.class);

        // When
        intercator.load();

        // Then
        final InOrder inOrder = inOrder(presenter, configurationRepository);
        inOrder.verify(presenter).presentLoading();
        inOrder.verify(configurationRepository).getSettings();
        inOrder.verify(presenter).presentTechnicalError();
    }

    @Test
    public void accept_WhenNoError_ShouldSaveTheConfigurationAndPresentTheNextFirstName() throws Exception {
        // Given
        final String firstName = "FIRST NAME";
        doNothing().when(configurationRepository).saveAccept(firstName);

        // When
        intercator.accept(firstName);

        // Then
        final InOrder inOrder = inOrder(configurationRepository, intercator);
        inOrder.verify(configurationRepository).saveAccept("FIRST NAME");
        inOrder.verify(intercator).presentNextFirstNameToPropose();
    }

    @Test
    public void accept_WhenErrorInConfigurationRepository_ShouldPresentASavingErrorAndProposeTheNestFirstName() throws Exception {
        // Given
        final String firstName = "FIRST NAME";
        doThrow(TechnicalException.class).when(configurationRepository).saveAccept(firstName);

        // When
        intercator.accept(firstName);

        // Then
        final InOrder inOrder = inOrder(configurationRepository, presenter, intercator);
        inOrder.verify(configurationRepository).saveAccept("FIRST NAME");
        inOrder.verify(presenter).presentAcceptSavingError();
        inOrder.verify(intercator).presentNextFirstNameToPropose();
    }

    @Test
    public void refuse_WhenNoError_ShouldSaveTheConfigurationAndPresentTheNextFirstName() throws Exception {
        // Given
        final String firstName = "FIRST NAME";
        doNothing().when(configurationRepository).saveRefuse(firstName);

        // When
        intercator.refuse(firstName);

        // Then
        final InOrder inOrder = inOrder(configurationRepository, intercator);
        inOrder.verify(configurationRepository).saveRefuse("FIRST NAME");
        inOrder.verify(intercator).presentNextFirstNameToPropose();
    }

    @Test
    public void refuse_WhenErrorInConfigurationRepository_ShouldPresentASavingErrorAndProposeTheNestFirstName() throws Exception {
        // Given
        final String firstName = "FIRST NAME";
        doThrow(TechnicalException.class).when(configurationRepository).saveRefuse(firstName);

        // When
        intercator.refuse(firstName);

        // Then
        final InOrder inOrder = inOrder(configurationRepository, presenter, intercator);
        inOrder.verify(configurationRepository).saveRefuse("FIRST NAME");
        inOrder.verify(presenter).presentRefuseSavingError();
        inOrder.verify(intercator).presentNextFirstNameToPropose();
    }

    @Test
    public void presentNextFirstNameToPropose_WhenNoMoreFirstNameToPropose_ShouldPresentNoMoreFirstName() throws Exception {
        // Given
        given(intercator.getFirstNamesToPropose()).willReturn(Collections.<FirstName>emptyList());

        // When
        intercator.presentNextFirstNameToPropose();

        // Then
        final InOrder inOrder = inOrder(intercator, presenter);
        inOrder.verify(intercator).getFirstNamesToPropose();
        inOrder.verify(presenter).presentNoMoreFirstName();
    }

    @Test
    public void presentNextFirstNameToPropose_WhenTechnicalErrorDuringGetFirstName_ShouldPresentTechnicalError() throws Exception {
        // Given
        doThrow(TechnicalException.class).when(intercator).getFirstNamesToPropose();

        // When
        intercator.presentNextFirstNameToPropose();

        // Then
        final InOrder inOrder = inOrder(intercator, presenter);
        inOrder.verify(intercator).getFirstNamesToPropose();
        inOrder.verify(presenter).presentTechnicalError();
    }

    @Test
    public void presentNextFirstNameToPropose_WhenNoError_ShouldPresentTheNextFirstNameAndRemoveTheLastProposed() throws Exception {
        // Given
        final List<FirstName> firstNamesToPropose = new ArrayList<>();
        firstNamesToPropose.add(firstName);
        doReturn(firstNamesToPropose).when(intercator).getFirstNamesToPropose();

        // When
        intercator.presentNextFirstNameToPropose();

        // Then
        final InOrder inOrder = inOrder(intercator, presenter);
        inOrder.verify(intercator).getFirstNamesToPropose();
        inOrder.verify(presenter).presentProposedFirstName(firstNameCaptor.capture());

        assertThat(firstNameCaptor.getValue()).isEqualTo(firstName);
        assertThat(intercator.getFirstNamesToPropose()).isEmpty();
    }

}