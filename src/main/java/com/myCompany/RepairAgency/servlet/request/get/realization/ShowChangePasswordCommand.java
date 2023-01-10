package com.myCompany.RepairAgency.servlet.request.get.realization;


import com.myCompany.RepairAgency.servlet.Path;
import com.myCompany.RepairAgency.servlet.PathFactory;
import com.myCompany.RepairAgency.servlet.request.IActionCommand;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class ShowChangePasswordCommand implements IActionCommand{
    @Override
    public Path execute(HttpServletRequest request, HttpServletResponse response) {
        Path page = PathFactory.getPath("path.page.forward.change_password");
        request.setAttribute("title", "title.change_password");
        copyAttributesFromSessionToRequest(request);

        deleteAttributesFromSession(request);

        return page;
    }


    private void copyAttributesFromSessionToRequest(HttpServletRequest request) {
        request.setAttribute("errorEmptyPassword",
                request.getSession().getAttribute("errorEmptyPassword"));
        request.setAttribute("errorEmptyPasswordRepeat",
                request.getSession().getAttribute("errorEmptyPasswordRepeat"));
        request.setAttribute("errorLoginPassMessage",
                request.getSession().getAttribute("errorLoginPassMessage"));
        request.setAttribute("errorEmptyLogin",
                request.getSession().getAttribute("errorEmptyLogin"));
        request.setAttribute("errorRecaptchaMessage",
                request.getSession().getAttribute("errorRecaptchaMessage"));
        request.setAttribute("errorEmptyEmail",
                request.getSession().getAttribute("errorEmptyEmail"));
        request.setAttribute("confirmationCodeError"
                , request.getSession().getAttribute("confirmationCodeError"));
        request.setAttribute("login",
                request.getSession().getAttribute("login"));
        request.setAttribute("email"
                , request.getSession().getAttribute("email"));
    }

    private void deleteAttributesFromSession(HttpServletRequest request) {
        request.getSession().removeAttribute("errorEmptyPassword");
        request.getSession().removeAttribute("errorEmptyPasswordRepeat");
        request.getSession().removeAttribute("errorEmptyEmail");
        request.getSession().removeAttribute("errorLoginPassMessage");
        request.getSession().removeAttribute("errorEmptyLogin");
        request.getSession().removeAttribute("errorRecaptchaMessage");
        request.getSession().removeAttribute("confirmationCodeError");
        request.getSession().removeAttribute("login");
        request.getSession().removeAttribute("email");
    }
}
