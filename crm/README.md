# Getting Started
    #
    mvn spring-boot:run
    mvn spring-boot:run -Dspring-boot.run.arguments=--server_port=8080
    #
    mvn -V -s ../settings.xml spring-boot:run -Dspring-boot.run.arguments="--spring.profiles.active=dev"

### Containerize MSA:
Builds an image and tags it
image is built using Cloud Native Buildpacks (no need for Dockerfile):

    mvn spring-boot:build-image

### Run MSA via docker:
    docker run -it -p8080:8080 --name crm crm:1.0.0-SNAPSHOT
    docker container stop crm
    docker container rm crm

# [App Service] Configure the deployment
    az login
    az account list-locations -o table
    # mvn com.microsoft.azure:azure-webapp-maven-plugin:1.13.0:config
    mvn package azure-webapp:deploy
    az group delete --name <resource_group_name>

# [Azure App Service] Configure the deployment
    export REGISTRY_NAME=vcdev
    az acr login -n $REGISTRY_NAME && mvn compile jib:build
- [Deploy a Spring Boot application to Linux on Azure App Service](https://docs.microsoft.com/en-us/azure/developer/java/spring-framework/deploy-spring-boot-java-app-on-linux)

# [Azure Kubernetes Service] Configure the deployment
    export REGISTRY_NAME=vcdev
    az aks create --resource-group=totemsoft-dev-rg --name=$REGISTRY_NAME-k8s-cluster \
      --attach-acr $REGISTRY_NAME \
      --dns-name-prefix=$REGISTRY_NAME-k8s --generate-ssh-keys
    
- [Deploy Spring Boot Application to the Azure Kubernetes Service](https://docs.microsoft.com/en-us/azure/developer/java/spring-framework/deploy-spring-boot-java-app-on-kubernetes)

# Application is deployed on OpenShift(minishift)
> Minishift is a tool that helps you run OpenShift locally by running a single-node OpenShift cluster inside a VM. You can try out OpenShift or develop with it, day-to-day, on your localhost.

## 1. Install virtual box
* [Virtualbox Download](https://www.virtualbox.org/wiki/Downloads)

## 2. Set up your virtualization environment
    brew install docker-machine-driver-xhyve
    sudo chown root:wheel /usr/local/opt/docker-machine-driver-xhyve/bin/docker-machine-driver-xhyve
    sudo chmod u+s /usr/local/opt/docker-machine-driver-xhyve/bin/docker-machine-driver-xhyve

## 3a. Install Minishift
    brew install minishift
    #brew install --force minishift
## 3b. Install Minikube
    brew install minikube

## 4a. Start Minishift
    minishift config set vm-driver virtualbox
    minishift start
    # minishift start --vm-driver=virtualbox
    # minishift stop
    minishift ip
    minishift oc-env

    oc login -u system:admin
    oc login -u developer
    https://<ip>:8443/console/catalog

    minishift addons install --defaults
    oc adm policy --as system:admin add-cluster-role-to-user cluster-admin developer
## 4b. Start Minikube (auto selected the docker driver, other choices: hyperkit, virtualbox, ssh)
    minikube start --driver=docker
    minikube kubectl -- get po -A
    #alias kubectl="minikube kubectl --"
    kubectl get po -A
    minikube dashboard

## 5a. For OpenShift
    # mvn clean package oc:resource oc:build oc:deploy -Popenshift
    mvn clean oc:resource -Popenshift
    mvn package oc:build -Popenshift
    mvn oc:deploy -Popenshift
    oc get pods -w
## 5b. For Kubernetes
    # mvn clean install k8s:build k8s:resource k8s:apply
    mvn clean k8s:resource -Pkubernetes
    mvn package k8s:build -Pkubernetes
    mvn k8s:deploy -Pkubernetes
    kubectl get pods

# PostgreSQL
    Username: user
    Password: password
    Database Name: crmdb
    Connection URL: postgresql://postgresql:5432/

# References
* [Kubernetes](https://kubernetes.io/docs/home/)
* [Minikube](https://minikube.sigs.k8s.io/docs/start/)
* [Minishift](https://docs.okd.io/3.11/minishift/getting-started/installing.html)
* [Eclipse jJKube](https://github.com/eclipse/jkube)
* [OpenShift Maven Plugin](https://github.com/eclipse/jkube/tree/master/openshift-maven-plugin)
* [Spring Boot Sample](https://github.com/eclipse/jkube/tree/master/quickstarts/maven/spring-boot)
* [how-to-setup-openshift-locally-on-mac-os](https://medium.com/swlh/how-to-setup-openshift-locally-on-mac-os-a3b7eb5a5151)
* [setting-up-virtualization-environment](https://docs.okd.io/3.11/minishift/getting-started/setting-up-virtualization-environment.html#setting-up-virtualbox-driver)
* [spring-boot-deploy-openshift](https://www.baeldung.com/spring-boot-deploy-openshift)
* [spring-cloud-kubernetes](https://github.com/spring-cloud/spring-cloud-kubernetes)
* [Online Spring Boot Banner Generator](https://devops.datenkollektiv.de/banner.txt/index.html)
