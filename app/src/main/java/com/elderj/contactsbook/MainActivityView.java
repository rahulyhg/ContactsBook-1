package com.elderj.contactsbook;

import java.util.List;

public interface MainActivityView {

    void showPeopleList(List<String> people);
    void showOrgList(List<Org> orgs);
    void showEditOrgDialog(Org org);

}
