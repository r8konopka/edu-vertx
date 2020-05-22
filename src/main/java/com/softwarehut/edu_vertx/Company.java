package com.softwarehut.edu_vertx;

import io.vertx.sqlclient.Row;
import io.vertx.sqlclient.Tuple;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Company {

  private Long id;
  private String name;
  private String description;
  private String website;

  public static Company of(Row row) {
    return new Company(row.getLong("id"), row.getString("name"), row.getString("description"), row.getString("website"));
  }

  public Long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getDescription() {
    return description;
  }

  public String getWebsite() {
    return website;
  }

  public Tuple toTuple() {
    return Tuple.of(id, name, description, website);
  }
}
