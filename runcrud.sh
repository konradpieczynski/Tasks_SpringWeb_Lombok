#!/usr/bin/env bash
fail() {
   echo "There were errors"
}

if ./gradlew build; then
   docker-compose down
   docker-compose up -d
   sleep 15
else
   docker-compose down
   fail
fi