
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

## Forking the repository

1. Go to the repository located at https://github.com/odrodrig/get-started-java-icp 

2. Sign into GitHub if you haven't already

3. Click on the **Fork** button at the top right of the page

This will create a copy of the application in your GitHub account for you to be able to modify

## Accessing BlueDemos

1. Go to [Blue Demos](https://bluedemos.com/show/199) and request an instance of ICP

2. You should get an email with a link to the environment and a password to sign in with.

3. Once logged into your IBM Cloud Private virtual environment, click on the profile icon at the top right of the dashboard and select **Configure client**.

![Configure Client](./images/configureClient.png)

4. Copy all the lines or just click on the double page icon to copy all lines to the clipboard.

![Copy Lines](./images/copyLines.png)

5. Then, open up the terminal by either double clicking the icon on the desktop or by clicking on the blue button at the top left of taskbar and selecting **terminal emulator**.

![terminal](./images/terminal.png)

6. Paste in the lines that were copied in the previous step.

![Cluster context](./images/clusterContext.png)

You should see **Switched to context "cluster.local-context"** when done.

7. Then, CD into your **~/Documents/** directory and clone the GitHub project that you forked earlier. You can find this by navigating to the repository in your browser and clicking on the green **Clone or Download** button on the right side of the page.

To clone the repo use the following comand replacing **\<url to repo\>** with the url that you copied.

```bash
git clone <url to repo>
```

7. Next, change directory in the repository that was cloned and find the **/setup/** directory.

8. From the **/setup/** directory run the following command:

```bash
bash setup.sh
```

This setup script will install the IBM Cloud Private plugin for the command line and configure it to point to the cluster within ICP. This also properly configures helm to work with ICP.

![Helm Configured](./images/helmConfigured.png)


## Deploying the Helm chart of the application

1. CD into the **/chart/liberty-starter/** directory of this repository and run the following command:

```bash
helm install . --tls --name liberty-starter
```

This will deploy the java application as well as a cloudant database within the kubernetes cluster.

## Creating a CI/CD pipeline

In this section we will be connecting our cloned GitRepo to a Continuous Integration/Continuous Deployment pipeline built with Jenkins. This pipeline contains 4 different steps as follows:

  | Stage                         | Purpose                                                                        |
  | ----------------------------- | ------------------------------------------------------------------------------ |
  | Build Application War File    | Pulls in dependencies from Maven and packages application into .war file       |
  | Build Docker Image            | Builds the Docker image based on the Dockerfile                                |
  | Push Docker Image to Registry | Uploads the Docker image to the Docker image registry withinin ICP             |
  | Deploy New Docker Image       | Updates the image tag in the Kubernetes deployment triggering a rolling update |

 More details of this pipline can be found in the [Jenkinsfile](./Jenkinsfile).

 1. Jenkins is already installed in this instance of IBM Cloud Private. To access it, open the Firefox browser and click on the Jenkins bookmark from the bookmark bar.

 ![Jenkins bookmark](./images/jenkinsBookmark.png)

 2. Log into Jenkins. The username and password should be autofilled for you. If not, the username should be "admin" and the password is stored in a kubernetes secret. To view the secret, run the following command in the terminal:

 ```bash
 printf $(kubectl get secret --namespace default jenkins -o jsonpath="{.data.jenkins-admin-password}" | base64 --decode);echo
 ```

3. Click on **New Item**, enter "liberty_starter_pipeline" for the name, and select **Pipeline**. When done, click **ok**.

![liberty starter pipeline](./images/libertyPipeline.png)

4. Find and click on the **Configure** button on the left side of the page.

5. Scroll down to the **Pipeline** section and find the **definition** drop down menu. Select **Pipeline script from SCM** and for **SCM** select **Git**.

6. For **Repository URL** enter the url to the cloned repository that you forked earlier. Ensure the **/master** branch is being targeted and click **Save**.

![pipeline config](./images/pipelineConfig.png) 

Now with our pipeline configured we can make a change and deploy the new version. 

7. On your local machine, clone the repo that you forked before. To clone the repo use the following comand replacing **\<url to repo\>** with the url that you copied.

```bash
git clone <url to repo>
```

8. Open the application in a code editor or IDE of your choice. For this lab we will be making a change to the index.html page to change the display language.

9. Open /src/main/webapp/index.html and on line 2 locate the **\<html lang="es">** tag.

![lang tag](./images/langTag.png)

10. Change the **"es"** to one of the following:

- en: English
- es: Spanish
- pt: Portuguese
- fr: French
- ja: Japanese

This will change the language that loads on the webpage to whatever language you selected.

11. Save the file and push to github.

Since this instance of ICP is for demos, it can't be accessed by github which makes automatic deployments not possible on code commits but we can manually trigger pipeline builds.

12. Swich back to ICP and open the pipeline that was created earlier.

13. Click on the **Build now** button to manually start the build process. This will pull the latest code from your code repository on GitHub.

![build now](./images/buildNow.png)

14. Your pipeline will now start building. You should see the different stages appear on the pipeline page.

![stages](./images/stages.png)

15. When the pipeline is finish deploying, open up the app from ICP and you can see that the language of the page has been changed.
