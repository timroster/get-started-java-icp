#!/bin/bash

#Install latest ICP plugin
bx plugin install ./icp-linux-amd64 -f

#Log into the ICP plugin
bx pr login -u admin -p admin -c id-mycluster-account

#Initialize helm
helm init

#Check helm version
helm version --tls