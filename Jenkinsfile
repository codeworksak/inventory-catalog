pipeline{
         agent any
         tools {
             jdk 'jdk25'
             maven 'Maven3_9'
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
                                  sh '''
                                  echo "Creating artifact"
                                  '''
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