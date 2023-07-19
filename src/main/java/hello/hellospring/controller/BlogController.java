package hello.hellospring.controller;

import hello.hellospring.domain.Post;
import hello.hellospring.service.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@Controller
public class BlogController {
    private final PostService postService;

    public BlogController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/blogs/list")
    public String list(Model model){
        List<Post> posts=postService.findPosts();
        model.addAttribute("posts", posts);
        return "blogs/list";
    }

    @GetMapping("/blogs/write")
    public String writeform(){
        return "blogs/write";
    }

    @PostMapping("/blogs/write")
    public String write(PostForm postForm){
        java.util.Date utilDate = new java.util.Date();
        long currentMilliseconds = utilDate.getTime();
        Post post=new Post();

        post.setTitle(postForm.getTitle());
        post.setDate(new Date(currentMilliseconds));
        post.setWriter(postForm.getWriter());
        post.setContent(postForm.getContent());
        post.setViews(1);

        postService.join(post);

        return "redirect:/blogs/list";
    }

    @GetMapping("/blogs/view")
    public String viewWhat(@RequestParam("id") Long id, Model model){
        model.addAttribute("post", postService.findPost(id));
        return "blogs/view";
    }

    @GetMapping("/blogs/editwhat")
    public String editWhat(@RequestParam("id") Long id, Model model){
        model.addAttribute("post", postService.findPost(id));
        return "blogs/edit";
    }

    @PostMapping("/blogs/edit")
    public String edit(@RequestParam("id") Long id, PostForm postForm){
        java.util.Date utilDate = new java.util.Date();
        long currentMilliseconds = utilDate.getTime();

        Post post=postService.findPost(id);

        post.setTitle(postForm.getTitle());
        post.setDate(new Date(currentMilliseconds));
        post.setWriter(postForm.getWriter());
        post.setContent(postForm.getContent());
        postService.editPost(post);
        return "redirect:/blogs/list";
    }


}
