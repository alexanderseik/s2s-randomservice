#!/usr/bin/env bash

kubectl --cluster="kind-s2s" --context="kind-s2s" port-forward -n kube-system svc/hubble-relay 4245:80
