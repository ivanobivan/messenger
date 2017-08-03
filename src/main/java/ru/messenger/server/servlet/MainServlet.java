package ru.messenger.server.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MainServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       /* BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(request.getInputStream()));
        while (bufferedReader.ready()){
            String line = bufferedReader.readLine();
            Client newClient =  JSONParse.parseString(line);
            if(newClient.getPassword().equals("1") && newClient.getUserName().equals("123")){
                response.setContentType("application/text");
                response.getWriter().print("YES");
            }else{
                response.setContentType("application/text");
                response.getWriter().print("NO");
            }
        }*/
    }
}
