package com.application.user;

import com.application.user.entities.Phone;
import com.application.user.entities.User;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

public class DataUsersMock {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy/MM/dd'T'HH:mm:ss");
    public static List<User> listTasksMock(){

        return List.of(
                new User( UUID.fromString("0033f9e3-f792-4b0b-a6dd-fd57f6ed7c7a"),"Julio Print","julioprint@dominio.cl","AsdfE12&", LocalDateTime.parse("2024/08/20T14:30:00", FORMATTER), LocalDateTime.parse("2024/08/20T14:30:00", FORMATTER), LocalDateTime.parse("2024/08/20T07:30:00", FORMATTER), "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJwZXBlLnBlcmV6QGVtcHJlc2EuY2wiLCJpYXQiOjE3MjQ5NTEzOTgsImV4cCI6MTcyNDk1NDk5OH0.KKlstLWIw0rwo0UU2UkvXnK6B5uufUPQlpAIblUlqWVLkyrjSR6040Fp9TPTtVewpT40lwEtvlkFxMj9mHfHbg",true, listPhoneMock() ),
                new User( UUID.fromString("0033f9e3-f792-4b0b-ff7d-fd57f6ed7c7a"),"Lola Barrios","lola@dominio.cl","GydfE12&", LocalDateTime.parse("2024/08/20T14:30:00", FORMATTER), LocalDateTime.parse("2024/08/27T07:30:00", FORMATTER), LocalDateTime.parse("2024/08/21T07:30:00", FORMATTER), "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJwZXBlLnBlcmV6QGVtcHJlc2EuY2wiLCJpYXQiOjE3MjQ5NTE0NDEsImV4cCI6MTcyNDk1NTA0MX0.Fd0SHgctSJxqPLl9oJiOSnue37RguUV4Vvl93QQYfv8EwC2t3DKgPBjdneNEndhCik71jSeQd_H1TiVKExrT6A",false , listPhoneMock()),
                new User( UUID.fromString("0033f9e3-f792-4b0b-a6dd-fd57f6ed8c7a"),"Pablo Morell","moreelp@dominio.cl","pRdfE12&", LocalDateTime.parse("2024/08/26T07:33:00", FORMATTER), LocalDateTime.parse("2024/08/28T07:30:00", FORMATTER), LocalDateTime.parse("2024/08/22T07:40:00", FORMATTER), "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJwZXBlLnBlcmV6QGVtcHJlc2EuY2wiLCJpYXQiOjE3MjQ5NTE0NTksImV4cCI6MTcyNDk1NTA1OX0.3Xb5UC0V0DIDODEsg4nFpYfr2nQre0qXg7k0BxTvJeFOgkgwdSa2QKeOLX7FjAShmbZdkqHx7mMOopy4doSCTg",true , listPhoneMock()),
                new User( UUID.fromString("0033f9e3-f792-4b0b-a6dd-fd57f7ed7c7a"),"Santis Jus","sanjus@dominio.cl","ksdDE12&", LocalDateTime.parse("2024/08/25T07:33:00", FORMATTER), LocalDateTime.parse("2024/08/29T07:30:00", FORMATTER), LocalDateTime.parse("2024/08/23T07:40:00", FORMATTER), "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJwZXBlLnBlcmV6QGVtcHJlc2EuY2wiLCJpYXQiOjE3MjQ5NTE0NzcsImV4cCI6MTcyNDk1NTA3N30.9BOLSSBTSO25u2isXNN9abkzfO6MT_5Qlba1Znv8hGE9OxQT7jumC0QCqcuOr0G3vgfxPUOdrL1ex1jQbiATTQ",false , listPhoneMock())
        );

    }

    public static User findUserMock () {
        return new User( UUID.fromString("0033f9e3-f792-4b0b-a6dd-fd57f6ed7c7a"),"Julio Print","julioprint@dominio.cl","AsdfE12&", LocalDateTime.parse("2024/08/20T14:30:00", FORMATTER), LocalDateTime.parse("2024/08/20T14:30:00", FORMATTER), LocalDateTime.parse("2024/08/20T07:30:00", FORMATTER), "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJwZXBlLnBlcmV6QGVtcHJlc2EuY2wiLCJpYXQiOjE3MjQ4ODQ1MzcsImV4cCI6MTcyNDg4ODEzN30",true, listPhoneMock() );
    }

    public static User newUserMock () {
        return new User( UUID.fromString("0033f9e3-f792-4b0b-ff9d-fd57f6ed7c7a"),"Neils Amstrong","holaneils@dominio.cl","GyrtJ42&", LocalDateTime.parse("2024/08/28T14:30:00", FORMATTER), LocalDateTime.parse("2024/08/27T07:30:00", FORMATTER), LocalDateTime.parse("2024/08/21T07:30:00", FORMATTER), "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJwZXBlLnBlcmV6QGVtcHJlc2EuY2wiLCJpYXQiOjE3MjQ4OD.5kgUGjzHA1L3Kl8_PBBtw_7QupoSEW31Xhyn_h9h_VjtNJz1mFG8Ley36w",true , listPhoneMock());
    }

    public static List<Phone> listPhoneMock() {
        return List.of(
                new Phone(1L,"333-4444","01","56"),
                new Phone(2L,"432-0984","02","59")
        );
    }
}
