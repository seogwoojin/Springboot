package hello.answer.service;


import hello.answer.domain.Answer;
import hello.answer.repository.AnswerRepository;
import hello.answer.repository.JpaAnswerRepository;
import hello.question.domain.Question;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class AnswerService {

//    public AnswerService(AnswerRepository answerRepository) {
//        this.answerRepository = answerRepository;
//    }
//
//    private final AnswerRepository answerRepository;

    private final JpaAnswerRepository jpaAnswerRepository;


    public void create(Question question, String content) {
        Answer answer = new Answer();
        answer.setContent(content);
        answer.setCreateDate(LocalDateTime.now());
        answer.setQuestion(question);
        this.jpaAnswerRepository.save(answer);
    }
}