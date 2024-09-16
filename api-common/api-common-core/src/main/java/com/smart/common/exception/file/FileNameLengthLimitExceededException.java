package com.smart.common.exception.file;

/**
 * @description: 文件名称超长限制异常类
 * @author: SanDuo
 * @date: 2020/5/22 18:00
 * @version: 1.0
 */
public class FileNameLengthLimitExceededException extends FileException {
    private static final long serialVersionUID = 1L;

    public FileNameLengthLimitExceededException(int defaultFileNameLength) {
        super("upload.filename.exceed.length", new Object[]{defaultFileNameLength});
    }
}
