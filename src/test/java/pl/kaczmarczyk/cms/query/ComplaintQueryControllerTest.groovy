package pl.kaczmarczyk.cms.query

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.ApplicationEventPublisher
import org.springframework.test.context.ActiveProfiles
import pl.kaczmarczyk.cms.command.ComplaintEvent
import spock.lang.Specification

import java.time.LocalDateTime

@SpringBootTest
@ActiveProfiles("test")
class ComplaintQueryControllerTest extends Specification {

    @Autowired
    private ApplicationEventPublisher publisher

    @Autowired
    private ComplaintQueryRepository repository

    def "should save event in repository"() {
        setup:
        def dateTime = LocalDateTime.of(2025, 1, 1, 0, 0)
        def event = new ComplaintEvent(1, 1, 'claimant', 'content', dateTime, 'pl', 0)

        when:
        publisher.publishEvent(event)

        then:
        def saved = repository.all().getFirst()
        saved.id() == event.id()
        saved.productId() == event.productId()
        saved.claimant() == event.claimant()
        saved.content() == event.content()
        saved.createdAt() == dateTime
        saved.country() == event.country()
        saved.counter() == event.counter()
    }

    def "should update complaint in repository"() {
        setup:
        def dateTime = LocalDateTime.of(2025, 1, 1, 0, 0)
        def complaint = new Complaint(1, 1, 'claimant', 'content', dateTime, 'pl', 0)
        repository.add(complaint)
        def event = new ComplaintEvent(1, 1, 'claimant', 'new content', dateTime, 'pl', 1)

        when:
        publisher.publishEvent(event)

        then:
        def all = repository.all()
        all.size() == 1
        def saved = all.getFirst()
        saved.id() == event.id()
        saved.productId() == event.productId()
        saved.claimant() == event.claimant()
        saved.content() == event.content()
        saved.createdAt() == dateTime
        saved.country() == event.country()
        saved.counter() == event.counter()
    }
}
