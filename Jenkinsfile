pipeline {
    agent any
	tools { 
        maven 'Maven'
    }
    stages {
		stage('Prepare') {
			steps {
				dir('ClaynFileSystem2') {
					if (isUnix()) {
						sh "mvn clean"
					} else {
						bat(/mvn clean/)
					}
				}
			}	
		}
        stage('Build') {
            steps {
                dir('ClaynFileSystem2') {
					if (isUnix()) {
						sh "/mvn -DskipTests compile"
					} else {
						bat(/mvn -DskipTests compile/)
					}
				}
            }
        }
        stage('Test') {
            steps {
                dir('ClaynFileSystem2') {
					if (isUnix()) {
						sh "mvn -Dmaven.test.failure.ignore=true test"
					} else {
						bat(/mvn -Dmaven.test.failure.ignore=true test/)
					}
				}
            }
        }
        stage('Deploy') {
            steps {
                dir('ClaynFileSystem2') {
					if (isUnix()) {
						sh "mvn -DskipTests install"
					} else {
						bat(/mvn -DskipTests install/)
						bat(/mvn -DskipTests install/)
						bat(/mvn -DskipTests install/)
					}
				}
            }
        }
    }
}