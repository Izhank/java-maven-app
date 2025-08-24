pipeline {
    agent any

    stages {
        stage('Greet') {
            steps {
                echo "Hello friends"
            }
        }

        stage('Deploy') {
            steps {
                sshagent(['AzureVmKey']) {
                    // 1. Copy project files to VM
                    sh '''
                        scp -o StrictHostKeyChecking=no -r Jenkinsfile pom.xml script.groovy src vars azure@57.159.27.246:/home/azure/app
                    '''

                    // 2. Deploy container on VM
                    sh '''
                        ssh -o StrictHostKeyChecking=no azure@57.159.27.246 "
                            docker ps -q --filter 'ancestor=izhank53/react-nodejs-example:latest' | xargs -r docker stop;
                            docker run -d -p 3080:3080 izhank53/react-nodejs-example:latest
                        "
                    '''
                }
            }
        }
    }
}

