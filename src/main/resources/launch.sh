#!/usr/bin/env bash

export DIR="/home/root/listen_to_atlas"

echo "*** Starting to launch program ***"

    cd $DIR

echo "Launching jar via java command"

    java -jar listen-to-atlas.jar $@

    sleep 1

echo "*** Finished program ***"