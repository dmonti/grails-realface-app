package com.realface

import org.codehaus.groovy.grails.web.servlet.mvc.exceptions.ControllerExecutionException;

class ModalController
{
    def load()
    {
        String path = params.path;
        if (!path || path.isEmpty())
        {
            log.warn("Template path not informed!");
            return render("");
        }

        try
        {
            return render(template: path, model: params);
        }
        catch (ControllerExecutionException e)
        {
            log.warn("Exception loading template path: '${path}'", e);
            return render("");
        }
    }
}