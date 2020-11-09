package org.example;

import org.example.stubs.TestInputStream;
import org.example.stubs.TestOutputStream;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import static org.junit.jupiter.api.Assertions.*;

class EchoServiceTest {


    @Test
    public void givenValidRequestAndResponse_whenEcho_thenTrueIsResponded() throws IOException {
        // Given
        EchoService echoService = new EchoService();
        String ip = "10.10.0.10.15";
        int port = 50;
        String request = "Hello World!";

        TestOutputStream outputStream = new TestOutputStream();
        InputStream inputStream = new TestInputStream(request);


        // When
        boolean response = echoService.sendEchoMessage(request, outputStream, inputStream);


        // Then
        String messageSent = outputStream.getMessageSent();

        assertEquals(request, messageSent);

        assertTrue(response);

    }

    @Test
    public void givenValidRequestAndWrongResponse_whenEcho_thenFalseIsResponded() throws IOException {
        // Given
        EchoService echoService = new EchoService();
        String ip = "10.10.0.10.15";
        int port = 50;
        String request = "Hello World!";

        TestOutputStream outputStream = new TestOutputStream();
        InputStream inputStream = new TestInputStream("Other thing");


        // When
        boolean response = echoService.sendEchoMessage(request, outputStream, inputStream);


        // Then
        String messageSent = outputStream.getMessageSent();

        assertEquals(request, messageSent);

        assertFalse(response);

    }

}