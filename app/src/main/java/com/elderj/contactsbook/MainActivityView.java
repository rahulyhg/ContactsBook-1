package com.elderj.contactsbook;

import java.util.List;

public interface MainActivityView {

    void showPeopleList(List<Person> people);
    void showOrgList(List<Org> orgs);
    void showEditOrgDialog(Org org);

}
