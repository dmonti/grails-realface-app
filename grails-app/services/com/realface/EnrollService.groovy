package com.realface

import grails.transaction.Transactional
import com.neurotec.biometrics.NBiometricOperation
import com.neurotec.biometrics.NBiometricStatus
import com.neurotec.biometrics.NBiometricTask
import com.neurotec.biometrics.NSubject
import com.neurotec.samples.FaceTools

@Transactional
class EnrollService
{
    private static final List<NSubject> subjects = new ArrayList<NSubject>()

    def storageService

    public void loadCache()
    {
        clear()
        for (PhotoTemplate photo : loadVerifiedTemplates())
        {
            addSubject(loadSubject(photo))
        }
        perform()
    }

    public List<PhotoTemplate> loadVerifiedTemplates()
    {
        return PhotoTemplate.findAll(
            "FROM PhotoTemplate WHERE user IS NOT NULL AND status = ? AND authenticity = ?",
            [NBiometricStatus.OK, AuthenticityStatus.VERIFIED]
        )
    }

    public NSubject loadSubject(PhotoTemplate photo)
    {
        File file = storageService.getTemplateFile(photo)
        NSubject subject = NSubject.fromFile(file.getAbsolutePath())
        subject.setId(photo.getSubjectId())
        return subject
    }

    public List<NSubject> getSubjects()
    {
        return EnrollService.subjects
    }

    public void addSubject(NSubject subject)
    {
        EnrollService.subjects.add(subject)
    }

    public void clear()
    {
        EnrollService.subjects.clear()
    }

    public void perform()
    {
        NBiometricTask enrollmentTask = new NBiometricTask(EnumSet.of(NBiometricOperation.ENROLL))
        for (NSubject subject : getSubjects())
        {
            enrollmentTask.getSubjects().add(subject)
        }
        EnrollAttach enrollAttach = new EnrollAttach()
        FaceTools.getInstance().getClient().performTask(enrollmentTask, enrollAttach, new EnrollHandler())
    }
}