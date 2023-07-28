package hello.question.repository;

import hello.answer.domain.Answer;
import hello.post.domain.Post;
import hello.question.domain.Question;
import jakarta.persistence.EntityManager;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public class QuestionRepository {

    public QuestionRepository(EntityManager em) {
        this.em = em;
    }

    private final EntityManager em;

    public Optional<Question> findById(Integer id) {
        Question question=em.find(Question.class, id);
        return Optional.ofNullable(question);
    }

    public List<Question> findAll(){
        return em.createQuery("select m from Question m", Question.class)
                .getResultList();
    }

    public Question save(Question question){
        em.persist(question);
        return question;
    }

}
