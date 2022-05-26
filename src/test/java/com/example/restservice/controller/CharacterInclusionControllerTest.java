package com.example.restservice.controller;

import com.example.restservice.entity.Inclusion;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.util.Assert;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class CharacterInclusionControllerTest {

    @Autowired
    CharacterInclusionController characterInclusionController;
    @Autowired
    TestRestTemplate testRestTemplate;

    @Test
    void inclusion() {
        Inclusion obj = characterInclusionController.inclusion("This is just a string for test", 'i');
        Assert.isTrue(obj.getCounter() == 3, "No, it's no true");
    }

    @Test
    void testWrongArgument() {
        String actual = testRestTemplate.getForObject("http://localhost:8080/inclusion?string=AAAAAA&symbol=1h", String.class);
        String expected = "{\"errorMessage\":\"Wrong symbol argument exception\",\"debugMessage\":\"Failed to convert value" +
                " of type 'java.lang.String' to required type 'char'; nested exception is java.lang.IllegalArgumentException:" +
                " String [1h] with length 2 cannot be converted to char type: neither Unicode nor single character\"}";
        assertEquals(expected, actual);
    }

    @Test
    void testWrongStringInput() {
        String actual = testRestTemplate.getForObject("http://localhost:8080/inclusion?string=A&symbol=1", String.class);
        String expected = "{\"errorMessage\":\"Entity not found exception\",\"debugMessage\":\"inclusion.name:" +
                " String length must be between 2 and 50\"}";
        assertEquals(expected, actual);
    }
}