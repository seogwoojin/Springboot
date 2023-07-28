package hello.post.controller;

import hello.post.domain.Post;
import hello.post.service.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@RequestMapping("/blogs")
@Controller
public class BlogController {
    private final PostService postService;

    public BlogController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/list")
    public String list(Model model){
        List<Post> posts=postService.findPosts();
        model.addAttribute("posts", posts);
        return "blogs/list";
    }
    @RequestMapping("/list/{page}")
    public String list(@PathVariable int page, Model model){
        List<Post> posts=postService.findPosts();

        int start=(page-1)*10;


        if(start<posts.size()) {
            List<Post> listPost = new ArrayList<>();
            for (int i = start; i < start + 10; i++) {
                if (i == posts.size() - 1) break;
                listPost.add(posts.get(i));
            }
            model.addAttribute("page", page);
            model.addAttribute("posts", listPost);
            return "blogs/list";
        }



        model.addAttribute("posts", posts);
        model.addAttribute("page", page);
        return "blogs/list";
    }

    @GetMapping("/write")
    public String writeform(){
        return "blogs/write";
    }

    @PostMapping("/write")
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

    @GetMapping("/view")
    public String viewWhat(@RequestParam("id") Long id, Model model){
        model.addAttribute("post", postService.findPost(id));
        return "blogs/view";
    }

    @GetMapping("/editwhat")
    public String editWhat(@RequestParam("id") Long id, Model model){
        model.addAttribute("post", postService.findPost(id));
        return "blogs/edit";
    }

    @PostMapping("/edit")
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

    @GetMapping("/delete")
    public String deleteWhat(@RequestParam("id") Long id){
        postService.delete(id);
        return "redirect:/blogs/list";
    }


}
