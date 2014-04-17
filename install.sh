#!/bin/bash

configPath=/usr/share/metalcon/middleware

sudo mkdir -p $configPath
sudo chown `whoami`:users $configPath
cp main.cfg $configPath
