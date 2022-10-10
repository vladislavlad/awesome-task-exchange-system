create table users
(
    id          bigserial primary key not null,
    uuid        uuid                  not null,
    username    varchar               not null,
    first_name  varchar,
    last_name   varchar,
    middle_name varchar
);

create table tasks
(
    id          bigserial primary key not null,
    uuid        uuid                  not null,
    title       varchar               not null,
    description varchar               not null,
    status      varchar               not null,
    user_id     bigint                not null references users (id)
);
