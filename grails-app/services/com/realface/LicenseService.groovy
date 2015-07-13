package com.realface

import com.neurotec.samples.FaceTools

class LicenseService
{
    public void obtainAll()
    {
        FaceTools.getInstance().obtainLicenses([
            "Devices.Cameras",
            "Biometrics.FaceMatching",
            "Biometrics.FaceDetection",
            "Biometrics.FaceExtraction",
            "Biometrics.FaceSegmentsDetection"
        ])
    }
}