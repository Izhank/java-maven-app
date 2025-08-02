def buildJar() {
    echo "Building the application..."
    def mvnHome = tool 'maven-3.9'
    sh "${mvnHome}/bin/mvn clean package -DskipTests"
}

def buildImage() {
    echo "Building the Docker image..."
    withCredentials([usernamePassword(credentialsId: 'DockerHub-Credentials', passwordVariable: 'PASS', usernameVariable: 'USER')]) {
        def imageTag = "izhank53/demo-app:${env.BUILD_NUMBER}"
        sh "docker build -t ${imageTag} ."
        sh "echo $PASS | docker login -u $USER --password-stdin"
        sh "docker push ${imageTag}"

        // Optional: also push latest tag
        sh "docker tag ${imageTag} izhank53/demo-app:latest"
        sh "docker push izhank53/demo-app:latest"
    }
}

def deployApp() {
    echo 'Deploying the application...'
}

return this

