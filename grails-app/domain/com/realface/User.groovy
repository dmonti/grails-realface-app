package com.realface

import com.realface.TextUtils

class User extends Person
{
    String code;

    String email;

    String password;

    String passwordSalt;

    Date dateCreated;

    Date lastUpdated;

    static constraints =
    {
        code(unique: true);
        email(unique: true);
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

    public static boolean existsUsernameOrEmail(String codeOrEmail)
    {
        final String query = "SELECT 1 FROM User WHERE code = :codeOrEmail OR email = :codeOrEmail";
        return !executeQuery(query, [codeOrEmail: codeOrEmail], [max: 1]).isEmpty()
    }
}