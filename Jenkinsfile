pipeline{
         agent any
         tools {
             jdk java
             maven Maven3.9
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
                                  sh '''
                                  echo "verify tools"
                                  '''
                       }
                }
                 stage("Build project") {
                       steps {
                                  sh '''
                                  echo "Build project"
                                  '''
                       }
                }
                 stage("Archive Artifacts") {
                       steps {
                                  sh '''
                                  echo "Creating artifact"
                                  '''
                       }
                }
                post {
                    success{
                        echo "Pipeline completed successfully"
                    }
                    failure {
                        echo "Pipeline failed"
                    }
                }
         }
}