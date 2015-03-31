# Optional string value classes with Squeryl

Question: How to support optional value classes, backed by a string?

# Create an example database

```
$ psql
# create database "scratch";
# \c scratch
# create table world ( planetName varchar(128) );
```

# Stack trace

From `sbt run`

```
[info] Running Main
insert into "world" ("planetName") values (?)
jdbcParams:[FieldStatementParam(World(Some(PlanetName(Earth))),World.planetName:Option[java.lang.String])]
[error] (run-main-18) org.squeryl.SquerylSQLException: Exception while executing statement : Can't infer the SQL type to use for an instance of SquerylEntrypoint$PlanetName. Use setObject() with an explicit Types value to specify the type to use.
[error] errorCode: 0, sqlState: 07006
[error] insert into "world" ("planetName") values (?)
[error] jdbcParams:[FieldStatementParam(World(Some(PlanetName(Earth))),World.planetName:Option[java.lang.String])]
org.squeryl.SquerylSQLException: Exception while executing statement : Can't infer the SQL type to use for an instance of SquerylEntrypoint$PlanetName. Use setObject() with an explicit Types value to specify the type to use.
errorCode: 0, sqlState: 07006
insert into "world" ("planetName") values (?)
jdbcParams:[FieldStatementParam(World(Some(PlanetName(Earth))),World.planetName:Option[java.lang.String])]
  at org.squeryl.SquerylSQLException$.apply(KeyedEntity.scala:118)
  at org.squeryl.internals.DatabaseAdapter$class._exec(DatabaseAdapter.scala:328)
  at org.squeryl.internals.DatabaseAdapter$class.exec(DatabaseAdapter.scala:379)
  at org.squeryl.adapters.PostgreSqlAdapter.exec(PostgreSqlAdapter.scala:24)
  at org.squeryl.internals.DatabaseAdapter$class.executeUpdateForInsert(DatabaseAdapter.scala:411)
  at org.squeryl.adapters.PostgreSqlAdapter.executeUpdateForInsert(PostgreSqlAdapter.scala:24)
  at org.squeryl.Table$$anonfun$insert$1.apply(Table.scala:56)
  at org.squeryl.logging.StackMarker$.lastSquerylStackFrame(StatisticsListener.scala:52)
  at org.squeryl.Table.insert(Table.scala:37)
  at Main$$anonfun$1.apply$mcV$sp(squeryl.scala:38)
  at Main$$anonfun$1.apply(squeryl.scala:34)
  at Main$$anonfun$1.apply(squeryl.scala:34)
  at org.squeryl.AbstractSession$class.using(Session.scala:159)
  at org.squeryl.Session.using(Session.scala:93)
  at org.squeryl.Session.withinTransaction(Session.scala:110)
  at org.squeryl.dsl.QueryDsl$class.inTransaction(QueryDsl.scala:126)
  at SquerylEntrypoint$.inTransaction(squeryl.scala:4)
  at Main$.delayedEndpoint$Main$1(squeryl.scala:34)
  at Main$delayedInit$body.apply(squeryl.scala:28)
  at scala.Function0$class.apply$mcV$sp(Function0.scala:40)
  at scala.runtime.AbstractFunction0.apply$mcV$sp(AbstractFunction0.scala:12)
  at scala.App$$anonfun$main$1.apply(App.scala:76)
  at scala.App$$anonfun$main$1.apply(App.scala:76)
  at scala.collection.immutable.List.foreach(List.scala:381)
  at scala.collection.generic.TraversableForwarder$class.foreach(TraversableForwarder.scala:35)
  at scala.App$class.main(App.scala:76)
  at Main$.main(squeryl.scala:28)
  at Main.main(squeryl.scala)
  at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
  at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
  at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
  at java.lang.reflect.Method.invoke(Method.java:483)
Caused by: org.postgresql.util.PSQLException: Can't infer the SQL type to use for an instance of SquerylEntrypoint$PlanetName. Use setObject() with an explicit Types value to specify the type to use.
  at org.postgresql.jdbc2.AbstractJdbc2Statement.setObject(AbstractJdbc2Statement.java:1914)
  at org.postgresql.jdbc3g.AbstractJdbc3gStatement.setObject(AbstractJdbc3gStatement.java:36)
  at org.postgresql.jdbc4.AbstractJdbc4Statement.setObject(AbstractJdbc4Statement.java:47)
  at org.squeryl.internals.DatabaseAdapter$class.setParamInto(DatabaseAdapter.scala:315)
  at org.squeryl.adapters.PostgreSqlAdapter.setParamInto(PostgreSqlAdapter.scala:24)
  at org.squeryl.internals.DatabaseAdapter$$anonfun$fillParamsInto$1.apply(DatabaseAdapter.scala:300)
  at org.squeryl.internals.DatabaseAdapter$$anonfun$fillParamsInto$1.apply(DatabaseAdapter.scala:299)
  at scala.collection.mutable.ResizableArray$class.foreach(ResizableArray.scala:59)
  at scala.collection.mutable.ArrayBuffer.foreach(ArrayBuffer.scala:48)
  at org.squeryl.internals.DatabaseAdapter$class.fillParamsInto(DatabaseAdapter.scala:299)
  at org.squeryl.adapters.PostgreSqlAdapter.fillParamsInto(PostgreSqlAdapter.scala:24)
  at org.squeryl.internals.DatabaseAdapter$$anonfun$executeUpdateForInsert$1.apply(DatabaseAdapter.scala:412)
  at org.squeryl.internals.DatabaseAdapter$$anonfun$executeUpdateForInsert$1.apply(DatabaseAdapter.scala:411)
  at org.squeryl.internals.DatabaseAdapter$class._exec(DatabaseAdapter.scala:324)
  at org.squeryl.internals.DatabaseAdapter$class.exec(DatabaseAdapter.scala:379)
  at org.squeryl.adapters.PostgreSqlAdapter.exec(PostgreSqlAdapter.scala:24)
  at org.squeryl.internals.DatabaseAdapter$class.executeUpdateForInsert(DatabaseAdapter.scala:411)
  at org.squeryl.adapters.PostgreSqlAdapter.executeUpdateForInsert(PostgreSqlAdapter.scala:24)
  at org.squeryl.Table$$anonfun$insert$1.apply(Table.scala:56)
  at org.squeryl.logging.StackMarker$.lastSquerylStackFrame(StatisticsListener.scala:52)
  at org.squeryl.Table.insert(Table.scala:37)
  at Main$$anonfun$1.apply$mcV$sp(squeryl.scala:38)
  at Main$$anonfun$1.apply(squeryl.scala:34)
  at Main$$anonfun$1.apply(squeryl.scala:34)
  at org.squeryl.AbstractSession$class.using(Session.scala:159)
  at org.squeryl.Session.using(Session.scala:93)
  at org.squeryl.Session.withinTransaction(Session.scala:110)
  at org.squeryl.dsl.QueryDsl$class.inTransaction(QueryDsl.scala:126)
  at SquerylEntrypoint$.inTransaction(squeryl.scala:4)
  at Main$.delayedEndpoint$Main$1(squeryl.scala:34)
  at Main$delayedInit$body.apply(squeryl.scala:28)
  at scala.Function0$class.apply$mcV$sp(Function0.scala:40)
  at scala.runtime.AbstractFunction0.apply$mcV$sp(AbstractFunction0.scala:12)
  at scala.App$$anonfun$main$1.apply(App.scala:76)
  at scala.App$$anonfun$main$1.apply(App.scala:76)
  at scala.collection.immutable.List.foreach(List.scala:381)
  at scala.collection.generic.TraversableForwarder$class.foreach(TraversableForwarder.scala:35)
  at scala.App$class.main(App.scala:76)
  at Main$.main(squeryl.scala:28)
  at Main.main(squeryl.scala)
  at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
  at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
  at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
  at java.lang.reflect.Method.invoke(Method.java:483)
[trace] Stack trace suppressed: run last compile:run for the full output.
java.lang.RuntimeException: Nonzero exit code: 1
  at scala.sys.package$.error(package.scala:27)
[trace] Stack trace suppressed: run last compile:run for the full output.
[error] (compile:run) Nonzero exit code: 1
```