package com.pastexplorehub.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandler {

    // 1. Handle Login Failures (Redirect back to login with error)
    @ExceptionHandler(InvalidCredentialsException.class)
    public ModelAndView handleInvalidCredentials(InvalidCredentialsException ex) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("error", ex.getMessage());
        mav.setViewName("login"); // Returns to login.jsp
        return mav;
    }

    // 2. Handle Registration Errors (For API/AJAX calls)
    @ExceptionHandler(UserAlreadyExistsException.class)
    public String handleUserExists(UserAlreadyExistsException ex, Model model) {
        model.addAttribute("error", ex.getMessage());
        return "register"; // Returns to register.jsp
    }

    // 3. Catch-all for any other unexpected errors
    @ExceptionHandler(Exception.class)
    public ModelAndView handleGlobalException(Exception ex) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("errorMessage", "Something went wrong: " + ex.getMessage());
        mav.setViewName("error"); // You should have an error.jsp page
        return mav;
    }
}