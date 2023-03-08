# Simple Turtle Graphics Implementation in JavaFX

This repository contains with a implementation of the Turtle Graphics in JavaFX (Turtle.java) and a simple Example-Usage(IllustrationTurtle.java).


## Publish to GitHub Packages
Publishing with GitHub Action according https://docs.github.com/en/actions/publishing-packages/publishing-java-packages-with-maven.   

## How to use the published artifact von GitHub Packages in another project
Add the following to your settings.xml in your .m2 folder:
    <settings>
      <profiles>
        <profile>
          <id>my</id>
          <repositories>
            <repository>
              <id>central</id>
              <url>https://repo1.maven.org/maven2</url>
            </repository>
            <repository>
              <id>github</id>
              <url>https://maven.pkg.github.com/hftm-inf/*</url>
            </repository>
8          </repositories>
        </profile>
      </profiles>
      <activeProfiles>
        <activeProfile>my</activeProfile>
      </activeProfiles>
      <servers>
        <server>
          <id>github</id>
          <username>simeonlin</username>
          <password>ghp_...</password>
        </server>
      </servers>
    </settings>
  
Add the following to your project pom.xml:
  <dependency>
    <groupId>ch.hftm.dsalg</groupId>
    <artifactId>dsalg_turtle-graphics</artifactId>
    <version>0.3</version>
  </dependency>

  