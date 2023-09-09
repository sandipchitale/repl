# Description

This wraps any command into a REPL. 

## How to use?

For example the following shows `kubectl` in REPL mode.

```
> java -jar build\libs\repl.jar kubectl
1 > kubectl /help
                      /history - show history
                         /help - this
                          /cls - clear screens
                       kubectl - repl command
2 > kubectl /history
1 /help
2 /history
3 > kubectl get pods
NAME                     READY   STATUS    RESTARTS        AGE
sample-9945998f7-ptxkr   1/1     Running   16 (168m ago)   131d
4 > kubectl get pods -A
NAMESPACE     NAME                                     READY   STATUS    RESTARTS         AGE
default       sample-9945998f7-ptxkr                   1/1     Running   16 (168m ago)    131d
kube-system   coredns-565d847f94-ljppq                 1/1     Running   16 (168m ago)    131d
kube-system   coredns-565d847f94-vwp57                 1/1     Running   16 (168m ago)    131d
kube-system   etcd-docker-desktop                      1/1     Running   16 (168m ago)    131d
kube-system   kube-apiserver-docker-desktop            1/1     Running   16 (168m ago)    131d
kube-system   kube-controller-manager-docker-desktop   1/1     Running   16 (168m ago)    131d
kube-system   kube-proxy-h8sxz                         1/1     Running   16 (168m ago)    131d
kube-system   kube-scheduler-docker-desktop            1/1     Running   18 (168m ago)    131d
kube-system   storage-provisioner                      1/1     Running   26 (168m ago)    131d
kube-system   vpnkit-controller                        1/1     Running   944 (168m ago)   131d
sample        sample-9945998f7-9l6cs                   1/1     Running   16 (168m ago)    131d
5 > kubectl exit
```
### Reference Documentation
For further reference, please consider the following sections:

* [Official Gradle documentation](https://docs.gradle.org)
* [Spring Boot Gradle Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/3.1.3/gradle-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/3.1.3/gradle-plugin/reference/html/#build-image)

### Additional Links
These additional references should also help you:

* [Gradle Build Scans – insights for your project's build](https://scans.gradle.com#gradle)

