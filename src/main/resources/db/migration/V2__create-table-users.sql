CREATE TABLE public.users
(
    id         serial       NOT NULL PRIMARY KEY,
    profile_id integer      NOT NULL,
    username   varchar(100) NOT NULL,
    login      varchar(100) NOT NULL,
    email      varchar(255) NULL,
    "password" varchar(255) NOT NULL,
    active     boolean NULL DEFAULT true,
    created_at timestamp    NOT NULL DEFAULT current_timestamp,
    updated_at timestamp NULL,
    CONSTRAINT fk_profile FOREIGN KEY (profile_id) REFERENCES public.profiles (id) ON DELETE CASCADE
);