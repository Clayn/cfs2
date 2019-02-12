# CFS 2
[![Build Status](https://travis-ci.org/Clayn/cfs2.svg?branch=development)](https://travis-ci.org/Clayn/cfs2) [![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

The CFS 2 (ClaynsFileSystem not a good name though) is the second version of  an abstract file access layer. 
It provides an API to virtual directories and files. While e.g. the Java IO and NIO packages doesn't distingush between files and directories (besides some checks if a file/path is a directory which fails if the directory doesn't exist) this project gives you a clear view if you have a directory or file. But why "virtual"? Because the files don't need to exist on the filesystem of the OS but can be anything that fits the API. For example an implementation can use a database to store the directories and files and programs using the API won't care (besides of the creation of the filesystem).

# Features
 - Different classes for directories and files
 - Changeable implementation for different use cases
 - Easy directory watching for file events
 - Access restriction (Can't leave the root directory)

# Content
This repository contains several Maven porjects which each one having a specific purpose

- **cfs2** 
	- Parent Maven project that contains the different modules and provides several common dependencies such as the logging framework (SLF4J)
- **[cfs2-api](https://github.com/Clayn/cfs2/tree/master/ClaynFileSystem2/ClaynFileSystem2-API)**
	- Filesystem API which is recommended to use by applications. Contains also some utility tools build on top of the filesystem classes
- **[cfs2-test](https://github.com/Clayn/cfs2/tree/master/ClaynFileSystem2/ClaynFileSystemTests)**
	- Common test cases that test the API conformity of implementations. This project can be added as test dependency to just extend the abstract test classes (which need a provider for a filesystem to test) and than test your implementation. 
- **[cfs2-impl](https://github.com/Clayn/cfs2/tree/master/ClaynFileSystem2/Local-Impl)** 
	- A basic implementation that uses the Java IO/NIO classes to provide a virtual filesystem that uses the OS filesystem
- **[cfs2-sample](https://github.com/Clayn/cfs2/tree/master/ClaynFileSystem2/ManualTest)** 
	- Contains samples for the usage of the virtual filesystem. Can be used to test different features of the project
<!--stackedit_data:
eyJoaXN0b3J5IjpbMTQ3MjA4NjU4Nyw3NTQ4NDA0MDgsLTE1MT
c1MzUyNTBdfQ==
-->