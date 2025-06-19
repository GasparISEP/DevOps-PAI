pipeline {
    agent any

    tools {
        jdk 'jdk-21'
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build & Test Backend') {
            steps {
                sh './mvnw clean verify'
            }
        }

        stage('Build & Test Frontend') {
            steps {
                dir('frontend') {
                    sh 'npm install'
                    sh 'CI=false npm run build'
                    sh 'npm test -- --watchAll=false'
                }
            }
        }

        stage('Docker Build') {
            steps {
                script {
                    docker.build("${BACKEND_IMAGE}:${BUILD_NUMBER}", "-f Dockerfile .")
                    docker.build("${FRONTEND_IMAGE}:${BUILD_NUMBER}", "frontend")
                }
            }
        }

        stage('Docker Push') {
            steps {
                withCredentials([usernamePassword(
                    credentialsId: "${DOCKER_CREDENTIALS_ID}",
                    usernameVariable: 'DOCKER_USER',
                    passwordVariable: 'DOCKER_PASS'
                )]) {
                    script {
                        docker.withRegistry(DOCKER_REGISTRY, DOCKER_CREDENTIALS_ID) {
                            docker.image("${BACKEND_IMAGE}:${BUILD_NUMBER}").push()
                            docker.image("${FRONTEND_IMAGE}:${BUILD_NUMBER}").push()
                        }
                    }
                }
            }
        }

        stage('Deploy') {
            steps {
                sh """
                    export BUILD_NUMBER=${BUILD_NUMBER}
                    sed 's/\\$BUILD_NUMBER/${BUILD_NUMBER}/g' docker-compose.yaml > docker-compose.expanded.yaml
                    docker-compose -f docker-compose.expanded.yaml down || true
                    docker-compose -f docker-compose.expanded.yaml pull
                    docker-compose -f docker-compose.expanded.yaml up -d
                """
            }
        }
    }

    post {
        always {
            junit 'target/surefire-reports/*.xml'
        }
        success {
            echo '✅ Build completo!'
        }
        failure {
            echo '❌ Falhou o build!'
        }
    }
}