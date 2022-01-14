package org.sparta.tests.APITests;

import org.junit.jupiter.api.*;

import java.util.List;
import java.util.Map;

import static org.sparta.ConnectionManager.*;

public class HeaderTestsTwo {

    @BeforeAll
    static void setupAll(TestInfo testInfo) {
        System.out.println("Executing: " + testInfo.getClass());
        setCityNameQuery("London");
        getConnection();
        Map<String, List<String>> headerMap = getConnectionHeaders();
        Map<String, String> expectedMap = getExpectedMap();
    }

    @Test
    @DisplayName("Check that server is openresty")
    void checkThatServerIsOpenResty() {
        Assertions.assertTrue(checkServer("openresty"));
    }

    @Test
    @DisplayName("Check that Access-Control-Allow-Methods contains GET")
    void checkThatAccessControlAllowMethodsContainsGet() {
        Assertions.assertTrue(checkAccessControllAllowMethods("GET"));
    }

    @Test
    @DisplayName("Check that Access-Control-Allow-Credentials is true")
    void checkThatAccessControlAllowCredentialsIsTrue() {
        Assertions.assertTrue(checkAccessControlAllowCredentials());
    }

    @Test
    @DisplayName("Check that Access-Control-Allow-Origin is *")
    void checkThatAccessControlAllowOriginIsAsterisk() {
        Assertions.assertTrue(checkAccessControlAllowOrigin("*"));
    }

    @Test
    @DisplayName("Check that Content-Length is equal to body length in bytes")
    void checkThatContentLengthIsEqualToBodyLength() {
        Assertions.assertTrue(checkBodyLengthInUtfEight());
    }

    @Test
    @DisplayName("Check that Connection is set to keep-alive")
    void checkThatConnectionIsKeepAlive() {
        Assertions.assertTrue(checkConnection("keep-alive"));
    }

    @Test
    @DisplayName("Check that Content-Type is correct")
    void checkThatContentTypeIsCorrect() {
        Assertions.assertTrue(checkContentType("application/json; charset=utf-8"));
    }

    @Test
    @DisplayName("Check that X-Cache-Key contains query parameters")
    void checkThatXCacheKeyContainsParameters() {
        Assertions.assertTrue(checkXCacheKeyContainsQueries());
    }

    @Test
    @DisplayName("Check that headers match expected values")
    void checkThatHeadersMatchExpectedValues() {
        Assertions.assertTrue(checkAgainstExpectedMap("Server"));
    }

}
