#!/usr/bin/env bash

helm install randomservices ../helm/randomservice --kube-context kind-s2s --create-namespace
