create table accounts.authentication
(
    userUuid    uuid,
    loginType   text,
    loginAt     timestamp,
    sessionUuid uuid,
    primary key ( userUuid, loginAt )
) with clustering order by (loginAt desc);

create table accounts.refreshTokenAction
(
    jwtId       uuid,
    userUuid    uuid,
    refreshedAt timestamp,
    primary key ( userUuid, refreshedAt )
) with clustering order by (refreshedAt desc);

create table accounts.session
(
    uuid      uuid,
    userUuid  uuid,
    active    boolean,
    createdAt timestamp,
    updatedAt timestamp,
    primary key ( uuid )
);
