pipeline{
   
        agent any
        tools{
            maven 'maven 3.8.2'
            jdk 'JDK 1.8'
        }
        
        stages{
            stage('Build Maven'){
                steps{
                    checkout([$class: 'GitSCM', branches: [[name: '*/testbranch']], extensions: [], userRemoteConfigs: [[credentialsId: 'ed8c467f-1450-4e94-9be8-ade7fbfb88b0', url: 'https://github.com/hhafzahh/DevopS.git']]])
                    bat 'mvn -f DevopS/pom.xml clean install test'
                }
            }
            
            //assuming startup.bat is running
            stage('Deploy To Tomcat'){
                steps{
                    script{ 
                       deploy adapters: [tomcat9(credentialsId: 'f7add778-3ac8-40fb-a07a-bc52030d335d', path: '', url: 'http://localhost:8090')], contextPath: 'usermanagement-devops', onFailure: false, war: '**/*.war'
                    }
                }
            }
        }
    
}
