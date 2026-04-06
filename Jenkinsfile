pipeline {
    agent any
    
    environment {
        // Pointing Jenkins to your actual user config file
        KUBECONFIG = "C:/Users/batch1/.kube/config"
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build & Package') {
            steps {
                bat 'mvn clean package'
            }
        }

        stage('JUnit Testing') {
            steps {
                bat 'mvn test'
            }
        }

        stage('Docker Build') {
            steps {
                bat 'docker build -t discount-app:v1 .'
            }
        }

        stage('K8s Deploy') {
            steps {
                echo 'Deploying to Docker Desktop Kubernetes...'
                // Using --kubeconfig and specifying the server directly
                bat 'kubectl --kubeconfig=%KUBECONFIG% --server=https://kubernetes.docker.internal:6443 --insecure-skip-tls-verify apply -f deployment.yaml'
            }
        }

        stage('Final Verification') {
            steps {
                bat 'kubectl --kubeconfig=%KUBECONFIG% get pods'
                bat 'kubectl --kubeconfig=%KUBECONFIG% get deployments'
            }
        }
    }
    
    post {
        success {
            echo 'SUCCESS: Dynamic Discount System is live on Kubernetes!'
        }
    }
}