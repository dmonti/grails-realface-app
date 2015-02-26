package com.realface

class Credential
{
    String code

    String password

    String passwordSalt

    AccessLevel level

    boolean enabled

    Date dateCreated

    Date lastUpdated

    static belongsTo = [user: User]

    static mappedBy = [user: "credential"]

    static constraints =
    {
        code(size: 4..16, unique: true)
        password(nullable: true, blank: false)
        passwordSalt(nullable: true, blank: false)
    }

    static mapping =
    {
        enabled(defaultValue: true)
        level(enumType: "ordinal", defaultValue: 0)
    }

    public boolean checkPassword(String password)
    {
        String secretPwd = buildSecretPassword(password)
        String hashedPassword = TextUtils.hash("SHA1", secretPwd)

        return hashedPassword.equals(getPassword())
    }

    public String getPasswordSalt()
    {
        if (passwordSalt == null)
        {
            passwordSalt = TextUtils.generatePasswordSalt()
        }
        return passwordSalt
    }

    public void setAndEncodePassword(String password)
    {
        setPassword(password)
        encodePassword()
    }

    private String encodePassword()
    {
        String password = getPassword()
        String secretPwd = buildSecretPassword(password)
        setPassword(TextUtils.hash("SHA1", secretPwd))
    }

    private String buildSecretPassword(String password)
    {
        String passwordSalt = getPasswordSalt()
        return new StringBuilder("rface").append(password).append(passwordSalt).append("sys")
    }
}