package hello;
import hello.post.repository.*;
import hello.member.repository.JpaMemberRepository;
import hello.member.service.MemberService;
import hello.post.service.PostService;
import hello.member.repository.MemberRepository;
import jakarta.persistence.EntityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {
    private final DataSource dataSource;
    private final EntityManager em;
    public SpringConfig(DataSource dataSource, EntityManager em) {
        this.dataSource = dataSource;
        this.em = em;
    }
    @Bean
    public PostService postService(){return new PostService(jpaPostRepository());}
    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }
    @Bean
    public MemberRepository memberRepository() {
// return new MemoryMemberRepository();
// return new JdbcMemberRepository(dataSource);
// return new JdbcTemplateMemberRepository(dataSource);
        return new JpaMemberRepository(em);
    }
    @Bean JpaPostRepository jpaPostRepository(){
        return new JpaPostRepository(em);
    }
}
