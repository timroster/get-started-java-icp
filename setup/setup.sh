#!/bin/bash

#log into the cloudctl tool for managing ICP
cloudctl login -u admin -p admin -c id-mycluster-account -a https://mycluster.icp:8443 -n default --skip-ssl-validation

#Initialize helm
helm init

#Check helm version
helm version --tls