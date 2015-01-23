package com.realface

import com.realface.TextUtils

class User extends Person
{
    String code;

    String password;

    String passwordSalt;

    static constraints =
    {
        code(blank: false, size: 4..16, unique: true);
    }

    static mapping =
    {
        table("AppUser");
    }

    public String getPasswordSalt()
    {
        if (passwordSalt == null)
        {
            passwordSalt = TextUtils.generatePasswordSalt();
        }
        return passwordSalt;
    }

    public void encodePassword(String password)
    {
        this.password = TextUtils.hash("SHA1", buildSecretPassword(password));
    }

    private String buildSecretPassword(String password)
    {
        String passwordSalt = getPasswordSalt();
        return new StringBuilder("rface").append(password).append(passwordSalt).append("8");
    }

    public boolean checkPassword(String password)
    {
        String secretPwd = buildSecretPassword(password);
        String hashedPassword = TextUtils.hash("SHA1", secretPwd);
        boolean checked = hashedPassword.equals(getPassword());

        return checked;
    }

    public String generatePasswordTokenHash()
    {
        return TextUtils.hash("SHA1", "jwt" + getId() + password + code);
    }

    public static User findUser(String codeOrEmail)
    {
        List result = executeQuery(
            "FROM User WHERE code = :codeOrEmail OR email = :codeOrEmail",
            [codeOrEmail: codeOrEmail], [max: 1]
        );

        User user;
        if (result.isEmpty())
            user = null;
        else
            user = result.first();

        return user;
    }

    public static boolean existsUsernameOrEmail(String codeOrEmail)
    {
        final String query = "SELECT 1 FROM User WHERE code = :codeOrEmail OR email = :codeOrEmail";
        return !executeQuery(query, [codeOrEmail: codeOrEmail], [max: 1]).isEmpty()
    }
}