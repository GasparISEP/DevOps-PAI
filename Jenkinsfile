pipeline {
    agent any

    tools {
        jdk 'jdk-21'
    }

    environment {
        DOCKER_CREDENTIALS_ID = 'dockerhub-credentials'
        DOCKER_REGISTRY       = 'https://index.docker.io/v1/'
        BACKEND_IMAGE         = 'ricardomarques21/backend'
        FRONTEND_IMAGE        = 'ricardomarques21/frontend'
        NODE_ENV              = 'development'
        DOCKER_HOST           = 'tcp://host.docker.internal:2375'
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build & Test Backend') {
            steps {
                echo '🔧 Building backend com Java 21...'
                script {
                    def javaHome = tool name: 'jdk-21', type: 'jdk'
                    env.JAVA_HOME = javaHome
                    sh "${javaHome}/bin/java -version"
                    sh 'mvn clean verify'
                }
            }
        }

        stage('Build Frontend') {
            steps {
                echo '🔧 Building frontend...'
                dir('frontend') {
                    sh 'npm install'
                    sh 'CI=false npm run build'
                }
            }
        }

        stage('Test Frontend') {
            steps {
                echo '🧪 Running frontend tests...'
                dir('frontend') {
                    sh 'npm test -- --watchAll=false || true'
                }
            }
        }

        stage('Build Docker Images') {
            steps {
                echo '🐳 Building Docker images...'
                script {
                    docker.build("${env.BACKEND_IMAGE}:${env.BUILD_NUMBER}", "-f src/Dockerfile .")
                    docker.build("${env.FRONTEND_IMAGE}:${env.BUILD_NUMBER}", 'frontend')
                }
            }
        }

        stage('Push Docker Images') {
            steps {
                echo '📤 Pushing Docker images to registry...'
                withCredentials([usernamePassword(
                    credentialsId: "${DOCKER_CREDENTIALS_ID}",
                    usernameVariable: 'DOCKER_USER',
                    passwordVariable: 'DOCKER_PASS'
                )]) {
                    script {
                        docker.withRegistry("${DOCKER_REGISTRY}", "${DOCKER_CREDENTIALS_ID}") {
                            docker.image("${env.BACKEND_IMAGE}:${env.BUILD_NUMBER}").push()
                            docker.image("${env.FRONTEND_IMAGE}:${env.BUILD_NUMBER}").push()
                        }
                    }
                }
            }
        }

        stage('Deploy to Production') {
            steps {
                echo '🚀 Deploying application to production...'
                script {
                    try {
                        sh 'docker-compose down || true'
                        sh 'docker-compose pull'
                        sh 'docker-compose up -d'
                    } catch (err) {
                        echo "⚠️ Deployment failed, but the pipeline will continue: ${err}"
                    }
                }
            }
        }

        stage('Archive Artifacts') {
            steps {
                echo '📦 Archiving artifacts...'
                archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
                archiveArtifacts artifacts: 'frontend/build/**', fingerprint: true
            }
        }
    }

    post {
        always {
            junit 'target/surefire-reports/*.xml'
        }
        success {
            echo '✅ Pipeline completed successfully!'
        }
        failure {
            slackSend(
                baseUrl: 'https://slack.com/api',
                teamDomain: 'RicardoMarques21',
                channel: '#failures',
                tokenCredentialId: 'slack-credentials',
                color: 'danger',
                message: "🚨 Build failed: ${env.JOB_NAME} #${env.BUILD_NUMBER}\n${env.BUILD_URL}",
                botUser: true
            )
        }
    }
}
