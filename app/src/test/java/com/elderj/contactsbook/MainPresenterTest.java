package com.elderj.contactsbook;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyListOf;
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

    @Test
    public void on_database_callback_complete_presenter_tells_view_to_update_text() {
        presenter = new MainPresenter(view, dbConnector);

        ArgumentCaptor<DatabaseCallback> captor = ArgumentCaptor.forClass(DatabaseCallback.class);

        presenter.saveOrgButtonTapped("", "", "");
        verify(dbConnector).createOrg(any(String.class), any(String.class), any(String.class), captor.capture());
        captor.getValue().actionComplete();

        verify(view).showOrgList(anyListOf(Org.class));
    }

    @Test
    public void on_resume_presenter_tells_view_to_show_org_list() {
        presenter = new MainPresenter(view, dbConnector);

        presenter.onResume();

        verify(view).showOrgList(anyListOf(Org.class));
    }

    @Test
    public void on_resume_presenter_reads_orgs_from_database() {
        presenter = new MainPresenter(view, dbConnector);

        presenter.onResume();

        verify(dbConnector).readAllOrgs();
    }

}