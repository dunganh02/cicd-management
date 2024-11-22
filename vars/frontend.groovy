
def call() {

    node {
        stage('Clone source code 1245') { // for display purposes
            withCredentials([file(credentialsId: 'ssh-key', variable: 'ssh_key_file')]) {
                // println(ssh_key_file)
                // writeFile(file: 'test.txt', text: ssh_key_file)
                // sh 'cat $ssh_key_file'
                sh 'cat $ssh_key_file > key'
                sh 'chmod 400 key'
                sh "ssh -o StrictHostKeyChecking=no -i key cicd@34.1.207.215 'cd jhook-fe && git pull'"
            }

        }
        stage('Build 1') {
             withCredentials([file(credentialsId: 'ssh-key', variable: 'ssh_key_file')]) {
                sh 'cat $ssh_key_file > key'
                sh 'chmod 400 key'
                sh "ssh -o StrictHostKeyChecking=no -i key cicd@34.1.207.215 'node -v && npm -v'"
                sh "ssh -o StrictHostKeyChecking=no -i key cicd@34.1.207.215 'cd jhook-fe && npm install'"
                sh "ssh -o StrictHostKeyChecking=no -i key cicd@34.1.207.215 'cd jhook-fe && npm run build'"
            }

        }

       stage('Run application ') {
            withCredentials([file(credentialsId: 'ssh-key', variable: 'ssh_key_file')]) {
                sh 'cat $ssh_key_file > key'
                sh 'chmod 400 key'
                sh "ssh -o StrictHostKeyChecking=no -i key cicd@34.1.207.215 ' cd jhook-fe && npm run dev'"
            }
        }
        stage('Results') {
            println(' >> Result')
            alohaha()
        }
      }
}

def alohaha(){
    println('This is  alohaah')
}
