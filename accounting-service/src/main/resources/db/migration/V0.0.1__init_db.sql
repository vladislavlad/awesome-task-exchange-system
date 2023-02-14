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

create table billing_cycles
(
    id             bigserial primary key not null,
    status         varchar               not null,
    start_datetime timestamptz           not null,
    end_datetime   timestamptz
);

create table transactions
(
    id                    bigserial primary key not null,
    public_id             varchar               not null,
    account_id            bigint                not null references accounts (id),
    billing_cycle_id      bigint                not null references billing_cycles (id),
    type                  varchar               not null,
    description           varchar,
    debit                 numeric               not null,
    credit                numeric               not null,
    linked_transaction_id bigint references transactions (id),
    created_at            timestamptz           not null default now()
);

create table tasks
(
    id          bigserial primary key not null,
    uuid        uuid unique           not null,
    title       varchar               not null,
    assign_cost int                   not null,
    reward      int                   not null,
    user_uuid   uuid references users (uuid)
);
