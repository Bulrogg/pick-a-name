package fr.fmi.pickaname.app.reinit.presentation;

import android.content.Context;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import fr.fmi.pickaname.R;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.verify;

public class ReinitPresenterImplTest {

    @Mock ReinitView view;
    @Mock Context context;
    @InjectMocks ReinitPresenterImpl presenter;

    @Captor ArgumentCaptor<String> messageCaptor;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void presentReinitializationFailure_ShouldDisplayAMessage() throws Exception {
        // Given
        given(context.getString(R.string.fragment_reinit_failed)).willReturn("OK");

        // When
        presenter.presentReinitializationFailure();

        // Then
        verify(view, only()).displayMessage(messageCaptor.capture());
        assertThat(messageCaptor.getValue()).isEqualTo("OK");
    }

    @Test
    public void presentReinitializationSuccess_ShouldDisplayAMessage() throws Exception {
        // Given
        given(context.getString(R.string.fragment_reinit_success)).willReturn("KO");

        // When
        presenter.presentReinitializationSuccess();

        // Then
        verify(view, only()).displayMessage(messageCaptor.capture());
        assertThat(messageCaptor.getValue()).isEqualTo("KO");
    }

}