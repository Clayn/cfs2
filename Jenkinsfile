pipeline {
    agent any
	tools { 
        maven 'Maven'
    }
    stages {
		stage('Prepare') {
				dir('ClaynFileSystem2') {
					if (isUnix()) {
						sh "mvn clean"
					} else {
						bat(/mvn clean/)
					}
				}
			
		}
        stage('Build') {
                dir('ClaynFileSystem2') {
					if (isUnix()) {
						sh "/mvn -DskipTests compile"
					} else {
						bat(/mvn -DskipTests compile/)
					}
				}
            
        }
        stage('Test') {
                dir('ClaynFileSystem2') {
					if (isUnix()) {
						sh "mvn -Dmaven.test.failure.ignore=true test"
					} else {
						bat(/mvn -Dmaven.test.failure.ignore=true test/)
					}
				}
            
        }
        stage('Deploy') {
                dir('ClaynFileSystem2') {
					if (isUnix()) {
						sh "mvn -DskipTests install"
					} else {
						bat(/mvn -DskipTests install/)
					}
				}
            
        }
    }
}