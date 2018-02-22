package com.epam.university.java.core.task035;

import com.epam.university.java.core.helper.TestHelper;
import com.epam.university.java.core.task034.Person;
import com.epam.university.java.core.task034.PhoneNumber;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.GsonBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class Task035Test {
    private Task035 instance;

    @Before
    public void setUp() throws Exception {
        instance = TestHelper.getInstance(Task035.class);
    }

    private String readData() throws Exception {
        return new BufferedReader(
                new InputStreamReader(
                        getClass()
                                .getResourceAsStream("/task035/data.json")))
                .lines()
                .collect(Collectors.joining());
    }

    @Test
    public void readWithJackson() throws Exception {
        final ObjectMapper mapper = new ObjectMapper();
        final Person person = instance.readWithJackson(mapper, readData());
        assertNotNull("JSON was not parsed", person);
        assertEquals("Incorrect JSON parsing",
                10,
                person.getId()
        );
        assertEquals("Incorrect JSON parsing",
                "Ivan",
                person.getFirstName()
        );
        assertEquals("Incorrect JSON parsing",
                "Petrov",
                person.getLastName()
        );
        assertNotNull("Incorrect JSON parsing", person.getPhoneNumbers());
        assertEquals("Incorrect JSON parsing",
                2,
                person.getPhoneNumbers().size()
        );
        assertArrayEquals("Incorrect JSON parsing",
                new String[]{"Phone first", "Phone second"},
                person.getPhoneNumbers()
                    .stream()
                    .map(PhoneNumber::getPhoneNumber)
                    .toArray()
        );
    }

    @Test
    public void readWithGson() throws Exception {

        final Person person = instance.readWithGson(
                new GsonBuilder(),
                readData()
        );

        assertNotNull("JSON was not parsed", person);
        assertEquals("Incorrect JSON parsing",
                10,
                person.getId()
        );
        assertEquals("Incorrect JSON parsing",
                "Ivan",
                person.getFirstName()
        );
        assertEquals("Incorrect JSON parsing",
                "Petrov",
                person.getLastName()
        );
        assertNotNull("Incorrect JSON parsing", person.getPhoneNumbers());
        assertEquals("Incorrect JSON parsing",
                2,
                person.getPhoneNumbers().size()
        );
        assertArrayEquals("Incorrect JSON parsing",
                new String[]{"Phone first", "Phone second"},
                person.getPhoneNumbers()
                    .stream()
                    .map(PhoneNumber::getPhoneNumber)
                    .toArray()
        );
    }

}