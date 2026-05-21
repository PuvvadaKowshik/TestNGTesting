pipeline {
    agent any

    tools {
        maven 'Maven'
        jdk 'JDK'
    }

    stages {

        stage('Clone') {
            steps {
                git branch: 'main',
                url: 'https://github.com/PuvvadaKowshik/TestNGTesting.git'
            }
        }

        stage('Run TestNG') {
            steps {
                bat 'mvn clean test'
            }
        }
    }

    post {
        always {
            emailext(
                subject: "Test Report - ${currentBuild.currentResult}",
                body: "Build Status: ${currentBuild.currentResult}",
                to: 'yourmail@gmail.com',
                attachmentsPattern: '**/emailable-report.html'
            )
        }
    }
}
