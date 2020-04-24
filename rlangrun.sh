#!/bin/bash

cd bin

if [ "$#" -eq 0 ]; then
  for file in ../src_code_ex/* ; do
    if [[ -f $file ]]; then
      echo $file
      # antlr4-test RobotLang main $file
      java -ea RobotLangMain $file
    fi
  done
else
  # antlr4-test RobotLang main ../src_code_ex/$1
  java -ea RobotLangMain ../src_code_ex/$1
fi

