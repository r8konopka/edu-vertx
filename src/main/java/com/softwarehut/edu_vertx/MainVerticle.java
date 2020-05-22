package com.softwarehut.edu_vertx;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.Launcher;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;

public class MainVerticle extends AbstractVerticle {

  public static void main(String[] args) {
    Launcher.executeCommand("run", MainVerticle.class.getName());
  }

  @Override
  public void start(Future<Void> startFuture) {
    vertx.deployVerticle(new CompanyVerticle());

    Router router = Router.router(vertx);

    router.route().handler(BodyHandler.create());
    router.get("/").handler(this::get);
    router.post("/").handler(this::add);

    vertx.createHttpServer().requestHandler(router::handle).listen(8888);
  }

  public void get(RoutingContext routingContext) {
    vertx.eventBus().request("company.get.addr", "", reply -> {
      routingContext.request().response()
        .putHeader("content-type", "application/json; charset=utf-8")
        .end((String) reply.result().body());
    });
  }

  public void add(RoutingContext routingContext) {
    final Company company = Json.decodeValue(routingContext.getBodyAsString(), Company.class);
    JsonObject jsonObject = JsonObject.mapFrom(company);
    vertx.eventBus().request("company.add.addr", jsonObject, reply -> {
      routingContext.request().response()
        .putHeader("content-type", "application/json; charset=utf-8")
        .end(Json.encodePrettily(company));
    });
  }
}
