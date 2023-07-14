package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.domain.Post;
import hello.hellospring.repository.JpaMemberRepository;
import hello.hellospring.repository.JpaPostRepository;
import hello.hellospring.repository.MemberRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public class PostService {
    private final JpaPostRepository jpaPostRepository;

    public PostService(JpaPostRepository jpaPostRepository) {
        this.jpaPostRepository=jpaPostRepository;
    }

    public Long join(Post post) {
        jpaPostRepository.save(post);
        return post.getId();
    }

    public List<Post> findPosts() {
        return jpaPostRepository.findAll();
    }

    public Post findPost(Long id){
        return jpaPostRepository.findById(id);
    }
}
