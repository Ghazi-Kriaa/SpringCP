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
                       withMaven(maven : 'apache-maven-3.6.1') {
                           bat'mvn clean compile'
                       }
                   }
               }

      }
    }