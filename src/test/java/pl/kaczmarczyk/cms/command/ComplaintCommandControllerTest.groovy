package pl.kaczmarczyk.cms.command

import com.redis.testcontainers.RedisContainer
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.testcontainers.service.connection.ServiceConnection
import org.springframework.http.HttpStatus
import org.springframework.test.context.ActiveProfiles
import org.testcontainers.junit.jupiter.Container
import spock.lang.Specification

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ActiveProfiles("test")
class ComplaintCommandControllerTest extends Specification {

    private final static String COMPLAINT_ENDPOINT = 'http://localhost:8080/api/v1/complaints'

    @Container
    @ServiceConnection
    static RedisContainer REDIS_CONTAINER = new RedisContainer("redis:7.4.2")


    @Autowired
    private TestRestTemplate template

    @Autowired
    private ComplaintCommandRepository repository

    def cleanup() {
        repository.deleteAll()
    }

    def "should add new complaint"() {
        setup:
        def command = new ComplaintCreateCommand(123, 'claimant@test.pl', 'content')
        def country = Locale.getDefault().getCountry()

        when:
        def response = template.postForEntity(
                new URI(COMPLAINT_ENDPOINT),
                command,
                ComplaintResponse.class
        )

        then:
        response.statusCode == HttpStatus.CREATED
        def saved = repository.findById(response.body.id()).get()
        saved.productId == command.productId()
        saved.claimant == command.claimant()
        saved.content == command.content()
        saved.country == country
        saved.counter == 0
    }

    def "should increment counter"() {
        setup:
        def command = new ComplaintCreateCommand(123, 'claimant@test.pl', 'content')
        def country = Locale.getDefault().getCountry()
        repository.save(Complaint.of(command, country))

        when:
        def response = template.postForEntity(
                new URI(COMPLAINT_ENDPOINT),
                command,
                ComplaintResponse.class
        )

        then:
        response.statusCode == HttpStatus.CREATED
        def saved = repository.findById(response.body.id()).get()
        saved.productId == command.productId()
        saved.claimant == command.claimant()
        saved.content == command.content()
        saved.country == country
        saved.counter == 1
    }

}
