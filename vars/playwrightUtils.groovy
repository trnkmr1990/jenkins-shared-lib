def installDependencies() {
    echo "Installing dependencies..."
    retry(2) {
        bat "npm ci"
    }
}

def runTests() {
    echo "Running Playwright tests..."
    retry(2) {
        bat "npx playwright test"
    }
}

def publishAllure() {
    echo "Publishing Allure Report..."
    allure([
        includeProperties: false,
        jdk: '',
        results: [[path: "allure-results"]]
    ])
}

def publishJUnitReport(){
    echo "Publishing JUnit Report..."
    junit allowEmptyResults: true, skipMarkingBuildUnstable: true, testResults: 'junit-results/*.xml'
}

def publishHtmlReport() {
    echo "Publishing Playwright HTML Report..."
    publishHTML([
        allowMissing: true,
        alwaysLinkToLastBuild: true,
        keepAll: true,
        reportDir: "playwright-report",
        reportFiles: "index.html",
        reportName: "Playwright HTML Report"
    ])
}
