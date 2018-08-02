
# Liberty getting started application
This is a modified version of the code for [Getting started tutorial for Liberty](https://console.bluemix.net/docs/runtimes/liberty/getting-started.html#getting-started-tutorial) for use in IBM Cloud Private developer training.

The main difference with this version is that it does not use an external IBM Cloudant service but rather uses a containerized version of Cloudant called [Cloudant for Developers](https://hub.docker.com/r/ibmcom/cloudant-developer/) so it can be deployed in it's entirety to the IBM Cloud Private Kubernetes environment.

This version of the Liberty app also uses the [Cloudant Java Client](https://github.com/cloudant/java-cloudant) to add information to a database and then return information from a database to the UI.

<p align="center">
  <kbd>
    <img src="docs/GettingStarted.gif" width="300" style="1px solid" alt="Gif of the sample app contains a title that says, Welcome, a prompt asking the user to enter their name, and a list of the database contents which are the names Joe, Jane, and Bob. The user enters the name, Mary and the screen refreshes to display, Hello, Mary, I've added you to the database. The database contents listed are now Mary, Joe, Jane, and Bob.">
  </kbd>
</p>

The following steps are the general procedure to set up and deploy your app to IBM Cloud. See more detailed instructions in the [Getting started tutorial for Liberty](https://console.bluemix.net/docs/runtimes/liberty/getting-started.html#getting-started-tutorial).


## Before you begin

You'll need [IBM Cloud Private](https://www.ibm.com/cloud-computing/products/ibm-cloud-private/), the [IBM Cloud Private CLI](https://www.ibm.com/support/knowledgecenter/en/SSBS6K_2.1.0.3/manage_cluster/install_cli.html) and the command line tools [kubectl](https://www.ibm.com/support/knowledgecenter/en/SSBS6K_2.1.0.3/manage_cluster/cfc_cli.html), and [helm](https://www.ibm.com/support/knowledgecenter/en/SSBS6K_2.1.0.3/app_center/create_helm_cli.html).

You will also need to follow the steps detailed in the setup/README.md document on configuring the ICP cli plugin and configuring Helm.

## 
