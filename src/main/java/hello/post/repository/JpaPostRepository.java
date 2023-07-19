package hello.post.repository;

import hello.post.domain.Post;
import jakarta.persistence.EntityManager;

import java.util.List;

public class JpaPostRepository {

    private final EntityManager em;

    public JpaPostRepository(EntityManager em) {
        this.em = em;
    }

    public Post save(Post post) {
        em.persist(post);
        return post;
    }

    public List<Post> findAll(){
        return em.createQuery("select m from Post m", Post.class)
                .getResultList();
    }
    public Post findById(Long id) {
        Post post=em.find(Post.class, id);
        return post;
    }
    public int updateViews(Long id){
        return em.createQuery("update Post p set p.views = p.views+1 where p.id = :id")
                .setParameter("id",id)
                .executeUpdate();
    }

    public Post editPost(Post post){
        em.merge(post);
        return post;
    }

    public int delete(Long id){
        return em.createQuery("delete Post p where p.id = :id")
                .setParameter("id",id)
                .executeUpdate();
    }
}
