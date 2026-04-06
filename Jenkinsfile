pipeline {
    agent any

    environment {
        // Define the image name to keep the script clean
        DOCKER_IMAGE = "discount-app:v1"
    }

    stages {
        stage('Checkout') {
            steps {
                // This pulls the latest code from the GitHub repo linked in the Jenkins Job
                checkout scm
            }
        }

        stage('Build & Package') {
            steps {
                echo 'Compiling the Dynamic Discount System...'
                bat 'mvn clean package'
            }
        }

        stage('JUnit Testing') {
            steps {
                echo 'Running Discount Calculation Logic Tests...'
                bat 'mvn test'
            }
        }

        stage('Docker Build') {
            steps {
                echo 'Creating Docker Image using Eclipse Temurin...'
                bat "docker build -t ${DOCKER_IMAGE} ."
            }
        }

        stage('K8s Deploy') {
            steps {
                echo 'Deploying to Kubernetes Cluster...'
                // The --validate=false flag helps if Jenkins has trouble reaching the OpenAPI spec
                bat 'kubectl apply -f deployment.yaml --validate=false'
            }
        }

        stage('Verify Deployment') {
            steps {
                echo 'Checking Pod Status...'
                bat 'kubectl get pods'
                bat 'kubectl get deployments'
            }
        }
    }

    post {
        success {
            echo 'CI/CD Pipeline Completed Successfully!'
        }
        failure {
            echo 'Pipeline Failed. Please check the Console Output for errors.'
        }
    }
}