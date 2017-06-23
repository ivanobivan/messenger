package main;


public abstract class Parameters {
    public static final int PORT = 5050;
    public static final String LOCAL_IP = "127.0.0.1";
    public static final String HTML_STRING = "HTTP/1.0 200 OK\r\n" +
            "Date: Fri, 31 Dec 1999 23:59:59 GMT\r\n" +
            "Server: Apache/0.8.4\r\n"+
            "Content-Type: text/html\r\n"+
            "Content-Length: 59\r\n"+
            "Expires: Sat, 01 Jan 2000 00:59:59 GMT\r\n"+
            "Last-modified: Fri, 09 Aug 1996 14:21:40 GMT\r\n"+"\r\n"+
            "<TITLE>Example</TITLE>"+
            "<html><body><h1>LOL</h1></body><html>"+
            "<P>Ceci est une page d'exemple.</P>";
}
