package com.qchery.ekjar.encrypt;

/**
 * 默认加密Provider
 *
 * @author Chery
 * @version 1.0.0
 * @date 2017/5/23
 */
public class DefaultEncryptProvider extends EncodeProvider {

    @Override
    public byte[] encrypt(byte[] plainBytes) {
        return plainBytes;
    }

}
