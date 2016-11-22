package fr.fmi.pickaname.app.sorting.presentation;

import android.content.Context;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import fr.fmi.pickaname.R;
import fr.fmi.pickaname.app.sorting.SortingFragment;
import fr.fmi.pickaname.core.entities.FirstName;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class SortingPresenterImplTest {

    @Mock SortingView view;
    @Mock Context context;
    @InjectMocks SortingPresenterImpl presenter;

    @Captor ArgumentCaptor<SortingScreenViewModel> viewModelCaptor;
    @Captor ArgumentCaptor<String> messageCaptor;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void presentSortingScreen_ShouldCallDisplayScreenViewModel() throws Exception {
        // Given
        final String lastName = "LAST NAME";

        // When
        presenter.presentSortingScreen(lastName);

        // Then
        verify(view).displayScreenViewModel(viewModelCaptor.capture());
        assertThat(viewModelCaptor.getValue().lastName).isEqualTo("LAST NAME");
        assertThat(viewModelCaptor.getValue().displayedChild).isEqualTo(SortingFragment.VF_SUCCESS);
    }

    @Test
    public void presentProposedFirstName_ShouldCallDisplayFirstName() throws Exception {
        // Given
        final FirstName firstName = mock(FirstName.class);
        given(firstName.getFirstName()).willReturn("FIRST NAME");

        // When
        presenter.presentProposedFirstName(firstName);

        // Then
        verify(view).displayFirstName("FIRST NAME");
    }

    @Test
    public void presentTechnicalError_ShouldCallDisplayScreenViewModel() throws Exception {
        // Given

        // When
        presenter.presentTechnicalError();

        // Then
        verify(view).displayScreenViewModel(viewModelCaptor.capture());
        assertThat(viewModelCaptor.getValue().displayedChild).isEqualTo(SortingFragment.VF_ERROR);
    }

    @Test
    public void presentLoading_ShouldCallDisplayScreenViewModel() throws Exception {
        // Given

        // When
        presenter.presentLoading();

        // Then
        verify(view).displayScreenViewModel(viewModelCaptor.capture());
        assertThat(viewModelCaptor.getValue().displayedChild).isEqualTo(SortingFragment.VF_LOADING);
    }

    @Test
    public void presentNoMoreFirstName_ShouldCallDisplayScreenViewModel() throws Exception {
        // Given

        // When
        presenter.presentNoMoreFirstName();

        // Then
        verify(view).displayScreenViewModel(viewModelCaptor.capture());
        assertThat(viewModelCaptor.getValue().displayedChild).isEqualTo(SortingFragment.VF_NO_MORE_FIRST_NAME);
    }

    @Test
    public void presentAcceptSavingError_ShouldCallDisplayMessage() throws Exception {
        // Given
        given(context.getString(R.string.fragment_sort_accept_error)).willReturn("TEST MESSAGE");

        // When
        presenter.presentAcceptSavingError();

        // Then
        verify(view).displayMessage(messageCaptor.capture());
        assertThat(messageCaptor.getValue()).isEqualTo("TEST MESSAGE");
    }

    @Test
    public void presentRefuseSavingError_ShouldCallDisplayMessage() throws Exception {
        // Given
        given(context.getString(R.string.fragment_sort_refuse_error)).willReturn("TEST MESSAGE");

        // When
        presenter.presentRefuseSavingError();

        // Then
        verify(view).displayMessage(messageCaptor.capture());
        assertThat(messageCaptor.getValue()).isEqualTo("TEST MESSAGE");
    }
}