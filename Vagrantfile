# -*- mode: ruby -*-
# vi: set ft=ruby:expandtab:tabstop=2:softtabstop=2

Vagrant.require_version "> 1.8.1"

# Install vagrant plugin
# From: https://github.com/hashicorp/vagrant/issues/1874#issuecomment-165904024
# @param: plugin type: Array[String] desc: The desired plugin to install
def ensure_plugins(plugins)
  logger = Vagrant::UI::Colored.new
  result = false
  plugins.each do |p|
    pm = Vagrant::Plugin::Manager.new(
      Vagrant::Plugin::Manager.user_plugins_file
    )
    plugin_hash = pm.installed_plugins
    next if plugin_hash.has_key?(p)
    result = true
    logger.warn("Installing plugin #{p}")
    if not system "vagrant plugin install #{p}"
      logger.error("Unable to install plugin #{p}")
      exit -1
    end
  end
  if result
    logger.warn('Re-run vagrant up now that plugins are installed')
    exit
  end
end

# Make sure we have all the proper plugins installed
ensure_plugins(["vagrant-triggers"])

Vagrant.configure("2") do |config|

  # Sync the TBA code directory
  config.vm.synced_folder "./", "/tba",
    type: "rsync",
    owner: "root",
    group: "root",
    rsync__rsync_path: "rsync",
    rsync__exclude: [
      ".git/",
    ],
    rsync__auto: true

  # Ports to forward
  ports = []

  # Provision with docker
  config.vm.hostname = "tba-android-docker"
  config.vm.provider "docker" do |d|
    d.name = "tba-android"
    d.build_dir = "ops/dev"
    d.ports = ports
    d.has_ssh = true
  end

  # Configure ssh into container
  config.ssh.insert_key = true
  config.ssh.username = "root"
  config.ssh.password = "tba"
  config.ssh.shell = "bash -c 'BASH_ENV=/etc/profile exec bash'"
end
