job('nodejs-app-wrap-by-docker') {
    scm {
        git('https://github.com/ArmiinJP/jenkins_course.git') {  node -> // is hudson.plugins.git.GitSCM
            node / gitConfigName('DSL User')
            node / gitConfigEmail('jenkins-dsl@newtech.academy')
        }
    }
    triggers {
        scm('H/20 * * * *')
    }

    steps {
        dockerBuildAndPublish {
            repositoryName('arminjp/nodejs-app-pipline-with-jdsl')
            tag('${GIT_REVISION,length=9}')
            dockerfileDirectory("./nodejs_app/Dockerfile")
            forcePull(false)
            forceTag(false)
            createFingerprints(false)
            skipDecorate()
            dockerRegistryURL("http://localhost:5000")
        }
    }
}
