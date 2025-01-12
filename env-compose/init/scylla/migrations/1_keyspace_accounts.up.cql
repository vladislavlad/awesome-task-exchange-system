create table accounts.authentication_action
(
    user_uuid    uuid,
    login_type   text,
    login_at     timestamp,
    session_uuid uuid,
    primary key ( user_uuid, login_at )
) with clustering order by (login_at desc);

create table accounts.refresh_token_action
(
    jwt_id       uuid,
    user_uuid    uuid,
    refreshed_at timestamp,
    primary key ( user_uuid, refreshed_at )
) with clustering order by (refreshed_at desc);

create table accounts.session
(
    uuid       uuid,
    user_uuid  uuid,
    active     boolean,
    created_at timestamp,
    updated_at timestamp,
    primary key ( uuid )
);
