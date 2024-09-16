package com.smart.common.exception.file;

import com.smart.common.exception.base.BaseException;

/**
 * @description: 文件信息异常类
 * @author: SanDuo
 * @date: 2020/5/22 17:50
 * @version: 1.0
 */
public class FileException extends BaseException {
    private static final long serialVersionUID = 1L;

    public FileException(String code, Object[] args) {
        super("file", code, args, null);
    }

}
