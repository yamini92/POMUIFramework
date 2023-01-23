pipeline{
    agent any
    stages{
        stage("Build"){
            steps{
            echo("Build the project")
            }
        }
        stage("deploy to dev env"){
            steps{
            echo("deploy to dev env")
            }
        }
        stage("Run unit test cases"){
            steps{
            echo("Run unit test cases")
            }
        }
        stage("Deploy to QA env"){
            steps{
            echo("Deploy to QA env")
            }
        }
        stage("Run ATs to QA env"){
            steps{
            echo("Run ATs to QA env")
            }
        }
        stage("Run on stage env"){
            steps{
            echo("Run on stage env")
            }
            
        }
        stage("Run on Prod env"){
            steps{
                echo("Run on Prod env")
            }
            
        }
    }
}