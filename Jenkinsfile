pipeline {
    agent any
     tools {
            maven 'Maven 3.8.7'
            jdk 'Java 17.0.6'
        }
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
                       withMaven(maven : 'apache-maven-3.8.7') {
                           bat'mvn clean compile'
                       }
                   }
               }

      }
    }