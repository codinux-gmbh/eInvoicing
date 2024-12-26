
../gradlew build -Dquarkus.package.type=native -Dquarkus.native.container-build=true -x test &&

# do not use Dockerfile.native-micro has it doesn't have glibc which is required by ERiC libraries
docker build -f src/main/docker/Dockerfile.native-micro -t docker.dankito.net/dankito/invoicing:latest . &&

docker push docker.dankito.net/dankito/invoicing:latest