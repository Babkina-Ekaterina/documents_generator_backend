package cs.vsu.ru.documents_generator.data.mapper.author;

import cs.vsu.ru.documents_generator.data.dto.author.AuthorEditDto;
import cs.vsu.ru.documents_generator.data.dto.author.AuthorRegisterDto;
import cs.vsu.ru.documents_generator.data.dto.author.GetAuthorDto;
import cs.vsu.ru.documents_generator.data.entity.author.AuthorEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Base64;

@Component
public class AuthorMapper {
    private static final String AES = "AES";
    private static final byte[] SECRET_KEY = System.getenv("AES_SECRET_KEY").getBytes();

    public AuthorMapper(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    private PasswordEncoder passwordEncoder;

    public AuthorEntity authorRegisterDtoToAuthorEntity(AuthorRegisterDto authorRegisterDto) {
        AuthorEntity authorEntity = new AuthorEntity();

        authorEntity.setLogin(authorRegisterDto.getLogin());
        authorEntity.setPassword(passwordEncoder.encode(authorRegisterDto.getPassword()));
        authorEntity.setName(encrypt(authorRegisterDto.getName()));
        authorEntity.setDateOfBirth(encrypt(authorRegisterDto.getDateOfBirth()));
        authorEntity.setAddress(encrypt(authorRegisterDto.getAddress()));
        authorEntity.setCitizenship(encrypt(authorRegisterDto.getCitizenship()));
        authorEntity.setSeries(encrypt(authorRegisterDto.getSeries()));
        authorEntity.setNumber(encrypt(authorRegisterDto.getNumber()));
        authorEntity.setDateOfIssue(encrypt(authorRegisterDto.getDateOfIssue()));
        authorEntity.setIssuedBy(encrypt(authorRegisterDto.getIssuedBy()));

        return authorEntity;
    }

    public GetAuthorDto authorEntityToGetAuthorDto(AuthorEntity authorEntity) {
        GetAuthorDto getAuthorDto = new GetAuthorDto(
                decrypt(authorEntity.getName()),
                decrypt(authorEntity.getDateOfBirth()),
                decrypt(authorEntity.getAddress()),
                decrypt(authorEntity.getCitizenship()),
                decrypt(authorEntity.getSeries()),
                decrypt(authorEntity.getNumber()),
                decrypt(authorEntity.getDateOfIssue()),
                decrypt(authorEntity.getIssuedBy()));

        return getAuthorDto;
    }

    public AuthorEntity authorEditDtoToAuthorEntity(AuthorEditDto authorEditDto, AuthorEntity authorEntity) {
        authorEntity.setName(encrypt(authorEditDto.getName()));
        authorEntity.setDateOfBirth(encrypt(authorEditDto.getDateOfBirth()));
        authorEntity.setAddress(encrypt(authorEditDto.getAddress()));
        authorEntity.setCitizenship(encrypt(authorEditDto.getCitizenship()));
        authorEntity.setSeries(encrypt(authorEditDto.getSeries()));
        authorEntity.setNumber(encrypt(authorEditDto.getNumber()));
        authorEntity.setDateOfIssue(encrypt(authorEditDto.getDateOfIssue()));
        authorEntity.setIssuedBy(encrypt(authorEditDto.getIssuedBy()));
        return authorEntity;
    }

    private static String encrypt(String value) {
        try {
            SecretKey secretKey = new SecretKeySpec(SECRET_KEY, AES);
            Cipher cipher = Cipher.getInstance(AES);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);

            byte[] encryptedValue = cipher.doFinal(value.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(encryptedValue);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String decrypt(String value) {
        try {
            SecretKey secretKey = new SecretKeySpec(SECRET_KEY, AES);
            Cipher cipher = Cipher.getInstance(AES);
            cipher.init(Cipher.DECRYPT_MODE, secretKey);

            byte[] decodedValue = Base64.getDecoder().decode(value);
            byte[] decryptedValue = cipher.doFinal(decodedValue);
            return new String(decryptedValue, StandardCharsets.UTF_8);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static byte[] generateKey() {
        SecureRandom secureRandom = new SecureRandom();
        byte[] key = new byte[16];
        secureRandom.nextBytes(key);
        return key;
    }
}
