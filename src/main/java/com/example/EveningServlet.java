package com.example;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.*;

@WebServlet("/evening")
public class EveningServlet extends HttpServlet {

    static Map<UUID, Map<String, String>> sessionAttributes = new HashMap<>();
    private static final String JSESSIONID = "JSESSIONID";
    private static final String NAME_ATTRIBUTE = "name";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        var sessionId = getSessionId(req);
        var name = saveOrGetName(sessionId, req.getParameter(NAME_ATTRIBUTE));

        resp.addCookie(new Cookie(JSESSIONID, sessionId.toString()));
        var writer = resp.getWriter();
        writer.printf("Good evening, %s!", name != null ? name : "Buddy");
        writer.flush();

    }

    private UUID getSessionId(HttpServletRequest req) {
        if (req.getCookies() == null) {
            return UUID.randomUUID();
        }
        var sessionIdCookie = Arrays.stream(req.getCookies())
                .filter(c -> JSESSIONID.equals(c.getName()))
                .findFirst();

        return sessionIdCookie.map(c -> UUID.fromString(c.getValue()))
                .orElseGet(UUID::randomUUID);
    }

    private String saveOrGetName(UUID sessionId, String name) {
        var attributes = sessionAttributes.computeIfAbsent(sessionId, m -> new HashMap<>());
        if (name == null) {
            return attributes.get(NAME_ATTRIBUTE);
        } else {
            attributes.put(NAME_ATTRIBUTE, name);
            return name;
        }
    }

}
