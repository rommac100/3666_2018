package org.firstinspires.ftc.teamcode;

import org.corningrobotics.enderbots.endercv.OpenCVPipeline;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

/**
 * Created by rommac100 on 10/29/17.
 */

public class Glyph_Process extends OpenCVPipeline {
    private Colour_Glyph selected_Ball_Colour = Colour_Glyph.Dark;

    public enum Colour_Glyph
    {
        Null(2),
        Light(1),
        Dark(0);

        private final int colour;

        Colour_Glyph(int colour)
        {
            this.colour = colour;
        }

    }


    private Mat hsv = new Mat();
    private Mat thresholded = new Mat();
    private Mat thresholded_rgba = new Mat();


    private final Scalar lower_light = new Scalar(10,5,110);
    private final Scalar upper_light = new Scalar(150,150,150);

    private final Scalar lower_dark = new Scalar(0,46,50);
    private final Scalar upper_dark = new Scalar(45,111,151);

    public void setShowGlyphColour(Colour_Glyph ballColour_temp)
    {
        selected_Ball_Colour = ballColour_temp;
    }

    @Override
    public Mat processFrame(Mat rgba, Mat gray) {

        Imgproc.cvtColor(rgba,hsv, Imgproc.COLOR_RGB2HSV, 3);

        if (selected_Ball_Colour == Colour_Glyph.Dark) {
            Core.inRange(hsv, lower_dark,upper_dark, thresholded);

            Imgproc.cvtColor(thresholded, thresholded_rgba, Imgproc.COLOR_GRAY2RGBA);
            return thresholded_rgba;
        }
        else if (selected_Ball_Colour == Colour_Glyph.Light)
        {
            Core.inRange(hsv,lower_light,upper_light, thresholded);

            Imgproc.cvtColor(thresholded, thresholded_rgba, Imgproc.COLOR_GRAY2RGBA);
            return thresholded_rgba;
        }
        else
        {
            return rgba;
        }

    }
}
