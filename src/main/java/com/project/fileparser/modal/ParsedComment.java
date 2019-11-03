package com.project.fileparser.modal;

import java.util.ArrayList;
import java.util.List;

public class ParsedComment {
    private List<LineComment> lineComments;
    private List<BlockComment> blockComments;
    private int totalLineNum;
    private int totalTODONum;
    private String fileName;

    public ParsedComment() {
        totalLineNum = 0;
        totalTODONum = 0;
    }

    /**
     * add a line comment into this object.
     * @param lineComment
     */
    public void addLineComment(LineComment lineComment) {
        if (lineComments == null) {
            this.lineComments = new ArrayList<>();
        }
        lineComments.add(lineComment);
        totalLineNum++;
        totalTODONum += lineComment.hasTODO() ? 1 : 0;
    }

    /**
     * add a block comment into this object.
     * @param blockComment
     */

    public void addBlockComment(BlockComment blockComment) {
        if (blockComments == null) {
            this.blockComments = new ArrayList<>();
        }
        blockComments.add(blockComment);
        totalLineNum += blockComment.getNumOfLines();
        totalTODONum += blockComment.getNumTODO();
    }

    public int getNumberOfLineComments() {
        return lineComments.size();
    }

    public int getNumberOfBlockComments() {
        return blockComments.size();
    }

    public List<LineComment> getLineComments() {
        return lineComments;
    }

    public List<BlockComment> getBlockComments() {
        return blockComments;
    }

    public int getTotalLineNum() {
        return totalLineNum;
    }

    public void setTotalLineNum(int totalLineNum) {
        this.totalLineNum = totalLineNum;
    }

    public int getTotalTODONum() {
        return totalTODONum;
    }

    public void setTotalTODONum(int totalTODONum) {
        this.totalTODONum = totalTODONum;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    /*
    public String toString() {
        String result = "======== All line comments: ========================" + System.lineSeparator();
        result += System.lineSeparator();
        for (LineComment lineComment: lineComments) {
            result += lineComment.toString();
            result += System.lineSeparator();
        }

        result += "======== All block comments: ========================" + System.lineSeparator();
        result += System.lineSeparator();
        for (BlockComment blockComment: blockComments) {
            result += blockComment.toString();
            result += System.lineSeparator();
        }
        result += System.lineSeparator();
        result += "======== Total number of lines: " + totalLineNum  + System.lineSeparator();
        result += System.lineSeparator();
        result += "======== Total number of TODOs: " + totalTODONum  + System.lineSeparator();

        return result;
    }*/
}
