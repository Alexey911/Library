package com.zhytnik.library.web.old;

import com.zhytnik.library.web.old.model.Model;
import com.zhytnik.library.web.old.view.RedirectView;
import com.zhytnik.library.web.old.view.View;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static java.util.Objects.isNull;

public class ViewDispatcher {
    public static final String REDIRECTION = "redirection";

    public static final String PREFIX = "/app";

    private ServletContext servletContext;
    private Map<String, String> resourcesPaths;

    public ViewDispatcher() {
        resourcesPaths = new HashMap<>();
        resourcesPaths.put("/categories/show", "/categories/show.jsp");
        resourcesPaths.put("/category/edit", "/categories/edit.jsp");
        resourcesPaths.put("/category/add", "/categories/add.jsp");
        resourcesPaths.put("/error", "/error.jsp");
    }

    public void setContext(ServletContext context) {
        this.servletContext = context;
    }

    public void dispatch(HttpServletRequest request, HttpServletResponse response,
                         View view) throws IOException, ServletException {
        if (hasRedirected(request.getSession())) {
            view = loadRedirected(request.getSession());
        }

        if (hasNeedRedirect(request, view)) {
            sendRedirect(request, response, view);
        } else {
            prepareModelData(request, view);
            forward(request, response, view);
        }
    }

    private boolean hasNeedRedirect(HttpServletRequest request, View view) {
        String uri = request.getPathInfo();
        return !uri.equals(view.getForward());
    }

    private View loadRedirected(HttpSession session) {
        View view = (View) session.getAttribute(REDIRECTION);
        session.removeAttribute(REDIRECTION);
        return view;
    }

    private boolean hasRedirected(HttpSession session) {
        return !isNull(session.getAttribute(REDIRECTION));
    }

    private void prepareModelData(HttpServletRequest request, View view) {
        if (view instanceof Model) {
            Map<String, Object> data = ((Model) view).getData();
            if (!isNull(data)) {
                for (Map.Entry<String, Object> pair : data.entrySet()) {
                    request.setAttribute(pair.getKey(), pair.getValue());
                }
            }
        }
    }

    private void sendRedirect(HttpServletRequest request, HttpServletResponse response,
                              View view) throws IOException {
        boolean isPrepared = !(view instanceof RedirectView);
        if (isPrepared) {
            HttpSession session = request.getSession();
            session.setAttribute(REDIRECTION, view);
        }
        response.sendRedirect(PREFIX.concat(view.getForward()));
    }

    private void forward(HttpServletRequest request, HttpServletResponse response,
                         View view) throws IOException, ServletException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");

        String path = resourcesPaths.get(view.getForward());
        if (isNull(path)) {
            path = resourcesPaths.get("error");
        }

        RequestDispatcher dispatcher = servletContext.getRequestDispatcher(path);
        dispatcher.forward(request, response);
    }
}