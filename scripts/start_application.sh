#!/usr/bin/env bash
set -e

DEPLOY_TO_ROOT='true'
JAR_LOCATION="/tmp/estoque-0.0.1-SNAPSHOT.jar"

# Copy the WAR file to the webapps directory
java -jar JAR_LOCATION