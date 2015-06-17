package com.realface

import com.neurotec.biometrics.NBiometricStatus

class PhotoTemplateController
{
    def photoTemplateService

    def capture()
    {
        PhotoTemplate photo = photoTemplateService.capture()
        return render(contentType: "text/json") { photo }
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
            File template1 = photoTemplateService.getTemplateFile(photo1)
            File template2 = photoTemplateService.getTemplateFile(photo2)

            photoTemplateService.recognize(template1, template2)
        }
        return render(1)
    }
}