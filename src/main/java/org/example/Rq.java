package org.example;

public class Rq {

    private final String cmd;
    private final int id;

    public Rq(String input) {
        String[] parts = input.trim().split(" ");
        this.cmd = parts[0];

        int parseId = 0;

        if (parts.length >= 2) {
            try {
                parseId = Integer.parseInt(parts[1]);
            } catch (NumberFormatException e) {
                parseId = 0;
            }
        }

        this.id = parseId;
    }

    public String getCmd() {
        return cmd;
    }

    public int getId() {
        return id;
    }
}
