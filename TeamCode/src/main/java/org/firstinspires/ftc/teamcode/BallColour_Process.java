package org.firstinspires.ftc.teamcode;

import org.corningrobotics.enderbots.endercv.OpenCVPipeline;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

/**
 * Created by rommac100 on 10/29/17.
 */

public class BallColour_Process extends OpenCVPipeline {
    private Colour_Ball selected_Ball_Colour = Colour_Ball.Null;

    public enum Colour_Ball
    {
        Null(2),
        BLUE(1),
        RED(0);

        private final int colour;

        Colour_Ball(int colour)
        {
            this.colour = colour;
        }

    }


    private Mat hsv = new Mat();
    private Mat thresholded = new Mat();
    private Mat thresholded_rgba = new Mat();


    private final Scalar lower_red = new Scalar(50,5,110);
    private final Scalar upper_red = new Scalar(255,150,150);

    private final Scalar lower_blue = new Scalar(90,128,30);
    private final Scalar upper_blue = new Scalar(170,255,255);

    public void setShowBallColour(Colour_Ball ballColour_temp)
    {
        selected_Ball_Colour = ballColour_temp;
    }

    @Override
    public Mat processFrame(Mat rgba, Mat gray) {

        Imgproc.cvtColor(rgba,hsv, Imgproc.COLOR_RGB2HSV, 3);

        if (selected_Ball_Colour == Colour_Ball.RED) {
            Core.inRange(hsv, lower_red,upper_red, thresholded);

            Imgproc.cvtColor(thresholded, thresholded_rgba, Imgproc.COLOR_GRAY2RGBA);
            return thresholded_rgba;
        }
        else if (selected_Ball_Colour == Colour_Ball.BLUE)
        {
            Core.inRange(hsv,lower_blue,upper_blue, thresholded);

            Imgproc.cvtColor(thresholded, thresholded_rgba, Imgproc.COLOR_GRAY2RGBA);
            return thresholded_rgba;
        }
        else
        {
            return rgba;
        }

    }
}
