CREATE TABLE public.profiles
(
    id          serial       NOT NULL PRIMARY KEY,
    name        varchar(100) NOT NULL,
    description varchar(255) NOT NULL,
    acronym     varchar(50)  NOT NULL,
    active      boolean      NULL     DEFAULT true,
    created_at  timestamp    NOT NULL DEFAULT current_timestamp,
    updated_at  timestamp    NULL
);