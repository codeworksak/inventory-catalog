pipeline{
         agent any
         tools {
             jdk 'jdk25'
             maven 'Maven3_9'
         }
         environment {
             APP_NAME="catalog-api"
            // DOCKER_IMAGE= "codeworksak/${APP_NAME}"
            DOCKER_IMAGE= "codeworksak/inventory-catalog"
             IMAGE_TAG="${BUILD_NUMBER}"
             DOCKER_CRED_ID="docker-cred"
         }
         stages {
                 stage("Checkout from GIT") {
                      steps {
                                sh '''
                                echo "checking out code from git"
                                '''
                                git branch: 'dev', credentialsId: 'github-token', url: 'https://github.com/codeworksak/inventory-catalog.git'
                      }
                 }
                 stage("Initialize verify tools") {
                       steps {
                             echo "verify tools"
                             echo "Java home ${env.JAVA_HOME}"

                                  sh '''
                                        mvn --version
                                  '''
                       }
                }
                 stage("Build project") {
                       steps {
                               echo "Build project"
                                  sh '''
                                    mvn -B -DskipTests clean package
                                  '''
                       }
                }
                stage("Archive Artifacts") {
                       steps {
                                echo "Creating artifact"
                                  sh '''
                                    # archiveArtifacts artifacts: '**/target/*.jar',fingerprint: true
                                  '''
                       }
                }
                stage("Build Docker Image") {
                       steps {
                                echo "Build Docker Image"
                                echo "${DOCKER_IMAGE}:${IMAGE_TAG}"
                               script {
                                  appImage= docker.build("${DOCKER_IMAGE}:${IMAGE_TAG}")
                               }
                       }
                }
                 stage("Push Docker Image") {
                       steps {
                                echo "Push Docker Image"
                                echo "${DOCKER_IMAGE}:${IMAGE_TAG}"
                               script {
                                  docker.withRegistry('',DOCKER_CRED_ID) {
                                      appImage.push()
                                      //appImage.push("latest")
                                  }
                               }
                       }
                }
         }
         post {
              success {
                     echo "Pipeline completed successfully"
              }
              failure {
                     echo "Pipeline failed"
              }
         }
}