@Library('playwright-lib') _

pipeline {
    agent any

    tools {
        nodejs 'Node20'
    }

    environment {
        CI = "true"
    }

    stages {

        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Install Dependencies') {
            steps {
                script {                         // ✔ Required for calling library steps
                    installDependencies()
                }
            }
        }

        stage('Run Tests') {
            steps {
                script {                         // ✔ Must be inside script block
                    runTests()
                }
            }
        }
    }

    post {
        always {
            script {                             // ✔ Required in post block too
                publishAllure()
                publishHTML()
            }
        }
    }
}
