package com.smart.common.exception.file;

/**
 * @description: 文件名大小限制异常类
 * @author: SanDuo
 * @date: 2020/5/22 18:00
 * @version: 1.0
 */
public class FileSizeLimitExceededException extends FileException {
    private static final long serialVersionUID = 1L;

    public FileSizeLimitExceededException(long defaultMaxSize) {
        super("upload.exceed.maxSize", new Object[]{defaultMaxSize});
    }
}
