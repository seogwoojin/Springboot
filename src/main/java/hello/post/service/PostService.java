package hello.post.service;

import hello.post.domain.Post;
import hello.post.repository.JpaPostRepository;
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
        jpaPostRepository.updateViews(id);
        return jpaPostRepository.findById(id);
    }

    public Post editPost(Post post){
        return jpaPostRepository.editPost(post);
    }

    public int delete(Long id){
        return jpaPostRepository.delete(id);
    }
}
