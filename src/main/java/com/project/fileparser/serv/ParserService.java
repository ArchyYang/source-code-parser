package com.project.fileparser.serv;

import com.project.fileparser.modal.BlockComment;
import com.project.fileparser.modal.LineComment;
import com.project.fileparser.modal.ParsedComment;
import com.project.fileparser.modal.RegexComment;
import com.project.fileparser.utils.RegexProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ParserService {

    @Autowired
    FileProviderService fileService;

    public ParsedComment getFileInput(MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();
        int i = fileName.lastIndexOf('.');
        if (i <= 0) {
            // Invalid
        }
        String extension = fileName.substring(i+1);
        RegexComment regex = RegexProvider.getRegex(extension);
        if (regex == null) {
            //
        }
        List<String> lines = fileService.provider(file.getInputStream());
        ParsedComment result = parse(lines, regex);
        result.setFileName(fileName);
        return result;
    }


    private ParsedComment parse(List<String> file, RegexComment regexComment) {

        ParsedComment result = new ParsedComment();
        BlockComment blockComment = new BlockComment();
        int lineNumber = 0;
        for (String line: file) {
            lineNumber++;
            String comment;
            // Check if the line is inside block comment.
            if (!blockComment.isEmpty()) {
                comment = findBlockCommentEnd(line, regexComment.getBlockCommentEnd());
                if (comment!=null) {
                    blockComment.addComment(new LineComment(comment,lineNumber));
                    result.addBlockComment(blockComment);
                    blockComment = new BlockComment();
                } else {
                    blockComment.addComment(new LineComment(line,lineNumber));
                };
                continue;
            }

            // Check if the line contains /* ...
            comment = findBlockCommentStart(line, regexComment.getBlockCommentStart());
            if (comment != null) {
                blockComment = new BlockComment();
                blockComment.addComment(new LineComment(comment, lineNumber));
                if (comment.contains("*/")){
                    // inside content
                    result.addBlockComment(blockComment);
                    blockComment = new BlockComment();
                }
                continue;
            }

            // Check if the line has //
            comment = findLineComment(line, regexComment.getSingleLineComment());
            if (comment != null) {
                result.addLineComment(new LineComment(comment, lineNumber));
            }
            //TODO: line is source code.
        }
        result.setTotalLineNum(lineNumber);
        return result;
    }

    /**
     * findBlockCommentEnd
     * @param line
     * @param regex
     * @return
     */

    public String findBlockCommentEnd(String line, String regex) {

        //Pattern endCommentWithTrailingCodePattern = Pattern.compile("(([^\"//]*\"[^\"//]*\")*[^\"]*)(\\*\\/)(.*)");
        Pattern endCommentWithTrailingCodePattern = Pattern.compile(regex);
        Matcher matcher = endCommentWithTrailingCodePattern.matcher(line);
        if (matcher.matches()) {
            // end of block comment
            String comment = matcher.group(1) + matcher.group(3);
            // reach */ block comment end.
            if (matcher.groupCount() >= 5 &&  matcher.group(4) != null) {
                // TODO: matcher.group(4) is a line of source code.
            }
            return comment;
        }
        return null;
    }

    /**
     * findBlockCommentStart
     * @param line
     * @param regex
     * @return
     */
    public String findBlockCommentStart(String line, String regex) {

        Pattern blockCommentStartPattern = Pattern.compile(regex);
        Matcher matcher = blockCommentStartPattern.matcher(line);
        if (matcher.matches()) {
            String comment = matcher.group(3);
            if (comment.equals(matcher.group(0))) {
                // TODO: matcher.group(0) is a line of source code
            }
            return comment;
        }
        return null;
    }

    /**
     * findLineComment
     * @param line
     * @param regex
     * @return
     */
    public String findLineComment(String line, String regex) {
        Pattern lineCommentPattern = Pattern.compile(regex);
        Matcher matcher = lineCommentPattern.matcher(line);
        if (matcher.matches()) {
            String comment = matcher.group(3);
            if (matcher.group(1) != null) {
                // TODO: matcher.group(1) is a line of source code
            }
            return comment;
        }
        return null;
    }


}
