package io;

import interfaces.OutputWriter;

public class Writer implements OutputWriter {
    public void print(String output) {
        System.out.print(output);
    }

    public void println(String output) {
        System.out.println(output);
    }
}
