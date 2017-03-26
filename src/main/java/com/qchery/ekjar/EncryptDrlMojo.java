package com.qchery.ekjar;

import org.apache.commons.codec.binary.Base64;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import java.io.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * drl 文件加密插件
 *
 * @author Chery
 * @date 2017/3/26 - 下午4:33
 */
@Mojo(name = "encrypt")
public class EncryptDrlMojo extends AbstractMojo {

    private AtomicInteger drlCount = new AtomicInteger();

    /**
     * Directory containing the generated JAR.
     */
    @Parameter(required = true, defaultValue = "${project.build.outputDirectory}")
    private File outputDirectory;

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        getLog().info("加密 " + outputDirectory.getAbsolutePath() + " 下面的所有 drl 规则文件开始...");
        encryptDrls(outputDirectory);
        getLog().info("文件加密结束，共加密 " + drlCount + " 个文件...");
    }

    private void encryptDrls(File file) {
        String fileName = file.getName();


        if (file.isFile() && (fileName.endsWith(".drl") || fileName.endsWith(".rule"))) {
            getLog().info("加密文件： " + fileName);

            ByteArrayOutputStream baos = null;
            BufferedInputStream bis = null;
            try {
                bis = new BufferedInputStream(new FileInputStream(file));
                baos = new ByteArrayOutputStream();

                int buf;
                while ((buf = bis.read()) != -1) {
                    baos.write(buf);
                }

                byte[] bytes = baos.toByteArray();

                String base64String = Base64.encodeBase64String(bytes);

                writeContent(file, base64String);

                drlCount.incrementAndGet();

            } catch (IOException e) {
                getLog().error("Drl 文件处理异常 : " + file.getAbsolutePath(), e);
            } finally {
                closeQuietly(baos);
                closeQuietly(bis);

            }
        }

        if (file.isDirectory()) {
            // 编码所有字文件夹
            File[] files = file.listFiles();
            if (files != null) {
                for (File childFile : files) {
                    encryptDrls(childFile);
                }
            }
        }

    }

    private void writeContent(File file, String base64String) {
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(file);
            fileWriter.write(base64String);
        } catch (IOException e) {
            getLog().error("文件 " + file.getName() + " 写入失败");
        } finally {
            closeQuietly(fileWriter);
        }
    }

    private void closeQuietly(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
            }
        }
    }

}
