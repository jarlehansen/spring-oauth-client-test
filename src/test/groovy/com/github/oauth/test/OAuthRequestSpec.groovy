package com.github.oauth.test

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification

@ContextConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class OAuthRequestSpec extends Specification {

    @Autowired
    private OAuthRequest request

    def "OAuth client"() {
        when:
        def response = request.request()

        then:
        response.length() > 0
    }
}
