#!/bin/sh
set -e

psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" <<-EOSQL
    CREATE USER accounts WITH PASSWORD 'accounts';
    CREATE DATABASE accounts;
    GRANT ALL PRIVILEGES ON DATABASE accounts TO accounts;

    CREATE USER tasks WITH PASSWORD 'tasks';
    CREATE DATABASE tasks;
    GRANT ALL PRIVILEGES ON DATABASE tasks TO tasks;

    CREATE USER accounting WITH PASSWORD 'accounting';
    CREATE DATABASE accounting;
    GRANT ALL PRIVILEGES ON DATABASE accounting TO accounting;
EOSQL