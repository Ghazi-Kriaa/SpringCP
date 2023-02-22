pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
              git'https://github.com/Ghazi-Kriaa/SpringCP.git'
              bat 'mvn -8 compile'
            }
        }
  stage('Test') {
           steps {
                           bat'mvn test'
                       }
                       post{
                       always{
                       junit'target/surefire-reports/*.xml'
                       }
                       }

      }
      stage('Package'){
      steps{
      bat'mvn package'
      archiveArtifacts'target/*.jar'
      }
      }
    }
    }