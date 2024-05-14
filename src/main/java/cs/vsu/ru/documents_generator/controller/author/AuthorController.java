package cs.vsu.ru.documents_generator.controller.author;

import cs.vsu.ru.documents_generator.data.dto.author.AuthorEditDto;
import cs.vsu.ru.documents_generator.data.dto.author.AuthorLoginDto;
import cs.vsu.ru.documents_generator.data.dto.author.AuthorRegisterDto;
import cs.vsu.ru.documents_generator.data.dto.author.GetAuthorDto;
import cs.vsu.ru.documents_generator.service.author.AuthorService;
import lombok.AllArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/author")
@AllArgsConstructor
@CrossOrigin(origins = {"https://documents-generator-frontend.onrender.com"}, allowCredentials = "true")
public class AuthorController {
    private AuthorService authorService;

    @PostMapping("/register")
    public ResponseEntity<?> authorRegister(@RequestBody AuthorRegisterDto authorRegisterDto) {
        if (authorService.authorRegister(authorRegisterDto)) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<Integer> authorLogin(@RequestBody AuthorLoginDto authorLoginDto) {
        int authorId = authorService.authorLogin(authorLoginDto);
        if (authorId != -1) {
            return new ResponseEntity<>(authorId, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(authorId, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get_author")
    public ResponseEntity<GetAuthorDto> getAuthorById(@RequestParam Integer authorId) {
        return new ResponseEntity<>(authorService.getAuthorById(authorId), HttpStatus.OK);
    }

    @GetMapping("/delete")
    public ResponseEntity<?> authorDelete(@RequestParam Integer authorId) {
        if (authorService.authorDelete(authorId)) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/edit")
    public ResponseEntity<?> authorEdit(@RequestParam Integer authorId, @RequestBody AuthorEditDto authorEditDto) {
        if (authorService.authorEdit(authorId, authorEditDto)) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
