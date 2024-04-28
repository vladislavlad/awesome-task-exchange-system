create table users
(
    id          bigserial primary key not null,
    uuid        uuid unique           not null,
    username    varchar unique        not null,
    first_name  varchar,
    last_name   varchar,
    middle_name varchar,
    deleted_at  timestamptz default null,
    deleted_by  uuid        default null
);

create table user_roles
(
    id      bigserial primary key not null,
    user_id bigint                not null references users (id),
    role    varchar               not null
);

create unique index uq__user_roles__user_id__role on user_roles (user_id, role);

create table tasks
(
    id          bigserial primary key not null,
    uuid        uuid unique           not null,
    title       varchar               not null,
    description varchar               not null,
    status      varchar               not null,
    user_uuid   uuid                  not null references users (uuid)
);
