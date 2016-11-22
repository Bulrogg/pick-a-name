package fr.fmi.pickaname.app.accepted.presentation;

import android.content.Context;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.BDDMockito;
import org.mockito.Captor;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import fr.fmi.pickaname.app.accepted.AcceptedFragment;
import fr.fmi.pickaname.app.common.firstname.FirstNameViewModel;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

public class AcceptedPresenterImplTest {

    @Mock AcceptedView view;
    @Mock Context context;
    @InjectMocks AcceptedPresenterImpl presenter;

    @Captor ArgumentCaptor<AcceptedScreenViewModel> viewModelCaptor;
    @Captor ArgumentCaptor<List<FirstNameViewModel>> itemsViewModelCaptor;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void presentLoading_ShouldCorrectlySetupTheView() throws Exception {
        // Given

        // When
        presenter.presentLoading();

        // Then
        verify(view).displayScreenViewModel(viewModelCaptor.capture());
        assertThat(viewModelCaptor.getValue().displayedChild).isEqualTo(AcceptedFragment.VF_LOADING);
    }

    @Test
    public void presentFirstNames_ShouldCorrectlySetupTheView() throws Exception {
        // Given
        final String lastName = "LAST NAME";
        final List<String> firstNames = Arrays.asList("A", "B");

        // When
        presenter.presentFirstNames(firstNames, lastName);

        // Then
        final InOrder inOrder = BDDMockito.inOrder(view);
        inOrder.verify(view).displayScreenViewModel(viewModelCaptor.capture());
        inOrder.verify(view).displayFirstNames(itemsViewModelCaptor.capture());
        assertThat(viewModelCaptor.getValue().displayedChild).isEqualTo(AcceptedFragment.VF_SUCCESS);
        assertThat(itemsViewModelCaptor.getValue()).hasSize(2);
        assertThat(itemsViewModelCaptor.getValue().get(0).firstName).isEqualTo("A");
        assertThat(itemsViewModelCaptor.getValue().get(0).lastName).isEqualTo("LAST NAME");
    }

    @Test
    public void presentNoFirstNameAccepted_ShouldCorrectlySetupTheView() throws Exception {
        // Given

        // When
        presenter.presentNoFirstNameAccepted();

        // Then
        verify(view).displayScreenViewModel(viewModelCaptor.capture());
        assertThat(viewModelCaptor.getValue().displayedChild).isEqualTo(AcceptedFragment.VF_NO_FIRST_NAME_ACCEPTED);
    }

    @Test
    public void presentLoadingFailure_ShouldCorrectlySetupTheView() throws Exception {
        // Given

        // When
        presenter.presentLoadingFailure();

        // Then
        verify(view).displayScreenViewModel(viewModelCaptor.capture());
        assertThat(viewModelCaptor.getValue().displayedChild).isEqualTo(AcceptedFragment.VF_ERROR);
    }

}