package com.softwarehut.edu_vertx;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Context;
import io.vertx.core.Vertx;
import io.vertx.core.eventbus.Message;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import io.vertx.pgclient.PgPool;
import io.vertx.sqlclient.Row;
import io.vertx.sqlclient.RowSet;
import io.vertx.sqlclient.Tuple;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static com.softwarehut.edu_vertx.PostgresConfig.PG_CONNECT_OPTIONS;
import static com.softwarehut.edu_vertx.PostgresConfig.PG_POOL_OPTIONS;

public class CompanyVerticle extends AbstractVerticle {

  private PgPool pgPool;

  @Override
  public void init(Vertx vertx, Context context) {
    super.init(vertx, context);
    pgPool = PgPool.pool(vertx, PG_CONNECT_OPTIONS, PG_POOL_OPTIONS);
  }

  @Override
  public void start() {
    vertx.eventBus().consumer("company.get.addr", msg -> {
      all(msg);
    });

    vertx.eventBus().consumer("company.add.addr", msg -> {
      JsonObject jsonObject = (JsonObject) msg.body();
      Company company = jsonObject.mapTo(Company.class);
      add(msg, company);
    });
  }

  @Override
  public void stop() {
    pgPool.close();
  }

  private void add(Message<Object> msg, Company company) {
    pgPool
      .preparedQuery("INSERT INTO company (id, name ,description, website) VALUES ($1, $2, $3, $4)")
      .execute(company.toTuple(), ar -> {
        if (ar.succeeded()) {
          RowSet<Row> rows = ar.result();
          System.out.println("Successfully inserted");

          msg.reply(Json.encodePrettily(company));
        } else {
          System.out.println("Failure: " + ar.cause().getMessage());
        }
      });
  }

  void all(Message<Object> msg) {

    pgPool.query("SELECT * FROM company")
      .execute(rowSetAsyncResult -> {
        if (rowSetAsyncResult.succeeded()) {
          RowSet<Row> result = rowSetAsyncResult.result();
          List<Company> dbCompanies = StreamSupport.stream(result.spliterator(), false)
            .map(rom -> Company.of(rom))
            .collect(Collectors.toList());

          msg.reply(Json.encodePrettily(dbCompanies));
        } else {
          System.out.println("Failure: " + rowSetAsyncResult.cause().getMessage());
        }
      });
  }
}
