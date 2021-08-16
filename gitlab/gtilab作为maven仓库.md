# gitlab作为maven仓库

## 主要说明
- 公共项目不需要用户认证，私有项目需要用户认证，本说明全部以私有项目为例
- mac上idea装的setting.xml路径： /Applications/IntelliJ IDEA.app/Contents/plugins/maven/lib/maven3/conf/
- 无论是依赖的开发者还是使用者，由于是私有项目都需要有gitlab上该项目的访问权限

## 权限

- 无论是开发者还是使用者，在gitlab中创建访问令牌后(read_repository,write_repository ,api)放入maven的setting.xml文件之中（所有用到该依赖的位置都需要加入）
```xml
<settings>
    <servers>
        <server>
            <id>gitlab-maven</id>
            <configuration>
                <httpHeaders>
                    <property>
                        <name>Private-Token</name>
                        <value>gitlab的token</value>
                    </property>
                </httpHeaders>
            </configuration>
        </server>
    </servers>
</settings>

```


## 编写maven依赖
- 创建maven项目（不是springboot项目）在pom.xml加入

```xml
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>aa</groupId>
    <artifactId>bb</artifactId>
    <version>2.0</version>


    <properties>
        <maven.compiler.source>8</maven.compiler.source>
        <maven.compiler.target>8</maven.compiler.target>
    </properties>

    
    
    <repositories>
        <repository>
            <id>gitlab-maven</id>
            <url>https://{gitlab域名}/api/v4/projects/{项目id}/packages/maven</url>
        </repository>
    </repositories>
    <distributionManagement>
        <repository>
            <id>gitlab-maven</id>
            <url>https://{gitlab域名}/api/v4/projects/{项目id}/packages/maven</url>
        </repository>
        <snapshotRepository>
            <id>gitlab-maven</id>
            <url>https://{gitlab域名}/api/v4/projects/{项目id}/packages/maven</url>
        </snapshotRepository>
    </distributionManagement>
</project>
```
- 执行 maven deploy即可上传成功

## 使用maven依赖
- 在setting.xml添加权限内容之后，在pom.xml添加如下内容即可
```xml

<groupId>aa</groupId>
<artifactId>bb</artifactId>
<version>2.0</version>

 <repositories>
        <repository>
            <id>gitlab-maven</id>
            <url>https://{gitlab域名}/api/v4/projects/{项目id}/packages/maven</url>
        </repository>
    </repositories>
```