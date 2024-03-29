package com.project.fileparser.modal;


public class LineComment {
    private boolean hasTODO = false;
    private final String TODO = "TODO";
    private String comment;
    private int lineNumber;


    public LineComment(String comment, int lineNumber) {
        setComment(comment);
        if (comment.contains(TODO)) {
            hasTODO = true;
        };
        this.lineNumber = lineNumber;
    }

    public boolean hasTODO() {
        return hasTODO;
    }

    public int getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(int lineNumber) {
        this.lineNumber = lineNumber;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getComment() {
        return comment;
    }
}
