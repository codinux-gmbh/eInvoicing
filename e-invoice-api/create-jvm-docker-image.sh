
../gradlew build -x test &&

docker build -f src/main/docker/Dockerfile.jvm -t docker.dankito.net/dankito/invoicing-jvm:latest . &&

docker push docker.dankito.net/dankito/invoicing-jvm:latest