package com.realface

class UserController
{
    def index()
    {
        List users = User.findAll("from User", [max: 25]);
        return [ users: users ]
    }
}