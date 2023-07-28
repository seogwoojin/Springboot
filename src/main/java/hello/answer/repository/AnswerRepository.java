package hello.answer.repository;


import hello.answer.domain.Answer;
import jakarta.persistence.EntityManager;

public class AnswerRepository {

    public AnswerRepository(EntityManager em) {
        this.em = em;
    }

    private final EntityManager em;

    public Answer save(Answer answer){
        em.persist(answer);
        return answer;
    }


}