#!/usr/bin/env bash

helm upgrade randomservices ../helm/randomservice --kube-context kind-s2s --create-namespace
