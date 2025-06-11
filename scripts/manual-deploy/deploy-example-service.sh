alias k8s2s='kubectl --cluster="kind-s2s" --context="kind-s2s"'

k8s2s create namespace example || echo ""
k8s2s delete -f ../k8s/deployment.yaml
k8s2s apply -f ../k8s/deployment.yaml
k8s2s delete -f ../k8s/service.yaml
k8s2s apply -f ../k8s/service.yaml
