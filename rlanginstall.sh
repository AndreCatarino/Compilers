#!/bin/bash

./rlangbuild.sh
CUR_DIR="$PWD/rlangrun.sh"
MSG="alias rlang='sh"
cd ~
#echo "alias rlang='sh ~/Downloads/C/compiladores-1819-g15/rlangrun.sh'" >> .bashrc
echo "$MSG $CUR_DIR'" >> .bashrc
