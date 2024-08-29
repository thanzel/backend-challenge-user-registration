package com.application.user.controller;

import com.application.user.DataUsersMock;
import com.application.user.configuration.TestConfig;
import com.application.user.entities.User;
import com.application.user.service.IUserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(UserController.class)
@Import(TestConfig.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IUserService userService;

    @Mock
    private ModelMapper modelMapperTest;

    private ObjectMapper objectMapper = new ObjectMapper();

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd'T'HH:mm:ss");
    private UUID id;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @SneakyThrows
    void testCreateUserReturnOk() {

        String bodyJsonUserCreate = "{"
                + "\"name\":\"Neils Amstrong\","
                + "\"email\":\"holaneils@dominio.cl\","
                + "\"password\":\"GyrtJ42&\","
                + "\"phones\":["
                + "    {"
                + "        \"id\":1,"
                + "        \"number\":\"333-4444\","
                + "        \"citycode\":\"01\","
                + "        \"countrycode\":\"56\""
                + "    },"
                + "    {"
                + "        \"id\":2,"
                + "        \"number\":\"432-0984\","
                + "        \"citycode\":\"02\","
                + "        \"countrycode\":\"59\""
                + "    }"
                + "]"
                + "}";


                String bodyJsonUserCreated = "{"
                + "\"id\":\"0033f9e3-f792-4b0b-ff9d-fd57f6ed7c7a\","
                + "\"name\":\"Neils Amstrong\","
                + "\"email\":\"holaneils@dominio.cl\","
                + "\"created\":\"2024-08-28T14:30:00\","
                + "\"modified\":\"2024-08-27T07:30:00\","
                + "\"lastLogin\":\"2024-08-21T07:30:00\","
                + "\"isActive\":true,"
                + "\"phones\":["
                + "    {"
                + "        \"id\":1,"
                + "        \"number\":\"333-4444\","
                + "        \"citycode\":\"01\","
                + "        \"countrycode\":\"56\""
                + "    },"
                + "    {"
                + "        \"id\":2,"
                + "        \"number\":\"432-0984\","
                + "        \"citycode\":\"02\","
                + "        \"countrycode\":\"59\""
                + "    }"
                + "],"
                + "\"password\":\"GyrtJ42&\","
                + "\"token\":\"eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJwZXBlLnBlcmV6QGVtcHJlc2EuY2wiLCJpYXQiOjE3MjQ4OD.5kgUGjzHA1L3Kl8_PBBtw_7QupoSEW31Xhyn_h9h_VjtNJz1mFG8Ley36w\""
                + "}";

        User userCreated = DataUsersMock.newUserMock();

        when(this.userService.save( any( User.class))).thenReturn( userCreated);

        mockMvc.perform(post("/api/user/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(bodyJsonUserCreate.toString()))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", "api/user/create"))
                .andExpect(content().json(bodyJsonUserCreated));

    }

    @Test
    @SneakyThrows
    void testUpdateUserReturnOk() {
        id = UUID.fromString("0033f9e3-f792-4b0b-a6dd-fd57f6ed7c7a");

        String bodyJsonUserUpdate = "{"
                + "\"name\":\"Neils Amstrong Updated\","
                + "\"email\":\"holaneilsupdated@dominio.cl\","
                + "\"isActive\":\"false\","
                + "\"password\":\"GyrtJ42&\","
                + "\"phones\":["
                + "    {"
                + "        \"id\":1,"
                + "        \"number\":\"333-4444\","
                + "        \"citycode\":\"01\","
                + "        \"countrycode\":\"56\""
                + "    },"
                + "    {"
                + "        \"id\":2,"
                + "        \"number\":\"432-0984\","
                + "        \"citycode\":\"02\","
                + "        \"countrycode\":\"59\""
                + "    }"
                + "],"
                + "\"isActive\":true"
                + "}";

        String bodyJsonUserUpdated = "{"
                + "\"id\":\"0033f9e3-f792-4b0b-ff9d-fd57f6ed7c7a\","
                + "\"name\":\"Neils Amstrong Updated\","
                + "\"email\":\"holaneilsupdated@dominio.cl\","
                + "\"created\":\"2024-08-28T14:30:00\","
                + "\"modified\":\"2024-08-27T07:30:00\","
                + "\"lastLogin\":\"2024-08-21T07:30:00\","
                + "\"isActive\":true,"
                + "\"phones\":["
                + "    {"
                + "        \"id\":1,"
                + "        \"number\":\"333-4444\","
                + "        \"citycode\":\"01\","
                + "        \"countrycode\":\"56\""
                + "    },"
                + "    {"
                + "        \"id\":2,"
                + "        \"number\":\"432-0984\","
                + "        \"citycode\":\"02\","
                + "        \"countrycode\":\"59\""
                + "    }"
                + "],"
                + "\"password\":\"GyrtJ42&\","
                + "\"token\":\"eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJwZXBlLnBlcmV6QGVtcHJlc2EuY2wiLCJpYXQiOjE3MjQ4OD.5kgUGjzHA1L3Kl8_PBBtw_7QupoSEW31Xhyn_h9h_VjtNJz1mFG8Ley36w\""
                + "}";

        User updatedUser = DataUsersMock.newUserMock();
        updatedUser.setName("Neils Amstrong Updated");
        updatedUser.setEmail("holaneilsupdated@dominio.cl");
        updatedUser.setIsActive(false);

        when(this.userService.findById(any())).thenReturn(Optional.of(updatedUser));
        when(this.userService.save(any(User.class))).thenReturn(updatedUser);


        mockMvc.perform(put("/api/user/actualizar/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(bodyJsonUserUpdate))
                .andExpect(status().isOk())
                .andExpect(content().json(bodyJsonUserUpdated));
    }

    @Test
    @SneakyThrows
    void testFindByAllUsersReturnOk() {

        when(userService.findAll()).thenReturn(DataUsersMock.listTasksMock());

        mockMvc.perform(get("/api/user/list"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value("0033f9e3-f792-4b0b-a6dd-fd57f6ed7c7a"))
                .andExpect(jsonPath("$[0].name").value("Julio Print"))
                .andExpect(jsonPath("$[0].email").value("julioprint@dominio.cl"))
                .andExpect(jsonPath("$[0].created").value("2024-08-20T14:30:00"))
                .andExpect(jsonPath("$[0].modified").value("2024-08-20T14:30:00"))
                .andExpect(jsonPath("$[0].lastLogin").value("2024-08-20T07:30:00"))
                .andExpect(jsonPath("$[0].isActive").value(true))
                .andExpect(jsonPath("$[0].phones[0].number").value("333-4444"))
                .andExpect(jsonPath("$[0].phones[1].number").value("432-0984"))

                .andExpect(jsonPath("$[3].id").value("0033f9e3-f792-4b0b-a6dd-fd57f7ed7c7a"))
                .andExpect(jsonPath("$[3].name").value("Santis Jus"))
                .andExpect(jsonPath("$[3].email").value("sanjus@dominio.cl"))
                .andExpect(jsonPath("$[3].created").value("2024-08-25T07:33:00"))
                .andExpect(jsonPath("$[3].modified").value("2024-08-29T07:30:00"))
                .andExpect(jsonPath("$[3].lastLogin").value("2024-08-23T07:40:00"))
                .andExpect(jsonPath("$[3].isActive").value(false))
                .andExpect(jsonPath("$[3].phones[0].number").value("333-4444"))
                .andExpect(jsonPath("$[3].phones[1].number").value("432-0984"));
    }

    @Test
    @SneakyThrows
    void testFindByIdUserIsFound() {
        id = UUID.fromString("0033f9e3-f792-4b0b-a6dd-fd57f6ed7c7a");

        when(userService.findById( any() )).thenReturn(Optional.of(DataUsersMock.findUserMock()));

        mockMvc.perform(get("/api/user/find/{id}",id))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value("0033f9e3-f792-4b0b-a6dd-fd57f6ed7c7a"))
                .andExpect(jsonPath("$.name").value("Julio Print"))
                .andExpect(jsonPath("$.email").value("julioprint@dominio.cl"))
                .andExpect(jsonPath("$.created").value("2024-08-20T14:30:00"))
                .andExpect(jsonPath("$.modified").value("2024-08-20T14:30:00"))
                .andExpect(jsonPath("$.lastLogin").value("2024-08-20T07:30:00"))
                .andExpect(jsonPath("$.isActive").value(true))
                .andExpect(jsonPath("$.phones[0].number").value("333-4444"))
                .andExpect(jsonPath("$.phones[1].number").value("432-0984"));
    }


    @Test
    @SneakyThrows
    void testFindByIdUserIsNotFound() {

        when(userService.findById( any() )).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/user/find/{id}",id))
                .andDo(print())
                .andExpect(status().isNotFound());

    }

    @Test
    @SneakyThrows
    void testDeleteByIdUserIsDeleted() {

        id = UUID.fromString("0033f9e3-f792-4b0b-a6dd-fd57f6ed7c7a");

        doNothing().when(userService).deleteById( any() );

        User userExist = DataUsersMock.findUserMock();
        when(userService.findById( any())).thenReturn(Optional.of(userExist));
        mockMvc.perform(delete("/api/user/eliminar/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("Usuario Eliminado"));

        verify(userService).deleteById(id);
    }

    @Test
    @SneakyThrows
    void testDeleteByIdUserIsNotOk() {
        id = UUID.fromString("0033f9e3-f792-4b0b-a6dd-fd57f6ed7c7a");

        when(userService.findById( any())).thenReturn( Optional.empty());
        doNothing().when(userService).deleteById( any() );

        mockMvc.perform(delete("/api/user/eliminar/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    @SneakyThrows
    void testGenerateTokenByIdIsGenerated() {
        id = UUID.fromString("0033f9e3-f792-4b0b-a6dd-fd57f6ed7c7a");
        String expectedToken = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJwZXBlLnBlcmV6QGVtcHJlc2EuY2wiLCJpYXQiOjE3MjQ4ODQ1MzcsImV4cCI6MTcyNDg4ODEzN30";

        when(userService.generateTokenById(any())).thenReturn(expectedToken);

        mockMvc.perform(put("/api/user/token/{id}", id))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_PLAIN))
                .andExpect(content().string(expectedToken));
    }

    @Test
    @SneakyThrows
    void testGenerateTokenByIdIsNotGenerated() {

        id = UUID.fromString("0033f9e3-f792-4b0b-a6dd-fd57f6ed7c7a");

        when(userService.generateTokenById(any())).thenThrow(new IllegalArgumentException("Usuario no encontrado"));

        mockMvc.perform(put("/api/user/token/{id}", id)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(content().string(""));
    }
}
