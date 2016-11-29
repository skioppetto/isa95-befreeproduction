#!/bin/bash
docker rm -f bfp-mongodb || true
docker run  --name bfp-mongodb -p 27017:27017 -d mongo mongod
docker cp be-free-production-isa95.init.js bfp-mongodb:/data/db
docker exec bfp-mongodb bash -c 'mongo < /data/db/be-free-production-isa95.init.js'
