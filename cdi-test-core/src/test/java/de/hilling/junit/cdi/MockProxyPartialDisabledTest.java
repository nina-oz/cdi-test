package de.hilling.junit.cdi;

import de.hilling.junit.cdi.beans.Person;
import de.hilling.junit.cdi.service.BackendService;
import de.hilling.junit.cdi.service.SampleService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.inject.Inject;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;

@ExtendWith(CdiTestJunitExtension.class)
@ExtendWith(MockitoExtension.class)
class MockProxyPartialDisabledTest {

    @Mock
    private BackendService backendService;

    @Inject
    private SampleService sampleService;

    @Test
    void createPerson() {
        Person person = new Person();
        sampleService.storePerson(person);
        verify(backendService).storePerson(person);
    }

    @Test
    void doNothing() {
        verifyZeroInteractions(backendService);
    }

}
