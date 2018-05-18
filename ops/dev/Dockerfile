FROM ubuntu:16.04
MAINTAINER The Blue Alliance

# Set debconf to run non-interactively
RUN echo 'debconf debconf/frontend select Noninteractive' | debconf-set-selections

# Get base apt dependencies
RUN apt-get update && apt-get install -y \
  git \
  wget \
  unzip \
  tar \
  sudo \
  jq \
  tmux \
  vim \
  openssh-server

# Get android dependencies
RUN apt-get install -y openjdk-8-jdk

ENV GRADLE_VERSION 4.6
RUN cd /opt && \
    wget -q https://services.gradle.org/distributions/gradle-${GRADLE_VERSION}-bin.zip && \
    unzip gradle*.zip && \
    ls -d */ | sed 's/\/*$//g' | xargs -I{} mv {} gradle && \
    rm gradle*.zip

ENV ANDROID_HOME /usr/local/android-sdk
ENV ANDROID_SDK_VERSION 24.4.1
ENV ANDROID_VERSION 24
ENV BUILD_TOOLS_VERSION 25.2.3

# Install Android SDK
RUN wget -q https://dl.google.com/android/android-sdk_r${ANDROID_SDK_VERSION}-linux.tgz
RUN tar -xvzf android-sdk_r${ANDROID_SDK_VERSION}-linux.tgz
RUN mv android-sdk-linux $ANDROID_HOME
RUN rm android-sdk_r${ANDROID_SDK_VERSION}-linux.tgz

ENV ANDROID_COMPONENTS platform-tools,android-$ANDROID_VERSION,build-tools-$BUILD_TOOLS_VERSION

# Install Android tools
RUN echo y | $ANDROID_HOME/tools/android update sdk --filter "${ANDROID_COMPONENTS}" --no-ui -a

# Configure ssh server
RUN mkdir /var/run/sshd
RUN echo 'root:tba' | chpasswd
RUN sed -i 's/PermitRootLogin prohibit-password/PermitRootLogin yes/' /etc/ssh/sshd_config
RUN sed 's@session\s*required\s*pam_loginuid.so@session optional pam_loginuid.so@g' -i /etc/pam.d/sshd
ENV NOTVISIBLE "in users profile"
RUN echo "export VISIBLE=now" >> /etc/profile
EXPOSE 22

# Start SSH server
CMD ["/usr/sbin/sshd", "-D"]
