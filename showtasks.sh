#!/usr/bin/env bash
fail() {
   echo "There was error"
}

if ./runcrud.sh; then
   gio open http://desktop-0001.lan:8090/crud/v1/tasks/
else
   fail
fi