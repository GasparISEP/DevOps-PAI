FROM jenkins/jenkins:lts

USER root

# ------------------------
# Dependências básicas
# ------------------------
RUN apt-get update && apt-get install -y \
    curl \
    gnupg \
    ca-certificates \
    unzip \
    software-properties-common \
    apt-transport-https \
    lsb-release \
    && apt-get clean && rm -rf /var/lib/apt/lists/*

# ------------------------
# Instalar Docker Compose V2
# ------------------------
RUN curl -SL https://github.com/docker/compose/releases/download/v2.37.1/docker-compose-linux-x86_64 -o /usr/local/bin/docker-compose && \
    chmod +x /usr/local/bin/docker-compose && \
    ln -s /usr/local/bin/docker-compose /usr/bin/docker-compose

# ------------------------
# Docker CLI - instalação oficial Docker CE
# ------------------------
RUN apt-get update && apt-get install -y docker.io

# Dar permissões ao utilizador jenkins para usar docker
RUN usermod -aG docker jenkins

# ------------------------
# Instalar Temurin JDK 21
# ------------------------
RUN curl -L -o /tmp/openjdk.tar.gz https://github.com/adoptium/temurin21-binaries/releases/download/jdk-21.0.1%2B12/OpenJDK21U-jdk_x64_linux_hotspot_21.0.1_12.tar.gz && \
    mkdir -p /opt/jdk && \
    tar -xzf /tmp/openjdk.tar.gz -C /opt/jdk && \
    rm /tmp/openjdk.tar.gz

ENV JAVA_HOME=/opt/jdk/jdk-21.0.1+12
ENV PATH=$JAVA_HOME/bin:$PATH

# ------------------------
# Instalar Maven 3.9.5
# ------------------------
ARG MAVEN_VERSION=3.9.5
RUN curl -fsSL https://archive.apache.org/dist/maven/maven-3/${MAVEN_VERSION}/binaries/apache-maven-${MAVEN_VERSION}-bin.tar.gz | \
    tar -xz -C /opt/ && \
    ln -s /opt/apache-maven-${MAVEN_VERSION} /opt/maven

ENV MAVEN_HOME=/opt/maven
ENV PATH=$MAVEN_HOME/bin:$PATH

# ------------------------
# Instalar Node.js 20
# ------------------------
RUN curl -fsSL https://deb.nodesource.com/setup_20.x | bash - && \
    apt-get install -y nodejs && \
    apt-get clean && rm -rf /var/lib/apt/lists/*

# Voltar para o utilizador jenkins para correr o serviço
USER jenkins
