#!/usr/bin/env bash
set -e
DIR=$(cd "$(dirname \"$0\")" && pwd)
WRAPPER_JAR="$DIR/gradle/wrapper/gradle-wrapper.jar"
if [ ! -f "$WRAPPER_JAR" ]; then
  echo "Downloading Gradle wrapper…"
  TMP_ZIP=$(mktemp)
  curl -L -o "$TMP_ZIP" "https://services.gradle.org/distributions/gradle-8.5-bin.zip"
  unzip -j "$TMP_ZIP" "gradle-8.5/lib/gradle-wrapper.jar" -d "$(dirname \"$WRAPPER_JAR\")"
  rm "$TMP_ZIP"
fi
java -classpath "$WRAPPER_JAR" org.gradle.wrapper.GradleWrapperMain "$@"

