#!/bin/bash

# Generate the JJTree parser
jjtree parse.jj

# Generate the JavaCC parser
javacc parse.jj.jj

# Compile the Java source code
javac Parser.java

# Run the Java application
java Parser