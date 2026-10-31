#!/usr/bin/env bash
set -e

# Use the gradle binary from the extracted distribution
DIR="$(pwd)"
GRADLE_BIN="$DIR/gradle/wrapper/gradle-8.5/bin/gradle"

exec "$GRADLE_BIN" "$@"
