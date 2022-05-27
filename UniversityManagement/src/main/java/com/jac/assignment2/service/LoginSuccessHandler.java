package com.jac.assignment2.service;

import com.jac.assignment2.*;
import org.springframework.security.core.*;
import org.springframework.security.web.authentication.*;
import org.springframework.stereotype.*;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

@Component
public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws ServletException, IOException {

        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

        String redirectURL = request.getContextPath();

        if (userDetails.hasRole("ADMIN")) {
            redirectURL = "admin";
        } else if (userDetails.hasRole("STUDENT")) {
            redirectURL = "studentDashboard";
        } else if (userDetails.hasRole("PROFESSOR")) {
            redirectURL = "professorDashboard";
        }
        else  redirectURL = "newUser";

        response.sendRedirect(redirectURL);

    }

}