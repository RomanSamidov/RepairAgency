package com.myCompany.RepairAgency.servlet.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class AttributeFSTRService {

    public static void forShowChangePassword(HttpServletRequest request) {
        forShowCreateUserForm(request);
        forShowProfile(request);

        HttpSession session = request.getSession();

        request.setAttribute("login", session.getAttribute("login"));
        session.removeAttribute("login");

        request.setAttribute("email", session.getAttribute("email"));
        session.removeAttribute("email");
    }


    public static void forShowCreateUserForm(HttpServletRequest request) {
        forShowLoginForm(request);

        HttpSession session = request.getSession();

        request.setAttribute("errorEmptyPasswordRepeat", session.getAttribute("errorEmptyPasswordRepeat"));
        session.removeAttribute("errorEmptyPasswordRepeat");

        request.setAttribute("errorEmptyEmail", session.getAttribute("errorEmptyEmail"));
        session.removeAttribute("errorEmptyEmail");
    }

    public static void forShowLoginForm(HttpServletRequest request) {
        HttpSession session = request.getSession();

        request.setAttribute("errorEmptyPassword", session.getAttribute("errorEmptyPassword"));
        session.removeAttribute("errorEmptyPassword");

        request.setAttribute("errorLoginPassMessage", session.getAttribute("errorLoginPassMessage"));
        session.removeAttribute("errorLoginPassMessage");

        request.setAttribute("errorEmptyLogin", session.getAttribute("errorEmptyLogin"));
        session.removeAttribute("errorEmptyLogin");

        request.setAttribute("errorRecaptchaMessage", session.getAttribute("errorRecaptchaMessage"));
        session.removeAttribute("errorRecaptchaMessage");
    }

    public static void forShowOrders(HttpServletRequest request) {
        HttpSession session = request.getSession();

        request.setAttribute("errorOrderTextMessage", session.getAttribute("errorOrderTextMessage"));
        session.removeAttribute("errorOrderTextMessage");

        request.setAttribute("errorOrderClientIdMessage", session.getAttribute("errorOrderClientIdMessage"));
        session.removeAttribute("errorOrderClientIdMessage");

    }

    public static void forShowProfile(HttpServletRequest request) {
        HttpSession session = request.getSession();

        request.setAttribute("confirmationCodeError", session.getAttribute("confirmationCodeError"));
        session.removeAttribute("confirmationCodeError");
    }


    public static void forShowSignupForm(HttpServletRequest request) {
        forShowCreateUserForm(request);
    }
}
