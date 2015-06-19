package com.realface

import com.neurotec.biometrics.NBiometricStatus

class IdentificationController
{
    def identificationService

    def index()
    {
        [logs: PhotoIdentificationLog.list(sort: "id", order: "desc")]
    }

    def match()
    {
        PhotoTemplate photo1 = PhotoTemplate.get(params.id1)
        PhotoTemplate photo2 = PhotoTemplate.get(params.id2)

        if (!NBiometricStatus.OK.equals(photo1.status))
        {
            log.warn("Invalid template 1, status: ${photo1.status}!")
        }
        else if (!NBiometricStatus.OK.equals(photo2.status))
        {
            log.warn("Invalid template 2, status: ${photo2.status}!")
        }
        else
        {
            identificationService.recognize(photo1, photo2)
        }
        return render(1)
    }
}