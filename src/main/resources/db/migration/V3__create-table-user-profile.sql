CREATE TABLE public.user_profile
(
    id         serial    NOT NULL PRIMARY KEY,
    user_id    integer   NOT NULL UNIQUE,
    profile_id integer   NOT NULL,
    created_at timestamp NOT NULL DEFAULT current_timestamp,
    updated_at timestamp NULL,
    CONSTRAINT fk_user
        FOREIGN KEY (user_id)
            REFERENCES users (id)
            ON DELETE CASCADE,
    CONSTRAINT fk_profile
        FOREIGN KEY (profile_id)
            REFERENCES profiles (id)
            ON DELETE CASCADE
);