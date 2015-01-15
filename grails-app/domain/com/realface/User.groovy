package com.realface

class User extends Person
{
    String code;

    String email;

    String password;

    String passwordSalt;

    Date dateCreated

    Date lastUpdated

    static constraints = { }

    public String getPasswordSalt()
    {
        if (passwordSalt == null)
        {
            passwordSalt = buildNewPasswordSalt();
        }
        return passwordSalt;
    }

    private String buildNewPasswordSalt()
    {
        String salt = "";
        for (int i = 0; i < 7; i++)
        {
            char c;
            double x = Math.random();
            if (x < 0.31)
                c = (char) ('A' + ((int) (Math.random() * 25)));
            else if (x < 0.62)
                c = (char) ('a' + ((int) (Math.random() * 25)));
            else if (x < 0.93)
                c = (char) ('0' + ((int) (Math.random() * 10)));
            else if (x < 0.95)
                c = '=';
            else
                c = '_';
            salt += c;
        }
        salt += "=";
        return salt;
    }

    public void encodePassword(String password)
    {
        this.password = TextUtils.hash("SHA1", buildSecretPassword(password));
    }

    private String buildSecretPassword(String password)
    {
        String passwordSalt = getPasswordSalt();
        return new StringBuilder("rface").append(password).append(passwordSalt).append("8.");
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
}