package com.example;

import io.micronaut.configuration.picocli.PicocliRunner;
import io.micronaut.context.ApplicationContext;

import org.bytedeco.depthai.*;
import org.bytedeco.javacpp.IntPointer;
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
        ColorCamera camRgb = pipeline.createColorCamera();
        XLinkOut xoutRgb   = pipeline.createXLinkOut();
        xoutRgb.setStreamName("rgb");
        camRgb.setResolution(ColorCameraProperties.SensorResolution.THE_4_K);
        camRgb.setPreviewSize(300, 300);
        camRgb.setInterleaved(false);
        camRgb.setColorOrder(ColorCameraProperties.ColorOrder.BGR);

        camRgb.preview().link(xoutRgb.input());
        Device device = new Device(pipeline, false);
        IntPointer cameras = device.getConnectedCameras();
        System.out.printf("Detect %d camera(s), USB speed: %s \n", cameras.limit(), device.getUsbSpeed());
        for (int i = 0; i < cameras.limit(); i++) {
            System.out.printf("    Camera %d is ready!\n", cameras.get(i));
        }
        device.close();
        pipeline.close();
    }
}
