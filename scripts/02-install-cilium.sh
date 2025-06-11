#!/usr/bin/env bash

helm repo add cilium https://helm.cilium.io/

helm install cilium cilium/cilium --version 1.16.1 --namespace kube-system --kube-context kind-s2s

# restart pods
kubectl --cluster="kind-s2s" --context="kind-s2s" get pods --all-namespaces -o custom-columns=NAMESPACE:.metadata.namespace,NAME:.metadata.name,HOSTNETWORK:.spec.hostNetwork --no-headers=true | grep '<none>' | awk '{print "-n "$1" "$2}' | xargs -L 1 kubectl --cluster="kind-s2s" --context="kind-s2s" delete pod

helm upgrade cilium cilium/cilium --version 1.16.1 --namespace kube-system --kube-context kind-s2s   \
      --namespace kube-system \
     --set hubble.enabled=true \
     --set hubble.relay.enabled=true \
     --set hubble.ui.enabled=true
