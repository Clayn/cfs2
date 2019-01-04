# CFS 2
[![Build Status](https://travis-ci.org/Clayn/cfs2.svg?branch=development)](https://travis-ci.org/Clayn/cfs2)

The CFS 2 (ClaynsFileSystem not a good name though) is the second version of  an abstract file access layer. 
It provides an API to virtual directories and files. While e.g. the Java IO and NIO packages doesn't distingush between files and directories (besides some checks if a file/path is a directory which fails if the directory doesn't exist) this project gives you a clear view if you have a directory or file. But why "virtual"? Because the files don't need to exist on the filesystem of the OS but can be anything that fits the API. For example an implementation can use a database to store the directories and files and programs using the API won't care (besides of the creation of the filesystem).

## Features


<!--stackedit_data:
eyJoaXN0b3J5IjpbMzEyMDAzOTA1LDE4ODMzNzk2MjldfQ==
-->