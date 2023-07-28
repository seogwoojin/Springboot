package hello.question.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import hello.question.domain.Question;
import hello.question.repository.JpaQuestionRepository;
import hello.question.repository.QuestionRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class QuestionService {
//
//    public QuestionService(QuestionRepository questionRepository) {
//        this.questionRepository = questionRepository;
//    }
//
//    private final QuestionRepository questionRepository;

    private final JpaQuestionRepository jpaQuestionRepository;


    public List<Question> getList() {
        return this.jpaQuestionRepository.findAll();
    }

    public Question getQuestion(Integer id) {
        Optional<Question> question = this.jpaQuestionRepository.findById(id);
        if (question.isPresent()) {
            return question.get();
        } else {
            throw new DataNotFoundException("question not found");
        }
    }

    public void create(String subject, String content) {
        Question q = new Question();
        q.setSubject(subject);
        q.setContent(content);
        q.setCreateDate(LocalDateTime.now());
        this.jpaQuestionRepository.save(q);
    }

    public Page<Question> getList(int page) {
        List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.desc("createDate"));
        Pageable pageable = PageRequest.of(page, 10, Sort.by(sorts));
        return this.jpaQuestionRepository.findAll(pageable);
    }
}