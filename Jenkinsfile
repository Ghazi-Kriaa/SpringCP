pipeline {
    agent any
    stages {
        stage('Checkout GIT') {
            steps {
                checkout([
                    $class: 'GitSCM',
                    branches: [[name: 'main']],
                    userRemoteConfigs: [[url: 'https://github.com/Ghazi-Kriaa/SpringCP.git']]
                ])
            }
        }
  stage('MVN CLEAN') {
              steps {
                 withMaven() {
                bat 'mvn clean compile'

             }
           }
          }
          stage('MVN COMPILE') {
                              steps {
                                  bat 'mvn compile'
                              }
                          }

                          stage('MVN PACKAGE') {
                              steps {
                                  bat 'mvn package'
                              }
                          }

                          stage('MVN TEST') {
                              steps {
                                  bat 'mvn test'
                              }
                          }
stage('MVN SONARQUBE') {
                    steps {
                        bat 'mvn sonar:sonar  -Dsonar.projectKey=SpringCP  -Dsonar.host.url=http://localhost:9000    -Dsonar.login=sqp_b63f6cc38319c01a83e749970e360316f818d767'
                    }
                }
stage('Build docker image'){
                    steps{
                         script{
                             bat 'docker build -t kriaa/springboot.jar .'
                         }
                    }
        }
        stage('Docker login') {

                     steps {
                          bat 'echo "login Docker ...."'
                          bat 'docker login -u kriaa -p ghazi1234'
                     }
        }
        stage('Docker push') {

                     steps {
                          bat 'echo "Docker is pushing ...."'
                          bat 'docker push kriaa/springboot.jar'
                     }
        }
      }
    }