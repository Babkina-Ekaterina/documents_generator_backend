package cs.vsu.ru.documents_generator.service.author;

import cs.vsu.ru.documents_generator.data.dto.author.AuthorEditDto;
import cs.vsu.ru.documents_generator.data.dto.author.AuthorLoginDto;
import cs.vsu.ru.documents_generator.data.dto.author.AuthorRegisterDto;
import cs.vsu.ru.documents_generator.data.dto.author.GetAuthorDto;
import cs.vsu.ru.documents_generator.data.entity.author.AuthorEntity;
import cs.vsu.ru.documents_generator.data.mapper.author.AuthorMapper;
import cs.vsu.ru.documents_generator.data.repository.AuthorRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class AuthorService {
    private AuthorRepository authorRepository;

    private AuthorMapper authorMapper;
    private PasswordEncoder passwordEncoder;

    @Transactional
    public Boolean authorRegister(AuthorRegisterDto authorRegisterDto) {
        AuthorEntity authorEntity = authorMapper.authorRegisterDtoToAuthorEntity(authorRegisterDto);
        if (authorRepository.findAuthorEntityByLogin(authorEntity.getLogin()) != null) {
            return false;
        }
        authorRepository.save(authorEntity);
        return true;
    }

    @Transactional
    public Integer authorLogin(AuthorLoginDto authorLoginDto) {
        AuthorEntity authorEntity = authorRepository.findAuthorEntityByLogin(authorLoginDto.getLogin());
        if (authorEntity != null) {
            String rawPassword = authorLoginDto.getPassword();
            String encodedPassword = authorEntity.getPassword();
            Boolean isPasswordRight = passwordEncoder.matches(rawPassword, encodedPassword);
            if (isPasswordRight) {
                return authorEntity.getId();
            } else {
                return -1;
            }
        } else {
            return -1;
        }
    }

    @Transactional
    public GetAuthorDto getAuthorById(Integer authorId) {
        AuthorEntity authorEntity = authorRepository.findAuthorEntityById(authorId);
        return authorMapper.authorEntityToGetAuthorDto(authorEntity);
    }

    @Transactional
    public Boolean authorDelete(Integer authorId) {
        AuthorEntity authorEntity = authorRepository.findAuthorEntityById(authorId);
        if (authorEntity == null) {
            return false;
        }
        authorRepository.deleteById(authorId);
        return true;
    }

    @Transactional
    public Boolean authorEdit(Integer authorId, AuthorEditDto authorEditDto) {
        AuthorEntity authorEntity = authorRepository.findAuthorEntityById(authorId);
        if (authorEntity == null) {
            return false;
        }
        authorRepository.save(authorMapper.authorEditDtoToAuthorEntity(authorEditDto, authorEntity));
        return true;
    }
}
