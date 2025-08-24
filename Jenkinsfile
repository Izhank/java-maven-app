pipeline {
    agent any
    stages {
        stage("Greet") {
            steps {
                echo "Hello friends"
            }
        }
        stage("deploy") {
            steps {
                script {
                    def dockerCMD = 'docker run -p 3080:3080 -d izhank53/react-nodejs-example:latest'
                    sshagent(['AzureVmKey']) {
                        sh " ssh -o StrictHostKeyChecking=no -r * azure@57.159.27.246 ${dockerCMD}"
                        
                    }
                }
            }
        }
    }
}
