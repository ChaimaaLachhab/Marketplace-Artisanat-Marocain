pipeline {
    agent any

    tools {
        maven 'mvn'
    }

    environment {
        SONARQUBE_SERVER = 'SonarQubeServer2'
        DOCKER_HUB_REPO = 'chaimaalachhab01/marketplace-artisanat'
        SONAR_TOKEN = credentials('SonarQubeToken2')
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/ChaimaaLachhab/Marketplace-Artisanat-Marocain.git'
            }
        }

        stage('Build and Unit Tests') {
            steps {
                dir('artisanat-backend') {
                    bat "mvn clean install"
                    bat "mvn test"
                }
            }
        }

        stage('SonarQube Analysis') {
            steps {
                dir('artisanat-backend') {
                    withSonarQubeEnv('SonarQubeServer2') {
                        sh "mvn sonar:sonar -Dsonar.projectKey=marketplace-artisanat-backend -Dsonar.host.url=http://localhost:9000 -Dsonar.token=${SONAR_TOKEN}"
                    }
                }
            }
        }

//         stage('Quality Gate Check') {
//             steps {
//                 timeout(time: 5, unit: 'MINUTES') {
//                     waitForQualityGate abortPipeline: true
//                 }
//             }
//         }

        stage('Tag and Push Docker Image') {
            steps {
                dir('artisanat-backend') {
                    script {
                        docker.withRegistry("https://index.docker.io/v1/", 'docker-credentials-id') {
                            def imageName = "${DOCKER_HUB_REPO}-backend:latest"
                            sh """
                                docker tag ${DOCKER_HUB_REPO}-backend ${imageName}
                                docker push ${imageName}
                            """
                        }
                    }
                }
            }
        }

        stage('Run Docker Compose') {
            steps {
                dir('artisanat-backend') {
                    sh 'docker-compose up -d'
                }
            }
        }
    }

    post {
        always {
            cleanWs()
        }
        success {
            echo 'Pipeline completed successfully!'
        }
        failure {
            echo 'Pipeline failed!'
        }
    }
}