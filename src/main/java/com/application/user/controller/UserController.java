package com.application.user.controller;

import com.application.user.dto.UserUpsertDTO;
import com.application.user.dto.UserDTO;
import com.application.user.entities.User;
import com.application.user.service.IUserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
/**
 * Prueba técnica desarrollo de una Api Restful para el registro de usuarios con JWT
 * para optar al cargo de Desarrollador Backend.
 * Fecha: Agosto 2024
 * @author Yenny Chipamo
 * @version 1.0
 */
@Slf4j
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @Autowired
    private ModelMapper modelMapper;

    /**
     * Método: Agregar usuarios
     * @RequestBody Parámetro de entrada: un json
     * @return El UserUpsertDTO con el usuario creado
     */
    @PostMapping("/create")
    public ResponseEntity<UserUpsertDTO> createUser(@Valid @RequestBody UserUpsertDTO userUpsertDTO) throws Exception {

        User userCreate = userService.save(User.builder()
                .id(userUpsertDTO.getId())
                .name(userUpsertDTO.getName())
                .email(userUpsertDTO.getEmail())
                .isActive(userUpsertDTO.getIsActive())
                .password(userUpsertDTO.getPassword())
                .phones(userUpsertDTO.getPhones())
                .token(userUpsertDTO.getToken())
                .build()
        );

        UserUpsertDTO userCreatedDTO = UserUpsertDTO.builder()
                .id(userCreate.getId())
                .name(userCreate.getName())
                .email(userCreate.getEmail())
                .created(userCreate.getCreated())
                .modified(userCreate.getModified())
                .lastLogin(userCreate.getLastLogin())
                .isActive(userCreate.getIsActive())
                .password(userCreate.getPassword())
                .phones(userCreate.getPhones())
                .token(userCreate.getToken())
                .build();

        return ResponseEntity.created(new URI("api/user/create")).body(userCreatedDTO);
    }

    /**
     * Método: Actualiza usuarios
     * @PathVariable Parámetro de entrada: el id de un usuario
     * @RequestBody Parámetro de entrada: un json
     * @return El DTO del usuario actualizado
     */
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<UserUpsertDTO> updateUser(@PathVariable UUID id, @RequestBody UserUpsertDTO userUpsertDTO) throws Exception {

        Optional<User> userOptional = userService.findById(id);

        if (userOptional.isPresent()) {

            User user = userOptional.get();

            user.setName(userUpsertDTO.getName());
            user.setEmail(userUpsertDTO.getEmail());
            user.setIsActive(userUpsertDTO.getIsActive());
            user.setPassword(userUpsertDTO.getPassword());
            user.setPhones(userUpsertDTO.getPhones());

            User updatedUser = userService.save(user);

            UserUpsertDTO userUpdatedDTO = modelMapper.map(updatedUser, UserUpsertDTO.class);

            return ResponseEntity.ok().body(userUpdatedDTO);
        }
        return ResponseEntity.notFound().build();
    }

    /**
     * Método: Listar todos los usuarios, es importante destacar que no se visualizan las fechas de control ni los password ni los tokens
     * @return Lista DTO con los usuarios registrados en Base de Datos
     */
    @GetMapping("/list")
    public ResponseEntity<List<UserDTO>> findByAllUsers() {

        List<UserDTO> userDTOList = userService.findAll()
                .stream()
                .map(user -> UserDTO.builder()
                        .id(user.getId())
                        .name(user.getName())
                        .email(user.getEmail())
                        .created(user.getCreated())
                        .modified(user.getModified())
                        .lastLogin(user.getLastLogin())
                        .isActive(user.getIsActive())
                        .phones(user.getPhones())
                        .build()
                ).toList();

        return ResponseEntity.ok(userDTOList);
    }

    /**
     * Método: Buscar un usuario
     * @PathVariable Parámetro de entrada: el id de un usuario
     * @return Busca un usuario existente en la Base de Datos
     */
    @GetMapping("/find/{id}")
    public ResponseEntity<Optional<UserDTO>> findByIdUser(@PathVariable UUID id) {
        Optional<User> userOptional = userService.findById(id);

        if(userOptional.isPresent()) {
            User user = userOptional.get();

            UserDTO userDTO = UserDTO.builder()
                    .id(userOptional.get().getId())
                    .name(userOptional.get().getName())
                    .email(userOptional.get().getEmail())
                    .created(userOptional.get().getCreated())
                    .modified(userOptional.get().getModified())
                    .lastLogin(userOptional.get().getLastLogin())
                    .isActive(userOptional.get().getIsActive())
                    .phones(userOptional.get().getPhones())
                    .build();

            return ResponseEntity.ok(Optional.ofNullable(userDTO));
        }
        return ResponseEntity.notFound().build();
    }

    /**
     * Método: Eliminar usuarios
     * @PathVariable Parámetro de entrada: el id de un usuario
     * @return Mensaje de usuario Eliminado.
     */
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> deleteByIdUser (@PathVariable UUID id) {
        Optional<User> userOptional = userService.findById(id);

        if (userOptional.isPresent()) {
            userService.deleteById(id);;
            return ResponseEntity.ok("Usuario Eliminado");
        }

        return ResponseEntity.notFound().build();
    }

    /**
     * Método: Generar Token
     * @PathVariable Parámetro de entrada: el id de un usuario
     * @return el nuevo token para el usuario
     */
    @PutMapping("/token/{id}")
    public ResponseEntity<String> generateTokenById(@PathVariable UUID id) throws Exception {

        try {
            String token = userService.generateTokenById(id);
            return ResponseEntity.ok(token);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }

    }

}
