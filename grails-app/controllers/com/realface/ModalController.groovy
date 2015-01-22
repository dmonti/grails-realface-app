package com.realface

import org.codehaus.groovy.grails.web.servlet.mvc.exceptions.ControllerExecutionException;

class ModalController
{
    def index()
    {
        redirect(action: "load", params: params);
    }

    def load()
    {
        String template = params.template;
        if (!template || template.isEmpty()) {
            log.warn("Template not informed!");
            return render("");
        }

        try {
            return render(template: template);
        } catch (ControllerExecutionException e) {
            log.warn("Exception loading template: '${template}'", e);
            return render("");
        }
    }
}