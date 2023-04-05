CREATE TABLE public.profile_permission
(
    id            serial    NOT NULL PRIMARY KEY,
    profile_id    integer   NOT NULL,
    permission_id integer   NOT NULL,
    CONSTRAINT fk_profile_permission FOREIGN KEY (profile_id) REFERENCES public.profiles (id) ON DELETE CASCADE,
    CONSTRAINT fk_permission_profile FOREIGN KEY (permission_id) REFERENCES public.permissions (id) ON DELETE CASCADE
);