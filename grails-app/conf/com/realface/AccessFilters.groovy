package com.realface

class AccessFilters
{
    def filters =
    {
        loginFilter(invert: true, controller: "access")
        {
            before =
            {
                if (controllerName && !session.currentUserId)
                {
                    redirect(uri: "/login");
                    return false
                }
            }

            after = { Map model ->
                model.currentUser = User.get(session.currentUserId);
            }

            afterView = { Exception e -> }
        }
    }
}