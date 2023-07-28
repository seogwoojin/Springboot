package hello.answer.repository;

import hello.answer.domain.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaAnswerRepository extends JpaRepository<Answer, Integer> {
}
