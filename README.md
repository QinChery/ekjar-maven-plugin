# ekjar-maven-plugin

用于加密 Drools 的规制文件，在规则读取时进行解密，重写 Drools 中的 ByteArrayResource 类可以配合完成此操作

使用方式：

```xml
<!-- 每一次都强制覆盖原有的 resources 文件 -->
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-resources-plugin</artifactId>
    <version>3.0.2</version>
    <configuration>
        <overwrite>true</overwrite>
    </configuration>
</plugin>
<!-- 打包开始之前对所有的 drl 文件进行加密处理 -->
<plugin>
    <groupId>com.qchery</groupId>
    <artifactId>ekjar-maven-plugin</artifactId>
    <version>1.0-SNAPSHOT</version>
    <executions>
        <execution>
            <goals>
                <goal>encrypt</goal>
            </goals>
            <phase>prepare-package</phase>
        </execution>
    </executions>
</plugin>
```
