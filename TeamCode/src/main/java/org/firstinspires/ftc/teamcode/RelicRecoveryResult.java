package org.firstinspires.ftc.teamcode;



public class RelicRecoveryResult {
    public double CryptoLeft   = -1;
    public double CryptoCenter = -1;
    public double CryptoRight  = -1;

    public double CubePos      = -1;

    public enum JewelOrder{
        RED_BLUE,
        BLUE_RED,
        UNKNOWN
    }
    public JewelOrder Jewels = JewelOrder.UNKNOWN;
}