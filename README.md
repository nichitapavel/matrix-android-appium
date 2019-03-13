# Appium control of the multiplication app in Android

## Requirements
- [gradle 5.1](https://gradle.org/releases/)
- [oracle/open jdk => v1.8_u191](https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)


## Appium
An appium server with an Android device connected and with the matrix app installed must be previously configured.
This topic is not trivial, hopefully with *npm install* package can be somehow alleviated.  
The appium server (*hub*) and android device details must be copied into **appium-conf.json** file, just
change the available fields.  
**TODO** add npm install package for appium.


## Instructions
After cloning use these commands to compile
- `./gradlew build`

Make use of the compiled app:
- go to *build/distributions/*
- extract either **.zip** or **.tar** archive
- go to *matrix-android-appium-<version>/bin/*, here are the executables **matrix-android-appium**/
**matrix-android-appium.bat**
- execute by typing `./matrix-android-appium 10 10 true` or `matrix-android-appium 10 10 true` 
- keep in mind that **appium-conf.json** must be where you execute


## Arguments
The app has 3 arguments, all are required. These arguments are send to the Android app for control. 
- **String** type **Integer** tells the size of the matrixes (only square matrixes are possible)
- **String** type **Integer** tells the maximum value of a coordinate, i.e.: if this value is 40 you
will never have in the matrix a number equal or higher to 40
- **String** type **boolean** `false` or `true` tells if you want the matrixes to be printed


## Oracle vs Open JRE
By default the generated binary uses java runtime returned from `which java`, usually this is **Open JRE**. If you want
to use Oracle JRE you must specify JAVA_HOME to that runtime, i.e.: 
`JAVA_HOME=/opt/jre1.8.0_202 ./matrix-android-appium 10 10 true`

## Current version: 0.2
