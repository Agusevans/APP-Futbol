package server;

import spark.Spark;

public class Server {

    private static int getAssignedPort() {
        return Integer.parseInt(System.getenv().getOrDefault("PORT", "8080"));
    }

    public static void main(String[] args) {
        Spark.port(getAssignedPort());
        Router.init();
    }

}