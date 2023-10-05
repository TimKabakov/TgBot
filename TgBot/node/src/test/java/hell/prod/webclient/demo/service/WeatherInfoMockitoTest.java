package hell.prod.webclient.demo.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import okhttp3.mockwebserver.MockWebServer;

import java.io.IOException;

@TestInstance(TestInstance.Lifecycle.PER_METHOD)
public class WeatherInfoMockitoTest {
    private static MockWebServer mockWebServer;
    private static ObjectMapper objectMapper;

    @BeforeAll
    static void setUp() throws IOException {
        objectMapper = new ObjectMapper();
        mockWebServer = new MockWebServer();
        mockWebServer.start();
    }

    @AfterAll
    static void tearDown() throws IOException {
        mockWebServer.shutdown();
    }

}
