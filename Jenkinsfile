pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                // Pulls the latest code from your GitHub repository
                checkout scm
            }
        }

        stage('Build & Test') {
            steps {
                // Standard Maven build and JUnit execution
                bat 'mvn clean package test'
            }
        }

        stage('Docker Build') {
            steps {
                // Builds the image we just verified in your logs
                bat 'docker build -t discount-app:v1 .'
            }
        }
        stage('K8s Deploy') {
            steps {
                echo 'Deploying to Kubernetes...'
                // We point directly to the config file so Jenkins doesn't use localhost:8080
                withEnv(["KUBECONFIG=C:/Users/batch1/.kube/config"]) {
                    bat 'kubectl apply -f deployment.yaml --validate=false'
                }
            }
        }

        stage('Verify') {
            steps {
                // Confirms the pods are actually running
                bat 'kubectl get pods'
            }
        }
    }
}