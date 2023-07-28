package hello.question.repository;

import hello.question.domain.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaQuestionRepository extends JpaRepository<Question, Integer> {
    Page<Question> findAll(Pageable pageable);

}
