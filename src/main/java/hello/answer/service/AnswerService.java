package hello.answer.service;


import hello.answer.domain.Answer;
import hello.answer.repository.AnswerRepository;
import hello.answer.repository.JpaAnswerRepository;
import hello.question.domain.Question;
import hello.question.service.DataNotFoundException;
import hello.user.SiteUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AnswerService {

//    public AnswerService(AnswerRepository answerRepository) {
//        this.answerRepository = answerRepository;
//    }
//
//    private final AnswerRepository answerRepository;

    private final JpaAnswerRepository jpaAnswerRepository;



    public Answer getAnswer(Integer id) {
        Optional<Answer> answer = this.jpaAnswerRepository.findById(id);
        if (answer.isPresent()) {
            return answer.get();
        } else {
            throw new DataNotFoundException("answer not found");
        }
    }

    public void modify(Answer answer, String content) {
        answer.setContent(content);
        answer.setModifyDate(LocalDateTime.now());
        this.jpaAnswerRepository.save(answer);
    }
    public void delete(Answer answer) {
        this.jpaAnswerRepository.delete(answer);
    }

    public Answer create(Question question, String content, SiteUser author) {
        Answer answer = new Answer();
        answer.setContent(content);
        answer.setCreateDate(LocalDateTime.now());
        answer.setQuestion(question);
        answer.setAuthor(author);
        this.jpaAnswerRepository.save(answer);
        return answer;
    }
    public void vote(Answer answer, SiteUser siteUser) {
        answer.getVoter().add(siteUser);
        this.jpaAnswerRepository.save(answer);
    }

}