# matrix-2

This project intends to build a Matrix class for Java.

[![Build Status](https://travis-ci.org/CompassSoftware/matrix-2.svg?branch=master)](https://travis-ci.org/CompassSoftware/matrix-2)
[![codecov.io](https://codecov.io/github/CompassSoftware/matrix-2/coverage.svg?branch=master)](https://codecov.io/github/CompassSoftware/matrix-2?branch=master)

---

Because I despise the [Eclipse IDE](https://eclipse.org/downloads/), I am doing this project through command line [Git](https://git-scm.com/) with [Atom.io](https://atom.io/).
This project is [Maven](https://maven.apache.org/) based and requires maven to be installed in order to package, test, run, etc...

You can find our Pivotal Tracker storyboard [here](https://www.pivotaltracker.com/n/projects/1519179).

## Releases

We provide prepackaged shaded and non-shaded jars of this project every release cycle.
The difference between shaded and non-shaded jars is that shaded jars contain the required maven dependencies prepackaged alongside the base project classes.
Non-shaded jars, however, **only** contain our project's classes.
Shaded jars are meant for use in projects where dependency duplication doesn't matter.

The most recent release cycle ended **2016-02-21**.

|Version|Released|Updated|Shaded|Non-Shaded|
|:------|:-------|:------|:-----|:-------|
|1.0|2016-02-21|2016-02-21|[Download](https://mega.nz/#!w40DEDgb!rN-Ja50C-RizONFbcSLU1NEaam3mi0Eu-PoEKMcSLdw)|[Download](https://mega.nz/#!Yxd0wIqB!7WJdMTB8EbaCkQMhpq3pXdY0pEf7VEkM6kkubM-0E4o)|

## Install Maven

To install Maven, I suggest you follow the official guide: <https://maven.apache.org/install.html>.

## Packaging this Project

To package this project, you simply need to use the `mvn` utility's package feature in the `pom.xml` directory.

```bash
git clone https://github.com/CompassSoftware/matrix-2.git
cd ./matrix-2/Javatrix/
mvn package
...
```
After executing the Maven package build, Maven will spit out a bunch of information regarding the building and testing of this project.
All of the packaged resources get thrown into a new directory named `target/`.

## Running this Project

**After packaging this project**, you can run the Matrix usage example class using the packaged **shaded** jar.

```bash
javac -cp ./target/Javatrix-1.0-SNAPSHOT-shaded.jar ./examples/MatrixExample.java
java -cp .:./target/Javatrix-1.0-SNAPSHOT-shaded.jar examples.MatrixExample
```

## Testing this Project

To test this project, simply use the test feature included in the `mvn` utility.

```bash
mvn test
...
```

**Note** that this project must be packaged before testing can occur.

## Deliver this Project

After prompting the `mvn` utility to package this project, distribute the `Javatrix-X.X-SNAPSHOT-shaded.jar` inside the `target/` directory.
This jar contains the required dependencies and project classes required for running the project.

In order to run the project see [Running this Project](#running-this-project)
