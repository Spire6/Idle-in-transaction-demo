# Idle-in-transaction-demo

This project is a demo app to reproduce a bug when pgdb connection/session remains in `idle in transaction` state.

## Link
- [Stackoverflow link](https://stackoverflow.com/questions/74740182/transientdataaccessresourceexception-r2dbc-pgdb-connection-remains-in-idle-in)
- [OpenAPI SwaggerUI](http://localhost:8080/demo/webjars/swagger-ui/index.html?configUrl=/portal/v3/api-docs/swagger-config#/)
- [PGAdmin](http://localhost:8082/)

## Steps
1. Call `/saveDummyFooBars` endpoint with size <= 256 - `http://localhost:8080/demo/api/saveDummyFooBars?size=256` 
- It should be ok!
2. Call the same endpoint with size greater than 256 - `http://localhost:8080/demo/api/saveDummyFooBars?size=257` 
- TransientDataAccessResourceException
- *Cannot exchange messages because the request queue limit is exceeded; nested exception is io.r2dbc.postgresql.client.ReactorNettyClient$RequestQueueException*
- There is no `Releasing R2DBC Connection` after this exception. The pgdb connection/session remains in `idle in transaction` state. 
- The app is not able to run properly when pool max size is reached and all of the connections are in `idle in transaction` state.

If I use `concatMap` instead of `flatMap` it works as expected - no exception, connection released!
It's also ok without `@Transactional` annotation or with flatMap when the elements are less than or equal to 256.
I have tried to change the `Queues.SMALL_BUFFER_SIZE`, and also tried to add a `concurrency` value to the flatmap.
It works when I reduced the value to `255` but I think it is not a good solution.

## Scripts
You can reproduce the issue with scripts under `./script` folder.
- Success - `saveDummyFooBars - success.sh`
- Fail - `saveDummyFooBars - fail.sh`

## Similar topics that I have checked
- https://stackoverflow.com/questions/72683271/cannot-save-more-than-256-records-r2dbc-spring-webflux-cannot-exchange-messages
- https://stackoverflow.com/questions/66396516/r2dbc-postgresql-cannot-exchange-messages-because-the-request-queue-limit-is
- https://stackoverflow.com/questions/68407202/r2dbc-pool-connection-not-released-after-cancel
