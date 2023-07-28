package hello;

import hello.answer.repository.AnswerRepository;
import hello.answer.service.AnswerService;
import hello.post.repository.*;
import hello.member.repository.JpaMemberRepository;
import hello.member.service.MemberService;
import hello.post.service.PostService;
import hello.member.repository.MemberRepository;
import hello.question.repository.QuestionRepository;
import hello.question.service.QuestionService;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {
    private final DataSource dataSource;
    private final EntityManager em;

    @Autowired
    public SpringConfig(DataSource dataSource, EntityManager em) {
        this.dataSource = dataSource;
        this.em = em;
    }

    @Bean
    public PostService postService(){return new PostService(jpaPostRepository());}
    @Bean
    public JpaPostRepository jpaPostRepository(){
        return new JpaPostRepository(em);
    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }
    @Bean
    public MemberRepository memberRepository() {
        return new JpaMemberRepository(em);
    }


//    @Bean
//    public QuestionRepository questionRepository(){return new QuestionRepository(em);}
//    @Bean
//    public QuestionService questionService() {
//        return new QuestionService(questionRepository());
//    }

//    @Bean
//    public AnswerRepository answerRepository(){
//        return new AnswerRepository(em);
//    }
//
//    @Bean
//    public AnswerService answerService(){
//        return new AnswerService(answerRepository());
//    }

}
