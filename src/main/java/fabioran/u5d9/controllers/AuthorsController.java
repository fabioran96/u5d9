package fabioran.u5d9.controllers;

import fabioran.u5d9.entities.Authors;
import fabioran.u5d9.services.AuthorsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/authors")
public class AuthorsController {
    @Autowired
    AuthorsService authorsService;

    // 1. - POST http://localhost:3001/authors (+ req.body)
    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED) // <-- 201
    public Authors saveAuthor(@RequestBody Authors body) throws Exception {
        return authorsService.save(body);
    }

    // 2. - GET http://localhost:3001/authors
    @GetMapping("")
    public Page<Authors> getAuthors(@RequestParam(defaultValue = "0") int page,
                                   @RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "id") String sortBy) {
        return authorsService.getAuthors(page, size, sortBy);
    }

    // 3. - GET http://localhost:3001/authors/{id}
    @GetMapping("/{authorId}")
    public Authors findById(@PathVariable int authorId){
        return authorsService.findById(authorId);
    }

    // 4. - PUT http://localhost:3001/authors/{id} (+ req.body)
    @PutMapping("/{authorId}")
    public Authors findAndUpdate(@PathVariable int authorId, @RequestBody Authors body){
        return authorsService.findByIdAndUpdate(authorId, body);
    }

    // 5. - DELETE http://localhost:3001/authors/{id}
    @DeleteMapping("/{authorId}")
    @ResponseStatus(HttpStatus.NO_CONTENT) // <-- 204 NO CONTENT
    public void findAndDelete(@PathVariable int authorId) {
        authorsService.findByIdAndDelete(authorId);
    }

    //6. - POST IMAGE
    @PostMapping("/{authorId}/avatar")
    public Authors uploadAvatar(@RequestParam("avatar")MultipartFile image, @PathVariable int authorId){
        try {
            return authorsService.uploadAvatar(authorId, image);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
