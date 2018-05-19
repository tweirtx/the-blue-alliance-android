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
ENV SDK_TOOLS_VERSION 3859397
ENV ANDROID_VERSION 24
ENV BUILD_TOOLS_VERSION 25.0.2

# Install Android SDK
RUN wget -q https://dl.google.com/android/repository/sdk-tools-linux-${SDK_TOOLS_VERSION}.zip
RUN unzip -q -d $ANDROID_HOME sdk-tools-linux-${SDK_TOOLS_VERSION}.zip
RUN rm sdk-tools-linux-${SDK_TOOLS_VERSION}.zip

# Install Android tools
RUN yes | $ANDROID_HOME/tools/bin/sdkmanager "platform-tools"
RUN yes | $ANDROID_HOME/tools/bin/sdkmanager "build-tools;${BUILD_TOOLS_VERSION}"
RUN yes | $ANDROID_HOME/tools/bin/sdkmanager "platforms;android-${ANDROID_VERSION}"

# Set up local environment variables
RUN echo "export ANDROID_HOME=$ANDROID_HOME" >> /root/.bashrc
RUN echo "export PATH=$PATH:$ANDROID_HOME/tools:$ANDROID_HOME/tools/bin:$ANDROID_HOME/platform-tools" >> /root/.bashrc

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
