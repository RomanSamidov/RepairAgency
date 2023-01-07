package com.myCompany.RepairAgency.servlet.tag;

import jakarta.servlet.jsp.JspException;
import jakarta.servlet.jsp.JspWriter;
import jakarta.servlet.jsp.tagext.TagSupport;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@SuppressWarnings("serial")
public class InfoTimeTag extends TagSupport {
    @Override
    public int doStartTag() throws JspException {
        LocalDateTime gc = LocalDateTime.now();
        String time = "<hr/><b> " + gc.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + " </b><hr/>";
        try {
            JspWriter out =
                    pageContext.getOut();
            out.write(time);
        } catch (IOException e) {
            throw new JspException(e.getMessage());
        }
        return SKIP_BODY;
    }
    @Override
    public int doEndTag(){
        return EVAL_PAGE;
    }
}