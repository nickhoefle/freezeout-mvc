package org.nickhoefle.freezeoutmvc;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.nickhoefle.freezeoutmvc.controllers.AuthenticationController;
import org.nickhoefle.freezeoutmvc.data.UserRepository;
import org.nickhoefle.freezeoutmvc.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.HandlerInterceptor;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Controller
public class AuthenticationFilter implements HandlerInterceptor {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationController authenticationController;

    private static final List<String> blacklist = Arrays.asList("/admin");

    // Override prehandle method to see if a user is logged in and in a session before allowing get/post requests
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        // is the uri part of the whitelist?
        if (isBlacklisted(request.getRequestURI())) {
            return true;
        }
        HttpSession session = request.getSession();

        User user = authenticationController.getUserFromSession(session);

        if (user != null) {
            return true;
        }
        response.sendRedirect("/login");
        return false;
    }

    //Check if the address Starts with /pathRoot
    private static boolean isBlacklisted(String path) {
        for (String pathRoot : blacklist) {
            if (!path.startsWith(pathRoot)) {
                return true;
            }
        }
        return false;
    }

}
