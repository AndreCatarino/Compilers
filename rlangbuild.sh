#!/bin/bash

antlr4-clean
cp java/* grammar/
cd grammar
antlr4 -visitor RobotLang.g4
antlr4-build
rm RobotLangMain.java
rm STVisitor.java
rm SemanticVisitor.java
mv *.java ../java
mv *.class ../bin
mv *.interp *.tokens ../antlr4
