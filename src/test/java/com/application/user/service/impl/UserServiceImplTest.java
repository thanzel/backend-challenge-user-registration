package com.application.user.service.impl;

import com.application.user.DataUsersMock;
import com.application.user.configuration.SecureKeyGenerator;
import com.application.user.entities.User;
import com.application.user.persistence.IUserDAO;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.crypto.SecretKey;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private IUserDAO iUserDAO;

    @Mock
    private SecureKeyGenerator secureKeyGenerator;

    @InjectMocks
    private UserServiceImpl userService;

    private User user;
    private static final UUID ID = UUID.fromString("0033f9e3-f792-4b0b-a6dd-fd57f6ed7c7a");;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy/MM/dd'T'HH:mm:ss");

    @BeforeEach
    void setUp() {
        user = new User();
        user.setEmail("test@dominio.cl");
        user.setToken("eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJwZXBlLnBlcmV6Qlc2EuY2wiL.505kgUGjzHA1L3Kl8_PBBtw_7Qup1Xhyn_h9h_VjtNJz1mFG8Ley36w");
    }

    @Test
    void testFindAllIsOk() {

        when(iUserDAO.findAll()).thenReturn(DataUsersMock.listTasksMock());
        List<User> result = userService.findAll();

        assertFalse(result.isEmpty());
        assertNotNull(result);
        assertEquals(4,result.size());

        assertEquals(ID, result.get(0).getId());
        assertEquals("Julio Print", result.get(0).getName());
        assertEquals("julioprint@dominio.cl", result.get(0).getEmail());
        assertEquals("AsdfE12&", result.get(0).getPassword());
        assertTrue(result.get(0).getIsActive());
        assertEquals(LocalDateTime.parse("2024/08/20T14:30:00", FORMATTER), result.get(0).getCreated());
        assertEquals(LocalDateTime.parse("2024/08/20T14:30:00", FORMATTER), result.get(0).getModified());
        assertEquals(LocalDateTime.parse("2024/08/20T07:30:00", FORMATTER), result.get(0).getLastLogin());

        assertEquals("Santis Jus", result.get(3).getName());
        assertEquals("sanjus@dominio.cl", result.get(3).getEmail());
        assertEquals("ksdDE12&", result.get(3).getPassword());
        assertFalse(result.get(3).getIsActive());
    }

    @Test
    void testFindByIdIsOk() {

        when(this.iUserDAO.findById( any() )).thenReturn(Optional.of(DataUsersMock.findUserMock()));

        Optional<User> result = this.userService.findById(ID);

        verify(this.iUserDAO).findById( any() );
        assertEquals(ID, result.get().getId());
        assertEquals("Julio Print", result.get().getName());
        assertEquals("julioprint@dominio.cl", result.get().getEmail());
        assertEquals("AsdfE12&", result.get().getPassword());
        assertTrue(result.get().getIsActive());
        assertEquals(LocalDateTime.parse("2024/08/20T14:30:00", FORMATTER), result.get().getCreated());
        assertEquals(LocalDateTime.parse("2024/08/20T14:30:00", FORMATTER), result.get().getModified());
        assertEquals(LocalDateTime.parse("2024/08/20T07:30:00", FORMATTER), result.get().getLastLogin());
    }

    @Test
    @SneakyThrows
    void testCreateNewUserIsOk() {

        when(iUserDAO.findByEmail( anyString() )).thenReturn( Optional.empty());
        SecretKey secureKey = Keys.secretKeyFor(SignatureAlgorithm.HS512);
        when(secureKeyGenerator.generateKey()).thenReturn(Base64.getEncoder().encodeToString(secureKey.getEncoded()));
        when(iUserDAO.save(any(User.class))).thenReturn(DataUsersMock.newUserMock());

        User result = DataUsersMock.newUserMock();
        this.userService.save(result);

        assertNotNull(result);
        assertNotNull(result.getToken());
        verify(iUserDAO).findByEmail(result.getEmail());
        verify(iUserDAO).save(result);

    }

    @Test
    void testSaveWithDuplicateEmailThenError() {

        when(iUserDAO.findByEmail( anyString() )).thenReturn(Optional.of(user));

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            userService.save(user);
        });

        assertEquals("El email ya está registrado. No se puede repetir", exception.getMessage());
        verify(iUserDAO, times(1)).findByEmail(user.getEmail());
        verify(iUserDAO, never()).save(any(User.class));
    }

    @Test
    void testDeleteByIdIsOk() {

        doNothing().when(iUserDAO).deleteById(ID);

        userService.deleteById(ID);

        verify(iUserDAO, times(1)).deleteById(ID);
    }

    @Test
    void testFindByEmailIsOk() {

        when(iUserDAO.findByEmail( anyString() )).thenReturn(Optional.of(user));

        Optional<User> result = userService.findByEmail(user.getEmail());

        assertTrue(result.isPresent());
        assertEquals(user.getEmail(), result.get().getEmail());
        verify(iUserDAO, times(1)).findByEmail(user.getEmail());

    }

    @Test
    @SneakyThrows
    void testGenerateTokenByIdIsGenerated() {

        when(iUserDAO.findById(ID)).thenReturn(Optional.of(user));

        SecretKey secureKey = Keys.secretKeyFor(SignatureAlgorithm.HS512);
        when(secureKeyGenerator.generateKey()).thenReturn(Base64.getEncoder().encodeToString(secureKey.getEncoded()));

        when(iUserDAO.save(any(User.class))).thenReturn(user);

        String token = userService.generateTokenById(ID);

        assertNotNull(token);
        assertEquals(user.getToken(), token);
        verify(iUserDAO, times(1)).findById(ID);
        verify(iUserDAO, times(1)).save(user);
    }

    @Test
    void testGenerateTokenByIdIsNotGenerated() {

        when(iUserDAO.findById( any())).thenReturn(Optional.empty());

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            userService.generateTokenById(ID);
        });

        assertEquals("Usuario no encontrado", exception.getMessage());
        verify(iUserDAO, times(1)).findById(ID);
        verify(iUserDAO, never()).save(any(User.class));
    }
}