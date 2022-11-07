pipeline {
    agent any
    stages {
        stage('Parameters'){
            steps {
                script {
                load('lib/parameters.groovy')
                }
            }
        }
        stage('Validate'){
            steps {
                echo "$perf_config"
                echo "${build_gramine}"
                echo "${gramine_repo_commit_id}"
                echo "${run}"
                echo "${run_specific_perf_test}"
                echo "${exec_mode}"
                echo "${iterations}"
            }
        }
    }
}