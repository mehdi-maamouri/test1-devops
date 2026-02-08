pipeline {
    agent any

    environment {
        DOCKER_USER = 'mehdimaamouri'
        IMAGE_NAME  = "${DOCKER_USER}/test1-devops"
        VERSION     = "1.0"
    }

    stages {

        stage('Checkout') {
            steps {
                echo "=== STEP 1: Fetching code from GitHub ==="
                git branch: 'main', url: 'https://github.com/mehdi-maamouri/test1-devops'
                echo "SUCCESS: Code downloaded."
            }
        }

        stage('Unit Tests') {
            steps {
                echo "=== STEP 2: Running Maven Tests ==="
                sh 'mvn -B test'
                echo "SUCCESS: Tests passed."
            }
        }

        stage('Build Jar') {
            steps {
                echo "=== STEP 3: Packaging Application ==="
                sh 'mvn -B clean package -DskipTests'
                echo "SUCCESS: JAR Built."
            }
        }

        stage('Docker Build') {
            steps {
                echo "=== STEP 4: Building Docker Image ==="
                sh "docker build -t ${IMAGE_NAME}:${VERSION} ."
                sh "docker tag ${IMAGE_NAME}:${VERSION} ${IMAGE_NAME}:latest"
                echo "SUCCESS: Docker Image Built."
            }
        }

        stage('Docker Push') {
            steps {
                echo "=== STEP 5: Push Image to Docker Hub ==="
                withCredentials([usernamePassword(
                    credentialsId: 'docker-hub-credentials',
                    usernameVariable: 'DOCKER_USERNAME',
                    passwordVariable: 'DOCKER_PASSWORD'
                )]) {

                    sh '''
                    echo "$DOCKER_PASSWORD" | docker login -u "$DOCKER_USERNAME" --password-stdin
                    docker push ''' + IMAGE_NAME + ''':''' + VERSION + '''
                    docker push ''' + IMAGE_NAME + ''':latest
                    docker logout
                    '''
                }
                echo "SUCCESS: Image pushed to Docker Hub."
            }
        }
    }

    post {

        success {
            echo "===================================="
            echo " PIPELINE COMPLETED SUCCESSFULLY "
            echo "===================================="
        }

        failure {
            echo "====================================

            echo " PIPELINE FAILED "
            echo "===================================="
        }
    }
}