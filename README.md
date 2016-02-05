# matrix-2

This project intends to build a Matrix class for Java.

Because I despise the [Eclipse IDE](https://eclipse.org/downloads/), I am doing this project through command line [Git](https://git-scm.com/) with [Atom.io](https://atom.io/).
This project is [Maven](https://maven.apache.org/) based and requires maven to be installed in order to package, test, run, etc...

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

**After packaging this project**, you will be able to run the Hello World application using the packaged **shaded** jar.

```bash
java -cp ./target/Javatrix-1.0-SNAPSHOT-shaded.jar cbyt.matrix.Matrix
```

## Testing this Project

To test this project, simply use the test feature included in the `mvn` utility.

```bash
mvn test
...
```

**Note** that this project must be packaged before testing can occur.

## Deliver this Project

After prompting the `mvn` utility to package this project, distribute the `Javatrix-1.0-SNAPSHOT-shaded.jar` inside the `target/` directory.
This jar contains the required dependencies and project classes required for running the project.

In order to run the project see [Running this Project](#running-this-project)
