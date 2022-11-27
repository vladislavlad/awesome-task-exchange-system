#!/bin/sh
set -e

psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" <<-EOSQL
    CREATE USER accounts WITH PASSWORD 'accounts';
    CREATE DATABASE accounts WITH OWNER accounts;
    GRANT ALL PRIVILEGES ON DATABASE accounts TO accounts;

    CREATE USER tasks WITH PASSWORD 'tasks';
    CREATE DATABASE tasks WITH OWNER tasks;
    GRANT ALL PRIVILEGES ON DATABASE tasks TO tasks;

    CREATE USER accounting WITH PASSWORD 'accounting';
    CREATE DATABASE accounting WITH OWNER accounting;
    GRANT ALL PRIVILEGES ON DATABASE accounting TO accounting;

    CREATE USER analytics WITH PASSWORD 'analytics';
    CREATE DATABASE analytics WITH OWNER analytics;
    GRANT ALL PRIVILEGES ON DATABASE analytics TO analytics;
EOSQL