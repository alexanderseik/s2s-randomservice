alias k8s2s='kubectl --cluster="kind-s2s" --context="kind-s2s"'

k8s2s apply -f ../k8s/serviceaccount/role.yaml
k8s2s apply -f ../k8s/serviceaccount/rolebinding.yaml
