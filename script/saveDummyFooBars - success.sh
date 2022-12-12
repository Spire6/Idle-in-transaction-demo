#!/bin/sh

echo Saving 256 elements...
curl -X POST http://localhost:8080/demo/api/saveDummyFooBars?size=256
