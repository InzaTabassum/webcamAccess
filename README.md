# Java Webcam Access with OpenCV and Swing

This is a simple beginner-friendly Java project that opens the computer webcam using **OpenCV** and displays the live camera feed in a **Swing** window.

## What This Project Uses

* **Java** — programming language
* **OpenCV** — accesses the webcam and reads camera frames
* **Swing** — creates the window and displays the camera
* **Maven** — manages the OpenCV dependency

## How the Program Works

The basic flow is:

```text
OpenCV
  ↓
Open Webcam
  ↓
Capture Frame as Mat
  ↓
Get Pixel Data
  ↓
Convert to BufferedImage
  ↓
Display in JLabel
  ↓
Repeat
```

Because frames are captured repeatedly, they appear as live video.

## Important Parts

### Load OpenCV

```java
OpenCV.loadLocally();
```

Loads the OpenCV library so Java can use it.

### Open the Webcam

```java
VideoCapture camera = new VideoCapture(0);
```

`0` means the default webcam.

### Store a Camera Frame

```java
Mat frame = new Mat();
```

`Mat` is OpenCV's container for an image.

### Capture a Frame

```java
camera.read(frame);
```

Reads one image from the webcam and stores it inside `frame`.

### Convert the Frame

OpenCV stores the image as a `Mat`, but Swing needs a `BufferedImage`.

The program copies the pixel data:

```text
Mat
 ↓
byte[]
 ↓
BufferedImage
```

### Display the Image

```java
label.setIcon(new ImageIcon(image));
```

This displays the converted camera image inside the Swing window.

### Keep Showing New Frames

```java
while (window.isVisible()) {
    // capture and display frames
}
```

The program keeps getting new frames while the window is open.

### Release the Camera

```java
camera.release();
```

This releases the webcam when the program finishes.

## Simple Component Guide

```text
VideoCapture  → controls the webcam
Mat           → stores one camera frame
BufferedImage → Java version of the image
ImageIcon     → allows Swing to display the image
JLabel        → holds the image
JFrame        → application window
```

## Running the Project

Run the `App` class.

A window called:

```text
My Camera
```

should appear and display the live webcam feed.

Close the window to stop the application.

## Current Goal

This project is currently focused on understanding the basics of webcam access and displaying live video.

Possible future improvements include:

* Take a photo
* Save photos to the computer
* Add a Take Photo button
* Record video
* Detect faces
* Detect movement
* Add image filters
