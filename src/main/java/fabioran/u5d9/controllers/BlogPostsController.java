package fabioran.u5d9.controllers;

import fabioran.u5d9.entities.BlogPosts;
import fabioran.u5d9.payloads.NewBlogPostPayload;
import fabioran.u5d9.services.BlogPostsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/blogposts")
public class BlogPostsController {
    @Autowired
    BlogPostsService blogPostsService;

    // 1. - POST http://localhost:3001/blogs (+ req.body)
    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED) // <-- 201
    public BlogPosts saveBlog(@RequestBody NewBlogPostPayload body) {
        return blogPostsService.save(body);
    }

    // 2. - GET http://localhost:3001/blogs
    @GetMapping("")
    public List<BlogPosts> getBlogs(@RequestParam(required = false) Integer authorId) {
        if(authorId != null) return blogPostsService.findByAuthor(authorId);
        else return blogPostsService.getBlogs();
    }

    // 3. - GET http://localhost:3001/blogs/{id}
    @GetMapping("/{blogId}")
    public BlogPosts findById(@PathVariable int blogId) {
        return blogPostsService.findById(blogId);
    }

    // 4. - PUT http://localhost:3001/blogs/{id} (+ req.body)
    @PutMapping("/{blogId}")
    public BlogPosts findAndUpdate(@PathVariable int blogId, @RequestBody NewBlogPostPayload body) {
        return blogPostsService.findByIdAndUpdate(blogId, body);
    }

    // 5. - DELETE http://localhost:3001/blogs/{id
    @DeleteMapping("/{blogId}")
    @ResponseStatus(HttpStatus.NO_CONTENT) // <-- 204 NO CONTENT
    public void findAndDelete(@PathVariable int blogId) {
        blogPostsService.findByIdAndDelete(blogId);
    }
}
