package com.softwarehut.edu_vertx;

import io.vertx.pgclient.PgConnectOptions;
import io.vertx.sqlclient.PoolOptions;

public class PostgresConfig {

  public static PgConnectOptions PG_CONNECT_OPTIONS = new PgConnectOptions()
    .setPort(5432)
    .setHost("localhost")
    .setDatabase("postgres")
    .setUser("postgres")
    .setPassword("admin");

  public static PoolOptions PG_POOL_OPTIONS = new PoolOptions()
    .setMaxSize(5);
}
