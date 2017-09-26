package com.elderj.contactsbook;

public class Org {

    public final int id;
    public final String name;
    public final String email;
    public final String phone;

    public Org(int id, String name, String email, String phone) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Org org = (Org) o;

        if (id != org.id) return false;
        if (name != null ? !name.equals(org.name) : org.name != null) return false;
        if (email != null ? !email.equals(org.email) : org.email != null) return false;
        return phone != null ? phone.equals(org.phone) : org.phone == null;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        return result;
    }
}
