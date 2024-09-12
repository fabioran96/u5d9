package fabioran.u5d9.repositories;

import fabioran.u5d9.entities.Authors;
import fabioran.u5d9.entities.BlogPosts;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BlogPostsRepository extends JpaRepository<BlogPosts, Integer> {
    List<BlogPosts> findByAuthors(Authors authors);
}
