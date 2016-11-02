package fr.fmi.pickaname.app.storage;

import android.content.SharedPreferences;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

public class DeviceStorageImplTest {

    @Mock SharedPreferences.Editor editor;
    @Mock SharedPreferences sharedPreferences;
    @InjectMocks DeviceStorageImpl deviceStorage;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        given(sharedPreferences.edit()).willReturn(editor);
    }

    @Test
    public void get_ShouldCallGetStringFromSharedPreference() throws Exception {
        // Given
        final String key = "test";
        final String defaultValue = "default";
        given(sharedPreferences.getString(key, defaultValue)).willReturn("stored");

        // When
        final String value = deviceStorage.get(key, defaultValue);

        // Then
        assertThat(value).isEqualTo("stored");
        verify(sharedPreferences).getString("test", "default");
    }

    @Test
    public void _When_Should() throws Exception {
        // Given
        final String key = "test";
        final String value = "value";
        given(editor.putString(key, value)).willReturn(editor);

        // When
        deviceStorage.put(key, value);

        // Then
        verify(editor).putString(key, value);
    }
}