-- H2 2.2.224;
;             
CREATE USER IF NOT EXISTS "SA" SALT '070443e1625040d6' HASH 'fe143fd7f66e4b4cd1fdb39600d76d8cf498022ff00a68d43b5e235e6dd5c355' ADMIN;         
CREATE MEMORY TABLE "PUBLIC"."PHONES"(
    "ID" BIGINT GENERATED BY DEFAULT AS IDENTITY(START WITH 1 RESTART WITH 4) NOT NULL,
    "CITYCODE" CHARACTER VARYING(255),
    "COUNTRYCODE" CHARACTER VARYING(255),
    "NUMBER" CHARACTER VARYING(255)
);   
ALTER TABLE "PUBLIC"."PHONES" ADD CONSTRAINT "PUBLIC"."CONSTRAINT_8" PRIMARY KEY("ID");       
-- 3 +/- SELECT COUNT(*) FROM PUBLIC.PHONES;  
INSERT INTO "PUBLIC"."PHONES" VALUES
(1, '01', '56', '555-1234'),
(2, '02', '56', '444-1234'),
(3, '01', '59', '444-7894');
CREATE MEMORY TABLE "PUBLIC"."USERS"(
    "IS_ACTIVE" BOOLEAN,
    "CREATED" TIMESTAMP(6),
    "LAST_LOGIN" TIMESTAMP(6),
    "MODIFIED" TIMESTAMP(6),
    "ID" UUID NOT NULL,
    "EMAIL" CHARACTER VARYING(255),
    "NAME" CHARACTER VARYING(255),
    "PASSWORD" CHARACTER VARYING(255),
    "TOKEN" CHARACTER VARYING(255)
);  
ALTER TABLE "PUBLIC"."USERS" ADD CONSTRAINT "PUBLIC"."CONSTRAINT_4D" PRIMARY KEY("ID");       
-- 2 +/- SELECT COUNT(*) FROM PUBLIC.USERS;   
INSERT INTO "PUBLIC"."USERS" VALUES
(TRUE, TIMESTAMP '2024-08-26 07:23:00', TIMESTAMP '2024-08-29 05:53:00', TIMESTAMP '2024-08-27 09:03:00', UUID '3f5f2bf9-8635-47c6-acc8-eb676149d0d6', 'pepe.perez@empresa.cl', 'Pepe Perez', 'AsdfE12&', 'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJwZXBlLnBlcmV6QGVtcHJlc2EuY2wiLCJpYXQiOjE3MjQ5NTEzNTQsImV4cCI6MTcyNDk1NDk1NH0.GOd2P759zSZGpUI4aX3xrFc_FsL4B3zI9rngOGMHPTymKAwrjXoQUBZguzUJyBUvRNcBl9_7ZsorDgWK-wmwrg'),
(FALSE, TIMESTAMP '2024-08-26 14:50:00', TIMESTAMP '2024-08-29 07:53:00', TIMESTAMP '2024-08-27 10:53:00', UUID '0033f9e3-f792-4b0b-a6dd-fd57f6ed7c7a', 'paco.mora@empresa.com', 'Paco Mora', 'Se&dfE12', 'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJwZXBlLnBlcmV6QGVtcHJlc2EuY2wiLCJpYXQiOjE3MjQ5NTEzNDEsImV4cCI6MTcyNDk1NDk0MX0.89J-dyeKwouDsJJ5ovt-dW-neKfGIWeGshuBzdj289E8I86_XCE6j21hu7kwL8wjavj1cXke4a_TlxurfJJp1w');     
CREATE MEMORY TABLE "PUBLIC"."USERS_PHONES"(
    "PHONES_ID" BIGINT NOT NULL,
    "USER_ID" UUID NOT NULL
);               
-- 3 +/- SELECT COUNT(*) FROM PUBLIC.USERS_PHONES;            
INSERT INTO "PUBLIC"."USERS_PHONES" VALUES
(1, UUID '3f5f2bf9-8635-47c6-acc8-eb676149d0d6'),
(2, UUID '3f5f2bf9-8635-47c6-acc8-eb676149d0d6'),
(3, UUID '0033f9e3-f792-4b0b-a6dd-fd57f6ed7c7a');           
ALTER TABLE "PUBLIC"."USERS" ADD CONSTRAINT "PUBLIC"."CONSTRAINT_4" UNIQUE("EMAIL");          
ALTER TABLE "PUBLIC"."USERS_PHONES" ADD CONSTRAINT "PUBLIC"."CONSTRAINT_5" UNIQUE("PHONES_ID");               
ALTER TABLE "PUBLIC"."USERS_PHONES" ADD CONSTRAINT "PUBLIC"."FK36DPKFHW8EHRYMJANEBPNKGML" FOREIGN KEY("USER_ID") REFERENCES "PUBLIC"."USERS"("ID") NOCHECK;   
ALTER TABLE "PUBLIC"."USERS_PHONES" ADD CONSTRAINT "PUBLIC"."FK2H8LRW8NCHQT19WJ7LQGDJFPO" FOREIGN KEY("PHONES_ID") REFERENCES "PUBLIC"."PHONES"("ID") NOCHECK;
