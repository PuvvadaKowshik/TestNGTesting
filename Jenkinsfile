pipeline {
    agent any

    tools {
        maven 'MAVEN3'
        jdk 'JDK17'
    }

    stages {

        stage('Clone Repository') {
            steps {
                git branch: 'main',
                url: 'https://github.com/PuvvadaKowshik/TestNGTesting.git'
            }
        }

        stage('Build and Run TestNG') {
            steps {
                bat 'mvn clean test'
            }
        }
    }

    post {
        always {
            emailext(
                subject: "TestNG Report - ${currentBuild.currentResult}",
                body: """
                Build Status: ${currentBuild.currentResult}

                Project: ${env.JOB_NAME}
                Build Number: ${env.BUILD_NUMBER}
                """,
                to: 'YOUR_MAIL@gmail.com',
                attachmentsPattern: '**/emailable-report.html'
            )
        }
    }
}
