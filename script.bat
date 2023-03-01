@echo off

jjtree parse.jj && javacc parse.jj.jj && javac parser.java && java Parser && pause