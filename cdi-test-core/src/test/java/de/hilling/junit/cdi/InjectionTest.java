package de.hilling.junit.cdi;

import de.hilling.junit.cdi.beans.ConstructorInjected;
import de.hilling.junit.cdi.beans.Person;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import javax.inject.Inject;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(CdiTestJunitExtension.class)
class InjectionTest {

    @Inject
    private Person person;

    @Inject
    private TestEnvironment testInformation;

    @Inject
    private ConstructorInjected constructorInjected;


    @Test
    void checkTestInformation() throws Exception {
        assertNotNull(testInformation);
        assertEquals(InjectionTest.class, testInformation.getTestClass());
        assertEquals(InjectionTest.class.getMethod("checkTestInformation"), testInformation.getTestMethod());
    }

    @Test
    void testInjection() {
        assertNotNull(person);
        assertNotNull(constructorInjected);
    }

    @Test
    void testProxiedCostructorInjection() {
        assertNotNull(constructorInjected.getPerson());
        assertNotNull(constructorInjected.getRequest());
    }

    @Test
    void testPersons() {
        checkPersonWorks(person);
        checkPersonWorks(constructorInjected.getPerson());
    }

    private void checkPersonWorks(Person person) {
        person.setName("test");
        assertEquals("test", person.getName());
    }

}
