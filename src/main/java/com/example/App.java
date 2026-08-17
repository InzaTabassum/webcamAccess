package com.example;

import nu.pattern.OpenCV;
import org.opencv.core.Mat;
import org.opencv.videoio.VideoCapture;
import org.opencv.imgcodecs.Imgcodecs;

import javax.swing.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;

public class App {

    public static void startCamera() {

        OpenCV.loadLocally();

        VideoCapture camera = new VideoCapture(0);

        JFrame window = new JFrame("My Camera");
        JButton button = new JButton("Take Photo");
        JLabel label = new JLabel();

        window.add(label);
        window.add(button, "South");
        window.setSize(640, 480);
        window.setVisible(true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Mat frame = new Mat();

        button.addActionListener(e -> {
            String filename = "photo_" + System.currentTimeMillis() + ".jpg";
            Imgcodecs.imwrite(filename, frame);
            System.out.println("Photo saved!");
        });

        while (window.isVisible()) {

            camera.read(frame);

            BufferedImage image =
                    new BufferedImage(
                            frame.cols(),
                            frame.rows(),
                            BufferedImage.TYPE_3BYTE_BGR
                    );

            byte[] data =
                    new byte[(int) frame.total() * frame.channels()];

            frame.get(0, 0, data);

            byte[] pixels =
                    ((DataBufferByte) image
                            .getRaster()
                            .getDataBuffer())
                            .getData();

            System.arraycopy(
                    data,
                    0,
                    pixels,
                    0,
                    data.length
            );

            label.setIcon(new ImageIcon(image));
        }

        camera.release();
    }

    public static void main(String[] args) {
        startCamera();
    }
}