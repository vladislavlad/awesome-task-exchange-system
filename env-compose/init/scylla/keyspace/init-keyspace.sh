#!/bin/bash

# Create default keyspace
CQL="CREATE KEYSPACE $CASSANDRA_KEYSPACE WITH REPLICATION = {'class': 'SimpleStrategy', 'replication_factor': 1};"
until echo $CQL | cqlsh; do
  echo "init-keyspace: ScyllaDB is unavailable - retry later"
  sleep 2
done &

exec /docker-entrypoint.py "$@"
