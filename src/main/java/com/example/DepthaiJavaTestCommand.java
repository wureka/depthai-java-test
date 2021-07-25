package com.example;

import io.micronaut.configuration.picocli.PicocliRunner;
import io.micronaut.context.ApplicationContext;

import org.bytedeco.depthai.Device;
import org.bytedeco.depthai.Pipeline;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

@Command(name = "depthai-java-test", description = "...",
        mixinStandardHelpOptions = true)
public class DepthaiJavaTestCommand implements Runnable {

    @Option(names = {"-v", "--verbose"}, description = "...")
    boolean verbose;

    public static void main(String[] args) throws Exception {
        PicocliRunner.run(DepthaiJavaTestCommand.class, args);
    }

    public void run() {
        // business logic here
        if (verbose) {
            System.out.println("Hi!");
        }
        Pipeline pipeline = new Pipeline();
        Device device = new Device();

    }
}
