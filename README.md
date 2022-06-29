# ju-session-mechanism-ex
Java Ultimate| Homework #13

A custom Session Mechanism
1. Create a new maven project
2. Add Servlet API dependency, maven war plugin, and configure war packaging
3. Configure deployment (using Tomcat)
4. Implement an EveningServlet that accepts your name as a query param and responds with a greeting "Good evening, {yourName}" use "Buddy" if no name was provided
5. Make sure it works in browser
6. Add a session, and store a name, so the server remembers who you are
7. Implement a custom session mechanism – instead of using HttpServletRequest#getSession create your own custom solution, that will keep track session attributes for each session. 
