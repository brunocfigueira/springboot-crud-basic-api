CREATE TABLE public.permissions
(
    id          serial       NOT NULL PRIMARY KEY,
    name        varchar(100) NOT NULL,
    description varchar(255) NOT NULL,
    created_at  timestamp    NOT NULL DEFAULT current_timestamp,
    updated_at  timestamp    NULL
);