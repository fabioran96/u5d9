package fabioran.u5d9.services;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import fabioran.u5d9.entities.Authors;
import fabioran.u5d9.exceptions.BadRequestException;
import fabioran.u5d9.exceptions.NotFoundException;
import fabioran.u5d9.repositories.AuthorsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class AuthorsService {
    @Autowired
    private AuthorsRepository authorsRepository;

    @Autowired
    private Cloudinary cloudinaryUploader;

    public Authors save(Authors body) {
        authorsRepository.findByEmail(body.getEmail()).ifPresent(user -> {
            throw new BadRequestException("L'email " + body.getEmail() + " è già stata utilizzata");
        });
        body.setAvatar("https://ui-avatars.com/api/?name=" + body.getName().charAt(0) + "+" + body.getSurname().charAt(0));
        return authorsRepository.save(body);
    }

    public Page<Authors> getAuthors(int page, int size, String sort) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
        return authorsRepository.findAll(pageable);
    }

    public Authors findById(int id) {
        return authorsRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public void findByIdAndDelete(int id) {
        Authors found = this.findById(id);
        authorsRepository.delete(found);
    }

    public Authors findByIdAndUpdate(int id, Authors body) {

        Authors found = this.findById(id);
        found.setEmail(body.getEmail());
        found.setName(body.getName());
        found.setSurname(body.getSurname());
        found.setDateOfBirth(body.getDateOfBirth());
        found.setAvatar(body.getAvatar());
        return authorsRepository.save(found);
    }

    public Authors uploadAvatar(int id, MultipartFile image) throws IOException {
        Authors found = this.findById(id);
        String avatarURL = (String) cloudinaryUploader.uploader().upload(image.getBytes(), ObjectUtils.emptyMap()).get("url");
        found.setAvatar(avatarURL);
        return authorsRepository.save(found);
    }
}
