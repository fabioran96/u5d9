package fabioran.u5d9.services;

import fabioran.u5d9.entities.Authors;
import fabioran.u5d9.entities.BlogPosts;
import fabioran.u5d9.exceptions.NotFoundException;
import fabioran.u5d9.payloads.NewBlogPostPayload;
import fabioran.u5d9.repositories.BlogPostsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogPostsService {
    @Autowired
    private BlogPostsRepository blogPostsRepository;
    @Autowired
    private AuthorsService authorsService;

    public BlogPosts save(NewBlogPostPayload body) {
        Authors author = authorsService.findById(body.getAuthorId());
        BlogPosts newBlogPost = new BlogPosts();
        newBlogPost.setReadingTime(body.getReadingTime());
        newBlogPost.setContent(body.getContent());
        newBlogPost.setTitle(body.getTitle());
        newBlogPost.setAuthors(author);
        newBlogPost.setCategory(body.getCategory());
        newBlogPost.setCover("http://picsum.photos/200/300");
        return blogPostsRepository.save(newBlogPost);
    }

    public List<BlogPosts> getBlogs() {
        return blogPostsRepository.findAll();
    }

    public BlogPosts findById(int id) {
        return blogPostsRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public void findByIdAndDelete(int id) {
        BlogPosts found = this.findById(id);
        blogPostsRepository.delete(found);
    }

    public BlogPosts findByIdAndUpdate(int id, NewBlogPostPayload body) {
        BlogPosts found = this.findById(id);

        found.setReadingTime(body.getReadingTime());
        found.setContent(body.getContent());
        found.setTitle(body.getTitle());
        found.setCategory(body.getCategory());

        if(found.getAuthors().getId()!= body.getAuthorId()) {
            Authors newAuthor = authorsService.findById(body.getAuthorId());
            found.setAuthors(newAuthor);
        }

        return blogPostsRepository.save(found);
    }

    public List<BlogPosts> findByAuthor(int authorId){
        Authors author = authorsService.findById(authorId);
        return blogPostsRepository.findByAuthors(author);
    }
}
