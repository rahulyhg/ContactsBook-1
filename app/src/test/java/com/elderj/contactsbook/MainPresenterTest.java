package com.elderj.contactsbook;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class MainPresenterTest {

    private MainActivityView view;
    private DatabaseConnecting dbConnector;
    private MainPresenter presenter;

    @Before
    public void setUp() {
        view = mock(MainActivityView.class);
        dbConnector = mock(DatabaseConnecting.class);
    }

    @Test
    public void on_save_org_button_tapped_presenter_tells_database_to_save_new_org() {
        presenter = new MainPresenter(view, dbConnector);
        presenter.saveOrgButtonTapped("", "", "");

        verify(dbConnector).createOrg(any(String.class), any(String.class), any(String.class), any(DatabaseCallback.class));
    }



}