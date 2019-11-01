package com.project.fileparser.utils;

import com.project.fileparser.modal.RegexComment;

public class RegexProvider {
    public static final String REGEX_BLOCK_COMMENT_START = "(([^\"//]*\"[^\"//]*\")*[^\"]*)(\\/\\*.*)";
    public static final String REGEX_BLOCK_COMMENT_END = "(([^\"//]*\"[^\"//]*\")*[^\"]*)(\\*\\/)(.*)";
    public static final String REGEX_SINGLE_LINE_COMMENT = "(([^\"]*\"[^\"]*\")*[^\"]*)(//.*)";

    //php line comment
    public static final String PHP_REGEX_SINGLE_LINE_COMMENT = "(([^\"(?://|#)]*\"[^\"(?://|#)]*\")*[^\"(?://|#)]*)((?://|#).*)";

    // python comment
    public static final String PYTHON_REGEX_BLOCK_COMMENT_START = "(([^\"#]*\"[^\"#]*\")*[^\"#]*)(\"\"\".*)";
    public static final String PYTHON_REGEX_BLOCK_COMMENT_END = "(([^\"#]*\"[^\"#]*\")*[^\"#]*)(\"\"\")(.*)";
    public static final String PYTHON_REGEX_SINGLE_LINE_COMMENT = "(([^\"]*\"[^\"]*\")*[^\"]*)(#.*)";

    public static RegexComment getRegex(String extension) {
        if (extension == null) {
            return null;
        }
        if (extension.equals(FileType.JAVA) || extension.equals(FileType.CPP) || extension.equals(FileType.C)
            || extension.equals(FileType.JAVASCRIPT)) {
            return new RegexComment(REGEX_SINGLE_LINE_COMMENT,
                                    REGEX_BLOCK_COMMENT_START,
                                    REGEX_BLOCK_COMMENT_END);
        }
        else if (extension.equals(FileType.PHP)) {
            return new RegexComment(PHP_REGEX_SINGLE_LINE_COMMENT,
                                    REGEX_BLOCK_COMMENT_START,
                                    REGEX_BLOCK_COMMENT_END);
        }
        else if (extension.equals(FileType.PYTHON)) {
            return new RegexComment(PYTHON_REGEX_SINGLE_LINE_COMMENT,
                                    PYTHON_REGEX_BLOCK_COMMENT_START,
                                    PYTHON_REGEX_BLOCK_COMMENT_END);
        }

        return null;
    }
}
