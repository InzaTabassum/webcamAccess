package com.example;

import nu.pattern.OpenCV;
import org.opencv.core.Mat;
import org.opencv.videoio.VideoCapture;

import javax.swing.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;

public class App {

    public static void main(String[] args) {

        OpenCV.loadLocally();

        VideoCapture camera = new VideoCapture(0);

        JFrame window = new JFrame("My Camera");
        JLabel label = new JLabel();

        window.add(label);
        window.setSize(640, 480);
        window.setVisible(true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Mat frame = new Mat();

        while (window.isVisible()) {

            camera.read(frame);

            BufferedImage image = new BufferedImage(frame.cols(), frame.rows(), BufferedImage.TYPE_3BYTE_BGR);

            byte[] data = new byte[(int) frame.total() * frame.channels()];

            frame.get(0, 0, data);

            byte[] pixels =
                    ((DataBufferByte) image.getRaster().getDataBuffer()).getData();

            System.arraycopy(data, 0, pixels, 0, data.length);

            label.setIcon(new ImageIcon(image));
        }

        camera.release();
    }
}