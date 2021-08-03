#!/usr/bin/env bash

cd /home/ec2-user/application
sudo java -jar estoque*.jar > log 2> errors &