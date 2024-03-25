create table users
(
    id          bigserial primary key not null,
    uuid        uuid unique           not null,
    username    varchar unique        not null,
    first_name  varchar,
    last_name   varchar,
    middle_name varchar
);

create index ix__users__uuid on users (uuid);

create table user_roles
(
    id      bigserial primary key not null,
    user_id bigint                not null references users (id),
    role    varchar               not null
);

create unique index uq__user_roles__user_id__role on user_roles (user_id, role);

create table accounts
(
    id          bigserial primary key not null,
    uuid        uuid unique           not null,
    type        varchar               not null,
    description varchar,
    status      varchar               not null,
    user_id     bigint references users (id)
);

create unique index uq__accounts__user_id on accounts (user_id);

create table tasks
(
    id          bigserial primary key not null,
    uuid        uuid unique           not null,
    title       varchar               not null,
    assign_cost int                   not null,
    reward      int                   not null,
    user_uuid   uuid references users (uuid)
);

create table transactions
(
    id           bigserial primary key not null,
    public_id    varchar               not null,
    type         varchar               not null,
    account_uuid uuid                  not null,
    debit        numeric               not null,
    credit       numeric               not null,
    completed_at timestamp             not null,
    created_at   timestamp             not null
);
