package com.elderj.contactsbook;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class MainPresenterTest {

    private MainActivityView view;
    private MainPresenter presenter;

    @Before
    public void setUp() {
        view = mock(MainActivityView.class);
    }

    @Test
    public void on_save_org_button_tapped_presenter_tells_database_to_save_new_org() {
        presenter = new MainPresenter(view);
        presenter.saveOrgButtonTapped("", "", "");

        verify(dbConnector).saveNewOrg();
    }

}