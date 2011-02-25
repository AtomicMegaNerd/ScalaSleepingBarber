#!/bin/bash

SRC=src/sleepingbarber
BIN=bin
FLAGS=-d

# The list of scala files to build
FILES=`ls $SRC/*.scala`

# Run clean first
./clean.sh

echo "Building Sleeping Barber Program"
echo "fsc -d $BIN $FILES"
fsc -d $BIN $FILES

echo "Complete."
