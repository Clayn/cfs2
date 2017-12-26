pipeline {
    agent any
	def maven=tool 'Maven'
    stages {
		stage('Prepare') {
			steps {
				dir('ClaynFileSystem2') {
					if (isUnix()) {
						sh "'${maven}/bin/mvn' clean"
					} else {
						bat(/"${maven}\bin\mvn" clean/)
					}
				}
			}	
		}
        stage('Build') {
            steps {
                dir('ClaynFileSystem2') {
					if (isUnix()) {
						sh "'${maven}/bin/mvn' -DskipTests compile"
					} else {
						bat(/"${maven}\bin\mvn" -DskipTests compile/)
					}
				}
            }
        }
        stage('Test') {
            steps {
                dir('ClaynFileSystem2') {
					if (isUnix()) {
						sh "'${maven}/bin/mvn' -Dmaven.test.failure.ignore=true test"
					} else {
						bat(/"${maven}\bin\mvn" -Dmaven.test.failure.ignore=true test/)
					}
				}
            }
        }
        stage('Deploy') {
            steps {
                dir('ClaynFileSystem2') {
					if (isUnix()) {
						sh "'${maven}/bin/mvn' -DskipTests install"
					} else {
						bat(/"${maven}\bin\mvn" -DskipTests install/)
					}
				}
            }
        }
    }
}