#!/usr/bin/env bash

../gradlew -p=.. clean bootJar

docker build -t randomservice ..

kind --name s2s load docker-image randomservice:latest
