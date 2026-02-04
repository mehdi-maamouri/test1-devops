pipeline {
    agent any

   environment {
        DOCKER_USER = 'mehdimaamouri'
        IMAGE_NAME  = "${DOCKER_USER}/test1-devops"
        DOCKER_HOST = 'tcp://127.0.0.1:2375' 
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
                sh 'mvn test'
                echo "SUCCESS: All tests passed."
            }
        }

        stage('Build & Package') {
            steps {
                echo "=== STEP 3: Compiling and Packaging JAR ==="
                sh 'mvn clean package -DskipTests'
                echo "SUCCESS: JAR file created in target/ directory."
            }
        }
        stage('Debug Workspace') {
    steps {
        sh 'echo "Workspace: $WORKSPACE"'
        sh 'ls -l $WORKSPACE'
          }
        }
        stage('Docker Build') {
            steps {
                echo "=== STEP 4: Building Docker Image ==="
                sh "docker build -t ${IMAGE_NAME}:1.0 ."
                sh "docker tag ${IMAGE_NAME}:1.0 ${IMAGE_NAME}:latest"
                echo "SUCCESS: Docker image ${IMAGE_NAME} is ready."
            }
        }

        stage('Docker Push') {
            steps {
                echo "=== STEP 5: Pushing Image to Docker Hub ==="
                script {
                    withCredentials([usernamePassword(credentialsId: 'docker-hub-credentials', 
                                                     usernameVariable: 'U', 
                                                     passwordVariable: 'P')]) {
                        sh "echo \$P | docker login -u \$U --password-stdin"
                        sh "docker push ${IMAGE_NAME}:1.0"
                        sh "docker push ${IMAGE_NAME}:latest"
                        sh "docker logout"
                    }
                    echo "SUCCESS: Image is now live on Docker Hub!"
                }
            }
        }
    }

    post {
        success {
            echo "===================================="
            echo "   PIPELINE COMPLETED SUCCESSFULLY  "
            echo "===================================="
        }
        failure {
            echo "===================================="
            echo "   PIPELINE FAILED! CHECK LOGS      "
            echo "===================================="
            emailext (
                to: 'mehdi.maamouri@esprit.tn',
                subject: "FAILED: ${currentBuild.fullDisplayName}",
                body: "Build failed. Check logs: ${env.BUILD_URL}",
                mimeType: 'text/html'
            )
        }
    }
}
