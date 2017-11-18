package org.firstinspires.ftc.teamcode;

/**
 * Created by rommac100 on 11/9/17.
 */

import org.firstinspires.ftc.robotcore.external.matrices.VectorF;
import org.opencv.core.Mat;

/**
 * Created by vandejd1 on 8/29/16.
 * FTC Team EV 7393
 */
public class CryptoBoxProcessor implements ImageProcessor<RelicRecoveryResult> {
    private static final String TAG = "BeaconProcessor";

    @Override
    public ImageProcessorResult<RelicRecoveryResult> process(long startTime, Mat rgbaFrame, boolean saveImages) {

        //<The code we write will go here>


        double LeftPosX = 0;
        double RightPosX = 0;
        double CenterPosX = 0;


        return new ImageProcessorResult<>(startTime, rgbaFrame,
                new RelicRecoveryResult()
        );

    }
}
