package server;

import spark.Spark;

public class Server {

    private static int getAssignedPort() {
        String port = System.getenv("PORT");
        if (port != null)
            return Integer.parseInt(port);
        else
            return 8080;
    }

    public static void main(String[] args) {
        Spark.port(getAssignedPort());
        Router.init();
    }

}