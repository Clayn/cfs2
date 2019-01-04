# CFS 2
[![Build Status](https://travis-ci.org/Clayn/cfs2.svg?branch=development)](https://travis-ci.org/Clayn/cfs2)

The CFS 2 (ClaynsFileSystem not a good name though) is the second version of  an abstract file access layer. 
It provides an API to virtual directories and files. While e.g. the Java IO and NIO packages doesn't distingush between files and directories (besides some checks if a file/path is a directory which fails if the directory doesn't exist) this project gives you a clear view if you have a directory or file. But why "virtual"? Because the files don't need to exist on the filesystem of the OS but can be anything that fits the API. For example an implementation can use a database to store the directories and files and programs using the API won't care (besides of the creation of the filesystem).

# Features
 - Different classes for directories and files
 - Changable implementation for different use cases
 - Easy directory watching for file events
 - Access restriction (Can't leave the root directory)

# Content
This repository contains several Maven porjects which each one having a specific purpose

- **cfs2** Parent Maven project that contains the different modules and provides several common dependencies such as the logging framework (SLF4J)


<!--stackedit_data:
eyJoaXN0b3J5IjpbMTQyMTQxNjE3LDIxMTU5ODc5MjAsMTg4Mz
M3OTYyOV19
-->