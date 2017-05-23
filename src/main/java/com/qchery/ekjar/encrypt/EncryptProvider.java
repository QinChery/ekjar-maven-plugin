package com.qchery.ekjar.encrypt;

/**
 * 加密策略
 *
 * @author Chery
 * @version 1.0.0
 * @date 2017/5/23
 */
public interface EncryptProvider {

    /**
     * 加密成字符串
     *
     * @param plainBytes 明文字节
     * @return 加密后明文
     */
    String encryptToString(byte[] plainBytes);

}
