node {
   def mvnHome
   stage('Preparation') { 
            mvnHome = tool 'Maven'
            dir('ClaynFilesystem2') {
                if (isUnix()) {
                sh "'${mvnHome}/bin/mvn' clean"
            } else {
                bat(/"${mvnHome}\bin\mvn" clean/)
            }
            }
             
        }
   dir('ClaynFilesystem2') {
        
		stage('Build') {
            if (isUnix()) {
                sh "'${mvnHome}/bin/mvn' -DskipTests compile"
            } else {
                bat(/"${mvnHome}\bin\mvn" -DskipTests compile/)
            }
        }
        stage('Test') {
            if (isUnix()) {
                sh "'${mvnHome}/bin/mvn' -Dmaven.test.failure.ignore=true test"
            } else {
                bat(/"${mvnHome}\bin\mvn" -Dmaven.test.failure.ignore=true test/)
            }
        }
        
        stage('Reporting') {
            if (isUnix()) {
                sh "'${mvnHome}/bin/mvn' -DskipTests site"
            } else {
                bat(/"${mvnHome}\bin\mvn" -DskipTests site/)
            }
        }
        stage('Results') {
            junit allowEmptyResults: true, testResults: '**/TEST-*.xml'
            //jacoco()
        }
        stage('Deployment') {
            if (isUnix()) {
                sh "'${mvnHome}/bin/mvn' -DskipTests install"
            } else {
                bat(/"${mvnHome}\bin\mvn" -DskipTests install/)
            }
        }
   }
}