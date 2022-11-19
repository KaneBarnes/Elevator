// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.frcteam3255.preferences.SN_DoublePreference;
import com.frcteam3255.preferences.SN_ZeroDoublePreference;
import com.frcteam3255.preferences.SN_ZeroIntPreference;

public final class RobotPreferences {
    public static final SN_ZeroIntPreference zeroIntPref = new SN_ZeroIntPreference();
    public static final SN_ZeroDoublePreference zeroDoublePref = new SN_ZeroDoublePreference();

    public static final class prefElevator {
        public static final SN_DoublePreference elevatorArbitraryFeedForward = new SN_DoublePreference(
                "elevatorArbitraryFeedForward", 0);
        public static final SN_DoublePreference elevatorP = new SN_DoublePreference("elevatorP", 1);
        public static final SN_DoublePreference elevatorI = new SN_DoublePreference("elevatorI", 0);
        public static final SN_DoublePreference elevatorD = new SN_DoublePreference("elevatorD", 0);

        public static final SN_DoublePreference elevatorClosedLoopSpeed = new SN_DoublePreference(
                "elevatorClosedLoopSpeed", 1);
        public static final SN_DoublePreference elevatorAllowableClosedLoopError = new SN_DoublePreference(
                "elevatorAllowableClosedLoopError", 0);

        public static final SN_DoublePreference elevatorPerpendicularMinPos = new SN_DoublePreference(
                "elevatorPerpendicularMinPos", 0);
        public static final SN_DoublePreference elevatorAngledMinPos = new SN_DoublePreference("elevatorAngledMinPos",
                120000);
        public static final SN_DoublePreference elevatorPerpendicularMaxPos = new SN_DoublePreference(
                "elevatorPerpendicularMaxPos", 207000);
        public static final SN_DoublePreference elevatorAngledMaxPos = new SN_DoublePreference("elevatorAngledMaxPos",
                277300);
        public static final SN_DoublePreference elevatorSlowdownMinThresholdEncoderCounts = new SN_DoublePreference(
                "elevatorSlowdownMinThreshold", 0);
        public static final SN_DoublePreference elevatorSlowdownMaxThresholdEncoderCounts = new SN_DoublePreference(
                "elevatorSlowdownMaxThreshold", 28000);
        public static final SN_DoublePreference elevatorSlowdownSpeed = new SN_DoublePreference("elevatorSlowdownSpeed",
                0.5);
    }
}