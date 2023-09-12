package hello.question.repository;

import java.time.LocalDateTime;
import java.util.List;

import hello.answer.controller.AnswerForm;
import hello.answer.domain.Answer;
import hello.question.domain.Question;
import hello.question.service.QuestionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;


@SpringBootTest
class SbbApplicationTests {
    @Autowired
    private JpaQuestionRepository jpaquestionRepository;

    @Test
    @Transactional
    void Test1(){
        Answer answer;
        int la=jpaquestionRepository.findById(313).get().getVoter().size();

        System.out.println(la);
    }

}