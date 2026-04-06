pipeline {
    agent any
    stages {
        stage('Checkout') {
            steps {
                // Jenkins pulls the code using the credentials you selected in the UI
                checkout scm
            }
        }
        stage('Build & Test') {
            steps {
                bat 'mvn clean test package'
            }
        }
        stage('Docker Build') {
            steps {
                bat 'docker build -t discount-app:v1 .'
            }
        }
        stage('K8s Deploy') {
            steps {
                bat 'kubectl apply -f deployment.yaml'
            }
        }
    }
}