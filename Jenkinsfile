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
                withSonarQubeEnv('SonarQubeServer2') {
                    bat "mvn sonar:sonar -Dsonar.projectKey=marketplace-artisanat -Dsonar.host.url=http://localhost:9000 -Dsonar.token=${SONAR_TOKEN}"
                }
            }
        }

        stage('Quality Gate Check') {
            steps {
                timeout(time: 5, unit: 'MINUTES') {
                    waitForQualityGate abortPipeline: true
                }
            }
        }

        stage('Build Docker Images') {
            steps {
                script {
                    def services = ['artisanat-backend', 'artisanat-frontend']
                    services.each { service ->
                        dir(service) {
                            bat "docker build -t marketplace-artisanat-${service} ."
                        }
                    }
                }
            }
        }

        stage('Tag and Push Docker Images') {
            steps {
                script {
                    def services = ['artisanat-backend', 'artisanat-frontend']
                    docker.withRegistry("https://index.docker.io/v1/", 'docker-credentials-id') {
                        services.each { service ->
                            def imageName = "${DOCKER_HUB_REPO}:${service}"
                            bat """
                                docker tag marketplace-artisanat-${service}:latest ${imageName}
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
                    bat 'docker-compose up -d'
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