pipeline {
    agent any
    
    environment {
        // This tells kubectl exactly where to find the 'keys' to your cluster
        KUBECONFIG = "C:/Users/batch1/.kube/config"
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build & Test') {
            steps {
                bat 'mvn clean package test'
            }
        }

        stage('Docker Build') {
            steps {
                bat 'docker build -t discount-app:v1 .'
            }
        }

        stage('K8s Deploy') {
            steps {
                // Using --kubeconfig here is a double-safety measure
                bat "kubectl --kubeconfig=${KUBECONFIG} apply -f deployment.yaml --validate=false"
            }
        }

        stage('Verify') {
            steps {
                bat "kubectl --kubeconfig=${KUBECONFIG} get pods"
            }
        }
    }
}