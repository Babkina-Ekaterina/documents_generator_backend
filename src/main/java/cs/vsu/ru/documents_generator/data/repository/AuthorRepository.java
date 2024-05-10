package cs.vsu.ru.documents_generator.data.repository;

import cs.vsu.ru.documents_generator.data.entity.author.AuthorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@EnableJpaRepositories
@Repository
public interface AuthorRepository extends JpaRepository<AuthorEntity,Integer> {
    AuthorEntity findAuthorEntityByLogin(String login);
    AuthorEntity findAuthorEntityById(Integer id);
}
